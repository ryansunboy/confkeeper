package com.qianwang365.confkeeper.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qianwang365.confkeeper.model.EndParamEntity;

public interface IEndParamService extends IBaseService<EndParamEntity, Long> {

	List<EndParamEntity> getAppVersionById(
			@Param(value = "appName") final String appName,
			@Param(value = "version") final String version);

	void txUpdate(long epId, List<EndParamEntity> lp);
}
