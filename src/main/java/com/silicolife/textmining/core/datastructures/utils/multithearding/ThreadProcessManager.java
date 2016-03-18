package com.silicolife.textmining.core.datastructures.utils.multithearding;

import java.util.ArrayList;
import java.util.List;

import com.silicolife.textmining.core.datastructures.utils.conf.OtherConfigurations;

public class ThreadProcessManager {

	
	protected JobQueue waiting;
	protected JobQueue finished;
	protected int numberOfThreads;
	protected List<JobWorker> runningJobs;
	protected List<Thread> runningThreads;
	protected List<IParallelJob<?>> jobs;
	private int max = 0;

	
	public ThreadProcessManager(boolean finishing) {
		this.waiting 	= new JobQueue();
		if(finishing)
			this.finished 	= new JobQueue();
		this.runningJobs = new ArrayList<JobWorker>();
		this.runningThreads = new ArrayList<Thread>();
		this.jobs = new ArrayList<IParallelJob<?>>();
		this.numberOfThreads = OtherConfigurations.getThreadsNumber();
	}
	
	public ThreadProcessManager(boolean finishing,int numberOfThreads) {
		this.waiting 	= new JobQueue();
		if(finishing)
			this.finished 	= new JobQueue();
		this.numberOfThreads = numberOfThreads;
		this.runningJobs = new ArrayList<JobWorker>();
		this.runningThreads = new ArrayList<Thread>();
		this.jobs = new ArrayList<IParallelJob<?>>();
	}
	
	public int getNumberOfThreads() {
		return numberOfThreads;
	}
	
	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}
	
	public void run() {
		int max = numberOfThreads;
		if(this.max<numberOfThreads)
			max = this.max;
		for(int i = 0; i < max ; i++) {
			JobWorker job = new JobWorker(waiting, finished);
			Thread t = new Thread(job);
			runningJobs.add(job);
			runningThreads.add(t);
			t.start();
		}
	}
	
	public boolean isComplete() {
		return waiting.isEmpty();
	}
	
	public int numberOfCompleteJobs() {
		return max - waiting.size() ;
	}
	
	public void join() throws InterruptedException {
		for(Thread t : runningThreads)
		{
			t.join();
		}
	}
	
	public void kill() {
		jobs = new ArrayList<IParallelJob<?>>();
		for(JobWorker t : runningJobs)
		{
			t.kill();
		}
		for(Thread th :runningThreads)
		{
			th.interrupt();
		}
		
	}

	public void addJob(IParallelJob<?> job) {
		waiting.add(job);
		jobs.add(job);
		max ++;
	}
	
	public List<Object> getResults()
	{
		List<Object> list = new ArrayList<Object>();
		for(IParallelJob<?> job : jobs)
		{
			list.add(job.getResultJob());
		}
		return list;
	}

	public JobQueue getWaiting() {
		return waiting;
	}

	public JobQueue getFinished() {
		return finished;
	}
	
	
		
	
}
