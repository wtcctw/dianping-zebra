package com.dianping.zebra.monitor;

import com.dianping.zebra.monitor.filter.CatFilterTest;
import com.dianping.zebra.monitor.monitor.GroupDataSourceMonitorTest;
import com.dianping.zebra.monitor.monitor.SingleDataSourceMonitorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({ CatFilterTest.class, GroupDataSourceMonitorTest.class, SingleDataSourceMonitorTest.class })
public class AllTests {

}