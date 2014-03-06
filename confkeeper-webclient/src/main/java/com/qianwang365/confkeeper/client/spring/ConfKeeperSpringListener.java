/**
 * ConfKeeperSpringListener.java
 */
package com.qianwang365.confkeeper.client.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * @author Yate
 * @date Dec 25, 2013
 * @description TODO
 * @version 1.0
 */
public class ConfKeeperSpringListener extends ContextLoaderListener {
	protected static final Logger log = LoggerFactory
			.getLogger(ConfKeeperSpringListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		String packages = event.getServletContext().getInitParameter(
				CONFIG_PACKAGES_PARAM);
		packagesToScan = packages != null ? packages.split(",")
				: new String[] { "com.qianwang365.confkeeper.client.spring" };
		super.contextInitialized(event);
		WebApplicationContextUtils.getWebApplicationContext(event
				.getServletContext());
	}

	@Override
	protected ContextLoader createContextLoader() {
		ContextLoader contextLoader = new ContextLoader() {
			@Override
			protected Class determineContextClass(ServletContext servletContext)
					throws ApplicationContextException {
				Class contextClass = super
						.determineContextClass(servletContext);

				if (contextClass == XmlWebApplicationContext.class) {
					return XmlWebApplicationContextExt.class;
				}

				return contextClass;
			}

			@Override
			protected WebApplicationContext createWebApplicationContext(
					ServletContext servletContext, ApplicationContext parent)
					throws BeansException {

				Class contextClass = determineContextClass(servletContext);
				if (!ConfigurableWebApplicationContext.class
						.isAssignableFrom(contextClass)) {
					throw new ApplicationContextException(
							"Custom context class ["
									+ contextClass.getName()
									+ "] is not of type ["
									+ ConfigurableWebApplicationContext.class
											.getName() + "]");
				}

				ConfigurableWebApplicationContext wac = (ConfigurableWebApplicationContext) BeanUtils
						.instantiateClass(contextClass);

				if (wac instanceof XmlWebApplicationContextExt) {
					((XmlWebApplicationContextExt) wac)
							.setPackagesToScan(packagesToScan);
				}

				wac.setParent(parent);
				wac.setServletContext(servletContext);
				wac.setConfigLocation(servletContext
						.getInitParameter(CONFIG_LOCATION_PARAM));
				customizeContext(servletContext, wac);
				wac.refresh();

				return wac;
			}
		};

		return contextLoader;
	}

	protected String[] packagesToScan;
	protected static final String CONFIG_PACKAGES_PARAM = "packagesToScanForOverrideExsitsBean";
}

class XmlWebApplicationContextExt extends XmlWebApplicationContext {
	public void setPackagesToScan(String[] packagesToScan) {
		if (packagesToScan == null)
			this.packagesToScan = new String[] { "com.qianwang365.confkeeper.client.spring" };
		else
			this.packagesToScan = packagesToScan;
	}

	@Override
	protected DefaultListableBeanFactory createBeanFactory() {
		DefaultListableBeanFactoryExt defaultListableBeanFactoryExt = new DefaultListableBeanFactoryExt(
				getInternalParentBeanFactory());
		defaultListableBeanFactoryExt.initRedirectMsg(packagesToScan);
		return defaultListableBeanFactoryExt;
	}

	protected String[] packagesToScan;
}
