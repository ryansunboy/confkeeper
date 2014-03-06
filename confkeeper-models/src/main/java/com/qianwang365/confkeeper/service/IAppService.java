package com.qianwang365.confkeeper.service;

import com.qianwang365.confkeeper.model.AppEntity;
import com.qianwang365.confkeeper.utils.Pagination;

public interface IAppService extends IBaseService<AppEntity, Long> {
	Pagination<AppEntity> getExEntities(int page, int rows,
			final AppEntity e);
}
