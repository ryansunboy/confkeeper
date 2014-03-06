/**
 * TestString.java
 */
package com.common;

import java.text.MessageFormat;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Yate
 * @date Jan 15, 2014
 * @description TODO
 * @version 1.0
 */
public class TestString {

	@Test
	public void testJSON() {
//		String test = "'{'\"code\":\"{1}\"'}'";
//		String v = MessageFormat.format(test,"200");
//		JSONObject json = JSONObject.parseObject(v);
//		Assert.assertNotNull(json);
		
		String MATCH_FORMAT = "\\[(\\d+)\\](.+)=(.+)";
		String s1 = "[0]user_db=mysql";
		String s2 = "[0]user_db = mysql";
		String s3 = "[1]com.qianwang365.confkeeper.client.utils.TestBean.setTest1(String)=yate";
		String s4 = "[1]com.qianwang365.confkeeper.client.utils.TestBean.setTest1(String) = yate";
		
		Pattern p = java.util.regex.Pattern.compile(MATCH_FORMAT);
		java.util.regex.Matcher m = p.matcher(s4);
		
		if(m.matches()){
			System.out.println(m.group(0));
			System.out.println(m.group(1));
			System.out.println(m.group(2));
			System.out.println(m.group(3));
		}
				
	}
}
