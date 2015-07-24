package com.dianping.zebra.admin.util;

public class CatAlarmContent {
	
	private String op;                 //insert
	
	private String type;               //SQL
	
	private String title;              //标题
	
	private String domin;             //数据库
	
	private String hostname;           //datasource
	
	private String user;               //变更人
	
	private String ip;                 //机器ip
	
	private String alterationDate;     //xxxx-xx-xx xx:xx:xx
	
	private String status;             //状态
	
	private String content;            //内容

	public CatAlarmContent(String hostname,String title,String user,String ip,String content) {
		this.hostname = hostname;
		this.title    = title;
		this.user     = user;
		this.ip       = ip;
		this.content  = content;
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

	public String getDomin() {
		return domin;
	}

	public void setDomin(String domain) {
		this.domin = domain;
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
	
}
