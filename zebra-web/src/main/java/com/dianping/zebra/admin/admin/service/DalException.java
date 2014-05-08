package com.dianping.zebra.admin.admin.service;

import java.util.ArrayList;
import java.util.List;

public class DalException extends Exception {

	/**
	 * 
	 */
   private static final long serialVersionUID = 1L;
   
   private List<String> m_markedDataSources = new ArrayList<String>();
   
   public DalException(String message, List<String> markedDataSources){
   	super(message);
   	
   	m_markedDataSources = markedDataSources;
   }
   
   public List<String> getMarkedDataSources(){
   	return m_markedDataSources;
   }

}
