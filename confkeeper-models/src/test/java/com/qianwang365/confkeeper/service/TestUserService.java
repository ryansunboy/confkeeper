// TestUserService
package com.qianwang365.confkeeper.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.qianwang365.confkeeper.model.UserEntity;

/**
 * @author Yate
 * @Date 2012-5-12
 * @description 详细说明
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/app-config.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestUserService extends
		AbstractTransactionalJUnit4SpringContextTests {
	@Resource
	private IUserService userService;

	@Test
	public void add() {
		UserEntity e = new UserEntity();
		e.setUserName("test");
		e.setPasswd("testdao");
		this.userService.add(e);
		System.out.println(e.getId());
	}
}
