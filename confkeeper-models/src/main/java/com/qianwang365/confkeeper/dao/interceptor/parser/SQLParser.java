/**
 * SQLParser.java
 */
package com.qianwang365.confkeeper.dao.interceptor.parser;

<<<<<<< HEAD
=======

>>>>>>> 0ed04841feb04ddb0dfb8f44d2a00f68437ce55b
/**
 * @author Yate
 * @date Nov 28, 2013
 * @description 弱化的ＳＱＬ分析工具
 * @version 1.0
 */
public class SQLParser {

	public static class Location {
		int s = -1;
		int e = -1;

		/**
		 * @return the s
		 */
		public final int getS() {
			return s;
		}

		/**
		 * @return the e
		 */
		public final int getE() {
			return e;
		}

	}

	private SQLParser(String t) {
	}

	public static Location parser(String sql, String token) {
		int pos = -1;
		int make = -1;
		boolean balance = false;

		Location l = new Location();
		for (;;) {
			char c = charAt(sql, ++pos);
<<<<<<< HEAD
			if ((c == token.charAt(0) || c == token.charAt(0) + 32)
					&& pos - 1 == make) {
=======
			if (c == token.charAt(0) && pos - 1 == make) {
>>>>>>> 0ed04841feb04ddb0dfb8f44d2a00f68437ce55b
				l.s = pos;
			}
			if (c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == '\f'
					|| c == '\b') {
				make = pos;
				if (!balance && l.s > -1
						&& sql.substring(l.s, pos).matches("(?i)" + token)) {
					l.e = pos;
					return l;
				}
			}
			if (c == '(') {
				balance = true;
			}
			if (c == ')') {
				balance = false;
			}
		}
	}

	protected static char charAt(String sql, int index) {
		if (index >= sql.length()) {
			return 0x1A;
		}

		return sql.charAt(index);
	}
}
