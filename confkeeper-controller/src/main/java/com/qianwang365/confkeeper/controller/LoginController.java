/**
 * LoginController.java
 */
package com.qianwang365.confkeeper.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qianwang365.confkeeper.service.IUserService;

/**
 * @author Yate
 * @date 2013年9月18日
 * @version 1.0
 */
@Controller
public class LoginController extends BaseController {

	@Resource
	private IUserService userService;

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String index() {
		return "index";
	}
}
