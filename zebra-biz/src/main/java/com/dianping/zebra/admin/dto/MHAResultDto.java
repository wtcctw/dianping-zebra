package com.dianping.zebra.admin.dto;

import java.util.HashSet;
import java.util.Set;

public class MHAResultDto {
		private String status;

		private Set<String> dsIds;

		public void addDsId(String dsId) {
			if (dsIds == null) {
				dsIds = new HashSet<String>();
			}

			dsIds.add(dsId);
		}

		public Set<String> getDsIds() {
			return dsIds;
		}

		public String getStatus() {
			return status;
		}

		public void setDsIds(Set<String> dsIds) {
			this.dsIds = dsIds;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	}