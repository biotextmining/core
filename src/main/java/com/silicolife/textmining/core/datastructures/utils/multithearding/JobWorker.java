package com.silicolife.textmining.core.datastructures.utils.multithearding;

public class JobWorker implements Runnable {
	
	protected JobQueue waiting;
	protected JobQueue finished;
	protected IParallelJob<?> currentJob;
	private boolean kill = false;
	

	
	public JobWorker(JobQueue waiting) {
		this.waiting = waiting;
	}
	
	public JobWorker(JobQueue waiting, JobQueue finished) {
		this.waiting = waiting;
		this.finished = finished;
	}

	public void run() {
		while(!waiting.isEmpty() && !kill ) {
			currentJob = getNextJob();
			currentJob.run();
			if(finished!=null)
			{
				storeJob(currentJob);
			}
			currentJob = null;
		}
	}

	protected synchronized IParallelJob<?> getNextJob() {
		return waiting.poll();
	}
	
	protected synchronized void storeJob(IParallelJob<?> job) {
		finished.add(job);
	}

	public void kill() {
		kill = true;
		if(currentJob!=null)
			currentJob.kill();
	}

}
