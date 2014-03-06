/**
 * TestAMQP.java
 */
package server;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qianwang365.confkeeper.server.ConfKeeperServer;
import com.qianwang365.confkeeper.server.amqp.JSONProducer;
import com.qianwang365.confkeeper.server.utils.Constants;
import com.qianwang365.confkeeper.server.utils.PropertyUtils;

/**
 * @author Yate
 * @date Jan 14, 2014
 * @description TODO
 * @version 1.0
 */
public class TestP {
	static {
		final String confPath = ConfKeeperServer.class.getClassLoader()
				.getResource("conf").getPath();
		PropertyUtils.getInstance().loads(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return FilenameUtils.getExtension(pathname.getName())
						.equalsIgnoreCase("conf") ? true : false;
			}
		}, confPath);
	}

	@Test
	public void testAdd() {
		try {
			JSONProducer p = new JSONProducer("ckns", "ckns");

			JSONObject json = new JSONObject();
			json.put(Constants.MSG_EVENT, "SET");

			JSONArray arr = new JSONArray();
			json.put(Constants.MSG_CONTENT, arr);

			JSONObject test1 = JSONObject
					.parseObject("{\"key\":\"com.qianwang365.confkeeper.client.utils.TestBean.setTest1(String)\",\"value\":\"yatetest1\"}");
			JSONObject test2 = JSONObject
					.parseObject("{\"key\":\"com.qianwang365.confkeeper.client.utils.TestBean.setTest2(long)\",\"value\":\"2\"}");
			JSONObject test3 = JSONObject
					.parseObject("{\"key\":\"com.qianwang365.confkeeper.client.utils.TestBean.setTest3(Long)\",\"value\":\"3\"}");

			arr.add(test1);
			arr.add(test2);
			arr.add(test3);

			p.sendMessage(json, "");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
