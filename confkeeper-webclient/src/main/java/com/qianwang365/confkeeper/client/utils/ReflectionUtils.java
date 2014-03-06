package com.qianwang365.confkeeper.client.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yate
 * @date 2013年9月23日
 * @description TODO
 * @version 1.0
 */
public final class ReflectionUtils {
	private static final Logger logger = LoggerFactory
			.getLogger(ReflectionUtils.class);

	public static Class<?> getSuperClassGenricType(final Class<?> clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	public static Class<?> getSuperClassGenricType(final Class<?> clazz,
			final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName()
					+ "'s superclass not ParameterizedType");
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of "
					+ clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName()
					+ " not set the actual class on superclass generic parameter");
			return Object.class;
		}
		return (Class<?>) params[index];
	}

	public static void execute(Object target, Class<?> clazz, String method,
			String param, String v) throws ClassNotFoundException,
			NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Class<?>[] clazzs = new Class[param.split(",").length];
		Object[] values = new Object[param.split(",").length];
		String[] ps = param.split(",");
		String[] vs = v.split(",");
		for (int i = 0; i < ps.length; i++) {
			switch (ps[i]) {
				case "String":
					clazzs[i] = java.lang.String.class;
					values[i] = vs[i].toString();
					break;
				case "long":
					clazzs[i] = long.class;
					values[i] = Long.parseLong(vs[i]);
					break;
				case "Long":
					clazzs[i] = java.lang.Long.class;
					values[i] = Long.parseLong(vs[i]);
					break;
				case "int":
					clazzs[i] = int.class;
					values[i] = Integer.parseInt(vs[i]);
					break;
				case "Integer":
					clazzs[i] = Integer.class;
					values[i] = Integer.parseInt(vs[i]);
					break;
				case "byte":
					clazzs[i] = byte.class;
					values[i] = Byte.parseByte(vs[i]);
					break;
				case "Byte":
					clazzs[i] = Byte.class;
					values[i] = Byte.parseByte(vs[i]);
					break;
				case "short":
					clazzs[i] = short.class;
					values[i] = Short.parseShort(vs[i]);
					break;
				case "Short":
					clazzs[i] = Short.class;
					values[i] = Short.parseShort(vs[i]);
					break;
				case "float":
					clazzs[i] = float.class;
					values[i] =Float.parseFloat(vs[i]);
					break;
				case "Float":
					clazzs[i] = Float.class;
					values[i] =Float.parseFloat(vs[i]);
					break;
				case "double":
					clazzs[i] = double.class;
					values[i] = Double.parseDouble(vs[i]);
					break;
				case "Double":
					clazzs[i] = Double.class;
					values[i] = Double.parseDouble(vs[i]);
					break;
				default:
					break;
			}
		}
		Method m = target.getClass().getMethod(method, clazzs);
		if (m != null) {
			m.invoke(target, values);
		}
	}
}
