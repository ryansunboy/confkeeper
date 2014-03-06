/**
 * SpringConfkeeperClient.java
 */
package com.qianwang365.confkeeper.client.spring;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qianwang365.confkeeper.client.conf.impl.ClientConfig;
import com.qianwang365.confkeeper.client.content.event.ContentEvent;
import com.qianwang365.confkeeper.client.content.event.ContentEventType;
import com.qianwang365.confkeeper.client.content.event.ContentListener;
import com.qianwang365.confkeeper.client.core.IMinaConnection;
import com.qianwang365.confkeeper.client.core.IMinaConnection.MinaConnParam;
import com.qianwang365.confkeeper.client.exception.FailException;
import com.qianwang365.confkeeper.client.exception.KeyClashException;
import com.qianwang365.confkeeper.client.factory.IConnectorFactory;
import com.qianwang365.confkeeper.client.factory.impl.MinaConnectorFactory;
import com.qianwang365.confkeeper.client.spring.annotation.OverrideExsitsBean;
import com.qianwang365.confkeeper.client.utils.Constants;
import com.qianwang365.confkeeper.client.utils.ReflectionUtils;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description TODO
 * @version 1.0
 */
@OverrideExsitsBean(override = PropertySourcesPlaceholderConfigurer.class)
public class SpringConfkeeperClient extends
		PropertySourcesPlaceholderConfigurer implements
		ContentListener<JSONObject>, ApplicationContextAware {

	protected static final Logger log = LoggerFactory
			.getLogger(SpringConfkeeperClient.class);

	protected IConnectorFactory<IMinaConnection<JSONObject>, MinaConnParam> factory = MinaConnectorFactory
			.getInstance();
	protected MinaConnParam p;
	protected ClientConfig config;
	protected IMinaConnection<JSONObject> conn;

	protected ApplicationContext springCtx;

	protected final MyData initData;

	protected static class MyData {
		final String host;
		final int port;
		final String appName;
		final String appVer;

		public MyData(String host, int port, String appName, String appVer) {
			super();
			this.host = host;
			this.port = port;
			this.appName = appName;
			this.appVer = appVer;
		}
	}

	/**
	 * @param host
	 * @param port
	 * @param version
	 */
	public SpringConfkeeperClient(String host, int port, String appName,
			String appVer) {
		initData = new MyData(host, port, appName, appVer);

		new Thread(new Runnable() {

			@Override
			public void run() {
				log.info("-----------Configution Keeper Client-----------");
				log.info("Version:" + Constants.CONF_CLIENT_VER);
				log.info("Connection addres:" + initData.host);
				log.info("connection port:" + initData.port);
				log.info("-----------Configution Keeper Client-----------");

				SpringConfkeeperClient.this.p = new MinaConnParam(
						initData.host, initData.port);
				JSONObject c = new JSONObject();
				c.put("appName", initData.appName);
				c.put("appVer", initData.appVer);
				SpringConfkeeperClient.this.config = new ClientConfig(
						ContentEventType.GET, c);
				SpringConfkeeperClient.this.conn = SpringConfkeeperClient.this.factory
						.getConnector(p);
				SpringConfkeeperClient.this.conn.init();
				try {
					SpringConfkeeperClient.this.conn.regeditListener(
							SpringConfkeeperClient.class.getName(),
							SpringConfkeeperClient.this);
				} catch (KeyClashException e) {
					log.error(e.getMessage(), e);
				}

				SpringConfkeeperClient.this.conn
						.connection(SpringConfkeeperClient.this.config);
			}
		}).start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qianwang365.confkeeper.client.base.conf.ContentListener#headleEvent
	 * (com.qianwang365.confkeeper.client.base.conf.ContentEvent)
	 */
	@Override
	public void headleEvent(ContentEvent<JSONObject> event)
			throws FailException {
		if (!event.getContent().getString("appName")
				.equalsIgnoreCase(initData.appName)) {
			return;
		}

		if (event.getType() == ContentEventType.GET) {
			JSONArray c = event.getContent()
					.getJSONArray(Constants.MSG_CONTENT);
			Properties p = new Properties();
			JSONObject e = null;
			for (int i = 0; i < c.size(); i++) {
				e = c.getJSONObject(i);
				if (e.getInteger("type") == 0) {
					p.setProperty(e.getString("key"), e.getString("value"));
				}
			}
			super.setProperties(p);
		} else if (event.getType() == ContentEventType.SET) {
			JSONArray c = event.getContent()
					.getJSONArray(Constants.MSG_CONTENT);
			JSONObject e = null;
			for (int i = 0; i < c.size(); i++) {// 动态更新
				e = c.getJSONObject(i);

				if (e.getInteger("type") == 1) {
					String k = e.getString("key");
					String clazz = k.substring(0, k.lastIndexOf("."));
					String method = k.substring(k.lastIndexOf(".") + 1,
							k.lastIndexOf("("));
					String param = k.substring(k.lastIndexOf("(") + 1,
							k.lastIndexOf(")"));
					Class<?> cl;
					try {
						cl = Class.forName(clazz);
						if (cl != null) {
							Object target = this.springCtx.getBean(cl);
							if (target != null) {
								ReflectionUtils.execute(target, cl, method,
										param, e.getString("value"));
							}
						}

					} catch (Exception e1) {
						this.logger
								.error("[SpringConfkeeperClient] server set event failed.",
										e1);
						throw new FailException();
					}
				} else {// 静态更新
					for (Properties p : super.localProperties) {
						if (p.containsKey(e.getString("key"))) {
							p.remove(e.getString("key"));
						}
						p.setProperty(e.getString("key"), e.getString("value"));
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.springCtx = applicationContext;
	}
}
