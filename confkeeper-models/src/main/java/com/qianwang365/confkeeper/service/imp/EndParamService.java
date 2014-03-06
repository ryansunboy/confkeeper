package com.qianwang365.confkeeper.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qianwang365.confkeeper.dao.IBaseMapperDao;
import com.qianwang365.confkeeper.dao.mapper.EndParamMapper;
import com.qianwang365.confkeeper.model.EndParamEntity;
import com.qianwang365.confkeeper.service.IEndParamService;

@Service
public class EndParamService extends BaseService<EndParamEntity, Long>
		implements IEndParamService {
	@Resource
	private EndParamMapper endParamMapper;

	@Override
	protected IBaseMapperDao<EndParamEntity, Long> getMapperDao() {
		return this.endParamMapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianwang365.confkeeper.service.IEndParamService#getAppVersionById
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public List<EndParamEntity> getAppVersionById(String appName, String version) {
		return this.endParamMapper.getAppVersionById(appName, version);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qianwang365.confkeeper.service.IEndParamService#txUpdate(long,
	 * java.util.List)
	 */
	@Transactional
	@Override
	public void txUpdate(long epId, List<EndParamEntity> lp) {
		this.endParamMapper.removeByEPID(epId);
		this.endParamMapper.batchAdd(lp);
	}
}
