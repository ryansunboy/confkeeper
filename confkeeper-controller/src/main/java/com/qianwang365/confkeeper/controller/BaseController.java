/**
 * MVCBaseController.java
 */
package com.qianwang365.confkeeper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author Yate
 * @date Jan 2, 2014
 * @description TODO
 * @version 1.0
 */
public class BaseController {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 自定义绑定处理
	 * 
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
	}
}
