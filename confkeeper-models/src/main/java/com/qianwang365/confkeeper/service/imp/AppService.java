package com.qianwang365.confkeeper.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qianwang365.confkeeper.dao.IBaseMapperDao;
import com.qianwang365.confkeeper.dao.mapper.AppMapper;
import com.qianwang365.confkeeper.model.AppEntity;
import com.qianwang365.confkeeper.service.IAppService;
import com.qianwang365.confkeeper.utils.Pagination;

@Service
public class AppService extends BaseService<AppEntity, Long> implements
		IAppService {
	@Resource
	private AppMapper appMapper;

	@Override
	protected IBaseMapperDao<AppEntity, Long> getMapperDao() {
		return this.appMapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianwang365.confkeeper.service.IAppService#getExEntities(com.qianwang365
	 * .confkeeper.utils.Pagination, com.qianwang365.confkeeper.model.AppEntity)
	 */
	@Override
	public Pagination<AppEntity> getExEntities(int page, int rows, AppEntity e) {
		Pagination<AppEntity> result = new Pagination<AppEntity>(page, rows);
		result.setRows(this.appMapper.getExEntities(result, e));
		return result;
	}
}
