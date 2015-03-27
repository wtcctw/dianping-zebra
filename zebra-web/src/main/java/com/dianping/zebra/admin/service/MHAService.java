package com.dianping.zebra.admin.service;

import java.util.Set;

public interface MHAService {

	public Set<String> findDsIds(String ip, String port);

	public boolean isMarkdownByMHA(String dsId);
	
	public void markDownDsIds(Set<String> dsIds);
	
	public void markUpDsId(String dsId);
}
