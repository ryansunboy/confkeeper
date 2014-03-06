package com.qianwang365.confkeeper.client.utils;

import java.nio.charset.Charset;

/**
 * Constants.java
 */

/**
 * @author Yate
 * @date 2013年10月12日
 * @description TODO
 * @version 1.0
 */
public final class Constants {
	public static final String CONF_CLIENT_VER = "0.1";

	public static final String CHARACTOR_CODE = "UTF-8";

	public static final long DEFAULT_TIMEOUT = 10 * 1000;

	public static final Charset CHARSET_UTF8 = Charset.forName(CHARACTOR_CODE);

	public static final String BOOTSTRAP_MSG = "bootstrap";
	public static final String MSG_EVENT = "event";
	public static final String MSG_CONTENT = "content";

	public static final String BYTES_FILTER = "bytes_filter";
	public static final String LOG_FILTER = "logs_filter";
	public static final String CODEC_FILTER = "codec_filter";
}
