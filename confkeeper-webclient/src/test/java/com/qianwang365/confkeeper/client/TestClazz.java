/**
 * TestClazz.java
 */
package com.qianwang365.confkeeper.client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * @author Yate
 * @date Jan 13, 2014
 * @description TODO
 * @version 1.0
 */
public class TestClazz {

	@Test
	public void testCRUD() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException {
//		String t1 = "com.qianwang365.confkeeper.client.utils.TestBean.setTest1(String)";
//		String t1 = "com.qianwang365.confkeeper.client.utils.TestBean.setTest2(Long)";
		String t1 = "com.qianwang365.confkeeper.client.utils.TestBean.setTest3(int)";

		String clazz = t1.substring(0, t1.lastIndexOf("."));
		String method = t1.substring(t1.lastIndexOf(".") + 1,
				t1.lastIndexOf("("));
		String param = t1.substring(t1.lastIndexOf("(") + 1,
				t1.lastIndexOf(")"));
		
		String value = "12";

		Class[] clazzs = new Class[param.split(",").length];
		Object[] values = new Object[param.split(",").length];
		String[] ps = param.split(",");
		String[] vs = value.split(",");
		for (int i = 0; i < ps.length; i++) {
			switch (ps[i].toLowerCase()) {
				case "string":
					clazzs[i] = java.lang.String.class;
					values[i]=vs[i].toString();
					break;

				case "long":
					clazzs[i] = java.lang.Long.class;
					values[i]=Long.parseLong(vs[i]);
					break;

				case "int":
					clazzs[i] = int.class;
					values[i]=Integer.parseInt(vs[i]);
					break;

				default:
					break;
			}
		}

		Class<?> c = Class.forName(clazz);
		Object target = Class.forName(clazz).newInstance();
		
		Method m = target.getClass().getMethod(method, clazzs);
		if (m != null) {
			m.invoke(target, values);
		}

	}
}
