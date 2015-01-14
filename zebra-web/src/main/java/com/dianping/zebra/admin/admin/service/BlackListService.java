package com.dianping.zebra.admin.admin.service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Dozer on 10/14/14.
 */
public interface BlackListService {
	Map<String, Map<String, BlackExtend>> getAllBlackList(String env) throws IOException;

	void deleteItem(String env, String key, String item) throws IOException;

	void addItem(String env, String ip, String item, String comment) throws IOException;

	public static class BlackExtend {
		private String comment;

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}
	}
}
