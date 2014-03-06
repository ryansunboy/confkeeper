// TestUserEntity
package com.qianwang365.confkeeper.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qianwang365.confkeeper.dao.mapper.UserMapper;
import com.qianwang365.confkeeper.model.UserEntity;

/**
 * @author Yate
 * @Date 2012-5-11
 * @description 详细说明
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring/app-config.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserDao {
	@Resource
	UserMapper dao;

	@Test
	public void testCRUD() {
		UserEntity e = new UserEntity();
		e.setUserName("test");
		e.setPasswd("testdao");
		dao.add(e);

		UserEntity t = dao.getEntity(e.getId());
		Assert.assertEquals(t.getUserName(), "test");

		UserEntity u = dao.getEntity(e.getId());
		Assert.assertEquals(u.getUserName(), "test");
		u.setUserName("hehe");
		dao.update(u);
		UserEntity x = dao.getEntity(u.getId());
		Assert.assertEquals(x.getUserName(), "hehe");

		UserEntity e1 = dao.getEntity(e.getId());
		Assert.assertNotNull(e1);
		dao.remove(e.getId());
		UserEntity e2 = dao.getEntity(e.getId());
		Assert.assertNull(e2);
	}

	@Test
	public void testBatch() {
		List<UserEntity> us = new ArrayList<UserEntity>();
		UserEntity e;
		for (int i = 2; i < 10; i++) {
			e = new UserEntity();
			e.setId((long) i);
			e.setUserName("test" + i);
			e.setPasswd("test" + i);
			us.add(e);
		}
		dao.batchAdd(us);

		List<UserEntity> dus = new ArrayList<UserEntity>();
		for (int i = 2; i < 10; i++) {
			e = new UserEntity();
			e.setId((long) i);
			dus.add(e);
		}
	}

}
