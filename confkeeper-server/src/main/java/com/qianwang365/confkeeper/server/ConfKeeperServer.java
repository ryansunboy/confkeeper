/**
 * ConfKeeperServer.java
 */
package com.qianwang365.confkeeper.server;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qianwang365.confkeeper.server.core.IMinaAcceptor.MinaStarterParam;
import com.qianwang365.confkeeper.server.core.impl.MinaAcceptor;
import com.qianwang365.confkeeper.server.utils.Constants;
import com.qianwang365.confkeeper.server.utils.PropertyUtils;

/**
 * @author Yate
 * @date Dec 26, 2013
 * @description TODO
 * @version 1.0
 */
public class ConfKeeperServer {

	private static Logger Log = LoggerFactory.getLogger(ConfKeeperServer.class);

	/**
	 * @description TODO
	 * @param args
	 */
	public static void main(String[] args) {
		final String confPath = ConfKeeperServer.class.getClassLoader()
				.getResource("conf").getPath();
		PropertyUtils.getInstance().loads(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return FilenameUtils.getExtension(pathname.getName())
						.equalsIgnoreCase("conf") ? true : false;
			}
		}, confPath);

		String port = PropertyUtils.getInstance().getValue("server.port");
		String amqp = PropertyUtils.getInstance().getValue("amqp.server");

		Log.info("--------Configution Keeper Server--------");
		Log.info("Version:" + Constants.CONF_SERVER_VER);
		Log.info("Listener port:" + port);
		Log.info("AMQP:" + amqp);
		Log.info("--------Configution Keeper Server--------");

		MinaStarterParam p = new MinaStarterParam(Integer.parseInt(port));
		MinaAcceptor<MinaStarterParam> a = new MinaAcceptor<MinaStarterParam>(p);
		try {
			a.start();
		} catch (IOException e) {
			Log.error(e.getMessage(), e);
			System.exit(0);
		}
	}
}
