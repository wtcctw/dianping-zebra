package com.dianping.zebra.admin.controller;

import java.util.List;

import org.junit.Test;

import com.dianping.zebra.biz.dto.PumaClientSyncTaskDto;

public class PumaClientSyncTaskControllerTest {
	
	@Test
	public void test(){
		PumaClientSyncTaskController controller = new PumaClientSyncTaskController();
		
		
		List<PumaClientSyncTaskDto> generatePlan = controller.generatePlan("unifiedorder");
		
		
		System.out.println(generatePlan);
	}

}
