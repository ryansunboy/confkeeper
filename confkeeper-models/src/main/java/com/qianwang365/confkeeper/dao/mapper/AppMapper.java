package com.qianwang365.confkeeper.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qianwang365.confkeeper.dao.IBaseMapperDao;
import com.qianwang365.confkeeper.model.AppEntity;
import com.qianwang365.confkeeper.utils.Pagination;

@Repository
@CacheNamespace(size = 512)
public interface AppMapper extends IBaseMapperDao<AppEntity, Long> {
	AppEntity getByEndPointId(@Param(value = "id") long id);

	List<AppEntity> getExEntities(Pagination<AppEntity> page,
			@Param(value = "e") final AppEntity e);

}
