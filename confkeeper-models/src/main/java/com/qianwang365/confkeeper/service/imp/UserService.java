package com.qianwang365.confkeeper.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qianwang365.confkeeper.dao.IBaseMapperDao;
import com.qianwang365.confkeeper.dao.mapper.UserMapper;
import com.qianwang365.confkeeper.model.UserEntity;
import com.qianwang365.confkeeper.service.IUserService;

@Service
public class UserService extends BaseService<UserEntity, Long> implements
		IUserService {
	@Resource
	private UserMapper userMapper;

	@Override
	protected IBaseMapperDao<UserEntity, Long> getMapperDao() {
		return this.userMapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianwang365.confkeeper.service.IUserService#getByUserName(java.lang
	 * .String)
	 */
	public UserEntity getByUserName(String uname, String passwd) {
		return this.userMapper.getByUserName(uname, passwd);
	}
}
