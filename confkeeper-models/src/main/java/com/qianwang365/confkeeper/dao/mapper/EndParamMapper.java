package com.qianwang365.confkeeper.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qianwang365.confkeeper.dao.IBaseMapperDao;
import com.qianwang365.confkeeper.model.EndParamEntity;

@Repository
@CacheNamespace(size = 512)
public interface EndParamMapper extends IBaseMapperDao<EndParamEntity, Long> {
	List<EndParamEntity> getAppVersionById(
			@Param(value = "appName") final String appName,
			@Param(value = "version") final String version);

	List<EndParamEntity> getEntitiesByEPID(@Param(value = "id") long epId);

	void removeByEPID(@Param(value = "id") long epId);
}
