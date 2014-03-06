/**
 * TestAppService.java
 */
package com.qianwang365.confkeeper.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.qianwang365.confkeeper.model.AppEntity;
import com.qianwang365.confkeeper.utils.Pagination;

/**
 * @author Yate
 * @date Dec 20, 2013
 * @description TODO
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/app-config.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestAppService extends
		AbstractTransactionalJUnit4SpringContextTests {
	@Resource
	private IAppService appService;

	@Test
	public void add() {
		Pagination<AppEntity> p = appService
				.getEntities(1, 10, new AppEntity());
		Assert.assertTrue(p.getTotal() > 0);
	}
}
