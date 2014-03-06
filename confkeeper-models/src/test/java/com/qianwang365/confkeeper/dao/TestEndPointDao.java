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
import com.qianwang365.confkeeper.dao.mapper.EndPointMapper;
import com.qianwang365.confkeeper.model.AppEntity;
import com.qianwang365.confkeeper.model.EndPointEntity;
import com.qianwang365.confkeeper.utils.Pagination;

/**
 * @author Yate
 * @Date 2012-5-11
 * @description 详细说明
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring/app-config.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEndPointDao {
	@Resource
	EndPointMapper epdao;

	@Resource
	AppMapper dao;

	@Test
	public void testCRUD() {
		AppEntity e = new AppEntity();
		e.setAppName("testapp");
		e.setAppNote("testtesttest");
		dao.add(e);

		EndPointEntity ep = new EndPointEntity();
		ep.setApp(e);
		ep.setTrunk("1");
		ep.setState(1);
//		ep.setState(EpState.ON);
		epdao.add(ep);

		EndPointEntity epq = epdao.getEntity(ep.getId());
		Assert.assertEquals(ep.getTrunk(), epq.getTrunk());
		Assert.assertNotNull(epq.getApp());

		epq.setState(0);
		epq.setTrunk("2");
		epdao.update(epq);

		EndPointEntity epu = epdao.getEntity(ep.getId());
		Assert.assertEquals(epu.getTrunk(), "2");

		epdao.remove(ep.getId());
		EndPointEntity epd = epdao.getEntity(ep.getId());
		Assert.assertNull(epd);
		
		Pagination<EndPointEntity> p = new Pagination<EndPointEntity>();
		p.setPageIndex(1);
		p.setPageSize(10);
		
		EndPointEntity eee = new EndPointEntity();
		eee.setState(1);
		epdao.getEntities(p, eee);
		
	}

}
