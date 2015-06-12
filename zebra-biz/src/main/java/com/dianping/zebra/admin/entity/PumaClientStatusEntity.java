package com.dianping.zebra.admin.entity;

/**
 * Dozer @ 6/12/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class PumaClientStatusEntity {
	private int id;

	private int taskId;

	private volatile long sequence;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
}
