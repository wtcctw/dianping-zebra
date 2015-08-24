package com.dianping.zebra.biz.dto;

public class MonitorDto {

	private int errorCode;

	private String errorMessage;

	public MonitorDto(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public MonitorDto(ErrorStyle es) {
		this.errorCode = es.errorCode;
		this.errorMessage = es.errorMsg;
	}
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public enum ErrorStyle {
		
		Success(0,"OK"),JdbcError(-1,"jdbcRef wrong"),ConnectError(2,"数据库连接失败，请检查权限!"),EnvError(3,"环境错误");
		
		
		public final int errorCode;
		
		public final String errorMsg;
		
		ErrorStyle(int errorCode,String errorMsg) {
			this.errorCode = errorCode;
			
			this.errorMsg = errorMsg;
		}
		

	}
}
