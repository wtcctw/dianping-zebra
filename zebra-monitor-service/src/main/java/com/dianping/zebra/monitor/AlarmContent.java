package com.dianping.zebra.monitor;

public class AlarmContent {
	
	private String op;                 //insert
	
	private String type;               //SQL
	
	private String title;              //标题
	
	private String domain;             //数据库
	
	private String hostname;           //datasource
	
	private String user;               //变更人
	
	private String ip;                 //机器ip
	
	private String alterationDate;     //xxxx-xx-xx xx:xx:xx
	
	private String status;             //状态
	
	private String content;            //内容
	
	private int    delay;              //延迟
	
	public AlarmContent(String hostname,String title,String user,String ip,String content,int delay) {
		this.hostname = hostname;
		this.title    = title;
		this.user     = user;
		this.ip       = ip;
		this.content  = content;
		this.delay    = delay;
	}
	
	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAlterationDate() {
		return alterationDate;
	}

	public void setAlterationDate(String alterationDate) {
		this.alterationDate = alterationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	
}
