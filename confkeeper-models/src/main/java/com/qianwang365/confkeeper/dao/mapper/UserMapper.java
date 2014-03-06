package com.qianwang365.confkeeper.dao.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qianwang365.confkeeper.dao.IBaseMapperDao;
import com.qianwang365.confkeeper.model.UserEntity;

@Repository
@CacheNamespace(size = 512)
public interface UserMapper extends IBaseMapperDao<UserEntity, Long> {
	UserEntity getByUserName(@Param(value = "uname") final String uname,
			@Param(value = "passwd") final String passwd);
}
