package com.qianwang365.confkeeper.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qianwang365.confkeeper.dao.IBaseMapperDao;
import com.qianwang365.confkeeper.dao.mapper.EndPointMapper;
import com.qianwang365.confkeeper.model.EndPointEntity;
import com.qianwang365.confkeeper.service.IEndPointService;

@Service
public class EndPointService extends BaseService<EndPointEntity, Long>
		implements IEndPointService {
	@Resource
	private EndPointMapper endPointMapper;

	@Override
	protected IBaseMapperDao<EndPointEntity, Long> getMapperDao() {
		return this.endPointMapper;
	}
}
