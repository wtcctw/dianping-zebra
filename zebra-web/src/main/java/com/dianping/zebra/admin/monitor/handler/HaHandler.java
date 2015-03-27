package com.dianping.zebra.admin.monitor.handler;

public interface HaHandler {

	public enum Operator {
		ZEBRA("zebra"),

		MHA("mha"),

		PEOPLE("people");

		private String operator;

		private Operator(String operator) {
			this.operator = operator;
		}

		public String getOperator() {
			return operator;
		}

	}

	public boolean markdown(String dsId, Operator operator);

	public boolean markup(String dsId, Operator operator);
}
