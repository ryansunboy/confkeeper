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

import com.qianwang365.confkeeper.dao.mapper.AppMapper;
import com.qianwang365.confkeeper.dao.mapper.EndParamMapper;
import com.qianwang365.confkeeper.dao.mapper.EndPointMapper;
import com.qianwang365.confkeeper.model.AppEntity;
import com.qianwang365.confkeeper.model.EndParamEntity;
import com.qianwang365.confkeeper.model.EndPointEntity;

/**
 * @author Yate
 * @Date 2012-5-11
 * @description 详细说明
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring/app-config.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEndParamDao {
	@Resource
	EndParamMapper eppdao;

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
		epdao.add(ep);

		List<EndParamEntity> eps = new ArrayList<EndParamEntity>();
		EndParamEntity epe;
		EndPointEntity v = new EndPointEntity();
		for (int i = 0; i < 5; i++) {
			epe = new EndParamEntity();
			v = new EndPointEntity();
			v.setId(ep.getId());
			epe.setEndpoint(v);
			epe.setType(0);
			epe.setKey("db" + i);
			epe.setValue("dbv" + i);
			eps.add(epe);
		}
		eppdao.batchAdd(eps);

		EndParamEntity epe1 = new EndParamEntity();
		v = new EndPointEntity();
		v.setId(ep.getId());
		epe1.setEndpoint(v);
		epe1.setType(1);
		epe1.setKey("db_test");
		epe1.setValue("dbv_test");
		eppdao.add(epe1);

		EndParamEntity epeq = eppdao.getEntity(epe1.getId());
		Assert.assertEquals(epeq.getValue(), epe1.getValue());

		epeq.setValue("hahahaha");
		eppdao.update(epeq);
		EndParamEntity epeu = eppdao.getEntity(epeq.getId());
		Assert.assertEquals(epeu.getValue(), epeq.getValue());

		eppdao.remove(epe1.getId());
		EndParamEntity epd = eppdao.getEntity(epe1.getId());
		Assert.assertNull(epd);
	}

}
