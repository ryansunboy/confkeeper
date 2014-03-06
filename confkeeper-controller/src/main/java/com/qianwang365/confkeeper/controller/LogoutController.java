/**
 * LogoutController.java
 */
package com.qianwang365.confkeeper.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Yate
 * @date Jan 2, 2014
 * @description TODO
 * @version 1.0
 */
@Controller
public class LogoutController extends BaseController {

	@RequestMapping(value = "/logout", method = { RequestMethod.GET })
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}
