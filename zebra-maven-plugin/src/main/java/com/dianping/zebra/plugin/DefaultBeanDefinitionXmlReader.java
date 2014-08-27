package com.dianping.zebra.plugin;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class DefaultBeanDefinitionXmlReader {
	
	private Document doc;
	
	public DefaultBeanDefinitionXmlReader(String path){
		InputStream inputStream = this.getClass().getResourceAsStream(path);
		
		try {
	      doc = new SAXReader().read(inputStream);
      } catch (DocumentException e) {
      	e.printStackTrace();
      }
	}
	
	public void print(){
		System.out.println(doc.toString());
	}
}
