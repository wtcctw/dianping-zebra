package com.dianping.zebra.dao.plugin.page;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dianping.zebra.dao.entity.HeartbeatEntity;
import com.dianping.zebra.dao.mapper.HeartbeatMapper;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:config/spring/common/appcontext-*.xml")
public class PageTest {

	@Autowired
	private HeartbeatMapper dao;

	@Test
	public void testPhysicalPageWithPaginate() {
		PageModel paginate = new PageModel(2, 100);
		dao.getAll(paginate);
		
		System.out.println(paginate.getRecordCount());
		Assert.assertEquals(100, paginate.getRecords().size());
	}
	
	@Test
	public void testPhysicalPageWithBound() {
		List<HeartbeatEntity> rows = dao.getPage(new RowBounds(0,100));
		
		System.out.println(rows.size());
	}
	
	@Test
	public void testPhysicalPageWithBound2() {
		PageModel paginate = new PageModel(1, 100);

		dao.getAllExcludeAppName("taurus-agent", paginate);

		System.out.println(paginate.getRecordCount());
		Assert.assertEquals(100, paginate.getRecords().size());
	}
}
