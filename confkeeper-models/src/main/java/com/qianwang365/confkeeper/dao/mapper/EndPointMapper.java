package com.qianwang365.confkeeper.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

import com.qianwang365.confkeeper.dao.IBaseMapperDao;
import com.qianwang365.confkeeper.model.EndPointEntity;

@Repository
@CacheNamespace(size = 512)
public interface EndPointMapper extends IBaseMapperDao<EndPointEntity, Long> {
	List<EndPointEntity> getEntitiesByAID(long id);
}
