package com.silicolife.textmining.core.datastructures.utils.multithearding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class JobQueue implements Queue<IParallelJob> {
	
	protected List<IParallelJob> jobs;
	
	public JobQueue() {
		this.jobs = new ArrayList<IParallelJob>();
	}
	
	public boolean addAll(Collection<? extends IParallelJob> jobsList) {
		jobs.addAll(jobsList);	
		return true;
	}

	@Override
	public void clear() {
		jobs.clear();
	}

	@Override
	public boolean contains(Object job) {
		return jobs.contains(job);
	}

	@Override
	public boolean containsAll(Collection<?> jobsList) {
		return jobs.containsAll(jobsList);
	}

	@Override
	public boolean isEmpty() {
		return jobs.isEmpty();
	}

	public Iterator<IParallelJob> iterator() {
		return jobs.iterator();
	}

	@Override
	public boolean remove(Object job) {
		return jobs.remove(job);
	}

	@Override
	public boolean removeAll(Collection<?> jobsList) {
		return jobs.removeAll(jobsList);
	}

	@Override
	public boolean retainAll(Collection<?> jobsList) {
		return jobs.retainAll(jobsList);
	}

	@Override
	public int size() {
		return jobs.size();
	}

	@Override
	public Object[] toArray() {
		return jobs.toArray();
	}

	@Override
	public <T> T[] toArray(T[] jobsArray) {
		return jobs.toArray(jobsArray);
	}

	public boolean add(IParallelJob job) {
		return jobs.add(job);
	}

	public IParallelJob element() {
		if(jobs.size() > 0)
			return jobs.get(0);
		else
			return null;
	}

	
	public boolean offer(IParallelJob job) {
		return jobs.add(job);
	}

	public IParallelJob peek() {
		return element();
	}

	public IParallelJob poll() {
		if(jobs.size() > 0)
			return jobs.remove(0);
		else
			return null;
	}

	public IParallelJob remove() {
		return poll();
	}
	
	

}
