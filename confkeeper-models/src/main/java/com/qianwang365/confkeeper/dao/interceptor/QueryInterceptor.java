package com.qianwang365.confkeeper.dao.interceptor;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qianwang365.confkeeper.dao.interceptor.dialect.IDBDialect;

/**
 * @author Yate
 * @date 2013年9月24日
 * @description TODO
 * @version 1.0
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "query", args = {
		Statement.class, ResultHandler.class }) })
public class QueryInterceptor implements Interceptor {
	private final static Logger LOG = LoggerFactory
			.getLogger(QueryInterceptor.class);

	private IDBDialect dialect;
	private Properties properties;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin
	 * .Invocation)
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		if (this.dialect == null) {
			return invocation.proceed();
		}

		return invocation.proceed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
	 */
	@SuppressWarnings("unchecked")
	public void setProperties(Properties properties) {
		this.properties = properties;
		if (this.properties.containsKey("DBDialect")) {
			try {
				Class<IDBDialect> target = (Class<IDBDialect>) Class
						.forName(properties.getProperty("DBDialect"));
				this.dialect = target.newInstance();
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}
}
