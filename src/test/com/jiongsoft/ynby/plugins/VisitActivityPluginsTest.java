package com.jiongsoft.ynby.plugins;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import com.jiongsoft.TestAll;
import com.jiongsoft.cocit.entity.CocEntityEvent;
import com.jiongsoft.cocit.utils.CocException;
import com.jiongsoft.ynby.entity.VisitActivity;
import com.jiongsoft.ynby.plugins.VisitActivityPlugins.SaveActivity;

public class VisitActivityPluginsTest {
	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void testSaveActivity1() {
		SaveActivity plugin = new SaveActivity();

		CocEntityEvent<VisitActivity> event = new CocEntityEvent();
		VisitActivity entity = new VisitActivity();

		event.setEntityData(entity);

		plugin.before(event);
	}

	@Test(expected = CocException.class)
	public void testSaveActivity2() {
		SaveActivity plugin = new SaveActivity();

		CocEntityEvent<VisitActivity> event = new CocEntityEvent();
		VisitActivity entity = new VisitActivity();
		entity.setPlanDate(new Date(2013 - 1900, 10 - 1, 27));

		// 有效期非法
		entity.setExpiredTo(new Date(2013 - 1900, 10 - 1, 27));

		entity.setPlanPersonNumber(10);

		event.setEntityData(entity);

		plugin.before(event);
	}

	@Test(expected = CocException.class)
	public void testSaveActivity3() {
		SaveActivity plugin = new SaveActivity();

		CocEntityEvent<VisitActivity> event = new CocEntityEvent();
		VisitActivity entity = new VisitActivity();
		entity.setPlanDate(new Date(2013 - 1900, 10 - 1, 27));

		// 有效期非法
		entity.setExpiredFrom(new Date(2013 - 1900, 10 - 1, 25));
		entity.setExpiredTo(new Date(2013 - 1900, 10 - 1, 25));

		entity.setPlanPersonNumber(10);

		event.setEntityData(entity);

		plugin.before(event);
	}

	@Test(expected = CocException.class)
	public void testSaveActivity4() {
		SaveActivity plugin = new SaveActivity();

		CocEntityEvent<VisitActivity> event = new CocEntityEvent();
		VisitActivity entity = new VisitActivity();
		entity.setPlanDate(new Date(2013 - 1900, 10 - 1, 27));
		entity.setExpiredFrom(new Date(2013 - 1900, 10 - 1, 22));
		entity.setExpiredTo(new Date(2013 - 1900, 10 - 1, 25));

		// 计划人数非法
		entity.setPlanPersonNumber(0);

		event.setEntityData(entity);

		plugin.before(event);
	}

	@Test
	public void testSaveActivity5() {
		SaveActivity plugin = new SaveActivity();

		CocEntityEvent<VisitActivity> event = new CocEntityEvent();
		VisitActivity entity = new VisitActivity();
		entity.setPlanDate(new Date(2013 - 1900, 10 - 1, 27));
		entity.setExpiredFrom(new Date(2013 - 1900, 10 - 1, 22));
		entity.setExpiredTo(new Date(2013 - 1900, 10 - 1, 25));
		entity.setPlanPersonNumber(50);

		event.setEntityData(entity);

		plugin.before(event);
	}
	
	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}