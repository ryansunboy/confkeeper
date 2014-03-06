/**
 * TestServer.java
 */
package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Yate
 * @date Dec 24, 2013
 * @description TODO
 * @version 1.0
 */
public class TestServer {

	public static void main(String[] args) {
		IoAcceptor acceptor = new NioSocketAcceptor();

		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8"))));

		acceptor.setHandler(new IoHandlerAdapter() {

			@Override
			public void sessionCreated(IoSession session) throws Exception {
				System.out.println("sessionCreated"
						+ System.currentTimeMillis());
			}

			@Override
			public void sessionOpened(IoSession session) throws Exception {
				System.out.println("sessionOpened" + System.currentTimeMillis());
			}

			@Override
			public void sessionClosed(IoSession session) throws Exception {
				System.out.println("sessionClosed" + System.currentTimeMillis());
			}

			@Override
			public void sessionIdle(IoSession session, IdleStatus status)
					throws Exception {
				System.out.println("sessionIdle" + System.currentTimeMillis());
			}

			@Override
			public void exceptionCaught(IoSession session, Throwable cause)
					throws Exception {
				System.out.println("exceptionCaught"
						+ System.currentTimeMillis());
				session.close(true);
			}

			@Override
			public void messageReceived(IoSession session, Object message)
					throws Exception {
				System.out.println("messageReceived："
						+ System.currentTimeMillis());
				JSONObject data = JSON.parseObject(message.toString());
				data.remove("content");

				JSONObject content = new JSONObject();
				content.put("user_db", "mysql");
				content.put("user_db_driver", "com.mysql.jdbc.Driver");
				content.put("user_db_uri", "jdbc:mysql://127.0.0.1:3306/confsvr?useUnicode=true&amp;characterEncoding=utf8");
				content.put("user_db_username", "root");
				content.put("user_db_password", "root123");
				content.put("min_connections", "5");
				content.put("max_connections", "20");
				data.put("content", content);

				session.write(data.toJSONString());
			}

			@Override
			public void messageSent(IoSession session, Object message)
					throws Exception {
				System.out.println("messageSent" + System.currentTimeMillis());
			}
		});

		try {
			acceptor.bind(new InetSocketAddress(9898));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
