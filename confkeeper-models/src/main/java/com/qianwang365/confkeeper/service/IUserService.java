package com.qianwang365.confkeeper.service;

import com.qianwang365.confkeeper.model.UserEntity;

public interface IUserService extends IBaseService<UserEntity, Long> {
	UserEntity getByUserName(final String uname,final String passwd);
}
