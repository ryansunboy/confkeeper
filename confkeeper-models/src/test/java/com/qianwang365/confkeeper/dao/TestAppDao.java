// TestUserEntity
package com.qianwang365.confkeeper.dao;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qianwang365.confkeeper.dao.mapper.AppMapper;
import com.qianwang365.confkeeper.model.AppEntity;

/**
 * @author Yate
 * @Date 2012-5-11
 * @description 详细说明
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring/app-config.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAppDao {
	@Resource
	AppMapper dao;

	@Test
	public void testCRUD() {
		AppEntity e = new AppEntity();
		e.setAppName("testapp");
		e.setAppNote("testtesttest");
		dao.add(e);

		AppEntity t = dao.getEntity(e.getId());
		Assert.assertEquals(t.getAppName(), "testapp");

		AppEntity u = dao.getEntity(e.getId());
		Assert.assertEquals(u.getAppName(), "testapp");
		u.setAppName("hehe");
		dao.update(u);
		AppEntity x = dao.getEntity(u.getId());
		Assert.assertEquals(x.getAppName(), "hehe");

		AppEntity e1 = dao.getEntity(e.getId());
		Assert.assertNotNull(e1);
		dao.remove(e.getId());
		AppEntity e2 = dao.getEntity(e.getId());
		Assert.assertNull(e2);
	}

}
