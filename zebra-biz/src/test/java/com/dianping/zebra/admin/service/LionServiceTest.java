package com.dianping.zebra.admin.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Ignore;
import org.junit.Test;

import com.dianping.zebra.biz.service.HttpService;
import com.dianping.zebra.biz.service.impl.HttpServiceImpl;

@Ignore
public class LionServiceTest {
	
	@Test
	public void testSetKey() throws UnsupportedEncodingException{
		HttpService httpService = new HttpServiceImpl();
		
		String params = String.format("env=%s&id=%s&key=%s&value=%s", "qa", 2, "lion-test.egg", URLEncoder.encode("12312312312aslkdflasjdflasjdlfjasldfjlasjflasjdflkasjdlfasjdlfjasldfjalsdjflkasdjflkasdjflajsdlfjaslkfjlasdjflasjdflkjaslkdfjlaksdjflasjflasjdfljaslkfjasldjflasdjflasjdflkasjdasjdlfjlasjflasjflasjdlfjasldfjlasjflkasdjflasjdflkasjdlfkjaslkjflasjdflasjdlfjasldfjlasdjflasjdlfjaslfjlasdjflasjlfjalsjflasjflasjdfljasldfjasljflasjflkasjflkjasdlfjalsjflasjdflasjdflajslfjasldfjlasdjflaksjdflkasjflkasjflasdjflasdjflasjflasjdflkasjdflasjflajsflasjflasjdflkasjfl", "utf-8"));
		String result = httpService.sendPost("http://lionapi.dp:8080/config2/set", params);
		
		System.out.println(result);
		
		
	}

}
