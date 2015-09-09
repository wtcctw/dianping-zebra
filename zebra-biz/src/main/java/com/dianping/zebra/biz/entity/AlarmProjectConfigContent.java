package com.dianping.zebra.biz.entity;

public class AlarmProjectConfigContent {

	private boolean autoMarkup;

	private boolean autoMarkdownForDown;

	private boolean autoMarkdownForDelay;

	private int minDelayTime;

	private int maxDelayTime;

	public AlarmProjectConfigContent(boolean autoMarkup, boolean autoMarkdownForDown, boolean autoMarkdownForDelay,
			int minDelayTime, int maxDelayTime) {
		this.autoMarkup = autoMarkup;
		this.autoMarkdownForDown = autoMarkdownForDown;
		this.autoMarkdownForDelay = autoMarkdownForDelay;
		this.minDelayTime = minDelayTime;
		this.maxDelayTime = maxDelayTime;
	}

	public AlarmProjectConfigContent(AlarmProjectConfigContent item) {
		this.autoMarkup = item.autoMarkup;
		this.autoMarkdownForDown = item.autoMarkdownForDown;
		this.autoMarkdownForDelay = item.autoMarkdownForDelay;
		this.minDelayTime = item.minDelayTime;
		this.maxDelayTime = item.maxDelayTime;
	}

	public void setAutoMarkup(boolean autoMarkup) {
		this.autoMarkup = autoMarkup;
	}

	public boolean isAutoMarkup() {
		return autoMarkup;
	}
	
	public void setAutoMarkdownForDown(boolean autoMarkdownForDown) {
		this.autoMarkdownForDown = autoMarkdownForDown;
	}

	public boolean isAutoMarkdownForDown() {
		return this.autoMarkdownForDown;
	}

	public void setAutoMarkdownForDelay(boolean autoMarkdownForDelay) {
		this.autoMarkdownForDelay = autoMarkdownForDelay;
	}

	public boolean isAutoMarkdownForDelay() {
		return this.autoMarkdownForDelay;
	}

	public void setMinDelayTime(int minDelayTime) {
		this.minDelayTime = minDelayTime;
	}

	public int getMinDelayTime() {
		return this.minDelayTime;
	}

	public void setMaxDelayTime(int maxDelayTime) {
		this.maxDelayTime = maxDelayTime;
	}

	public int getMaxDelayTime() {
		return this.maxDelayTime;
	}
}
