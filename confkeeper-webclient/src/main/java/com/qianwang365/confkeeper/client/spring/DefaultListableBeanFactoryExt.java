/**
 * DefaultListableBeanFactoryExt.java
 */
package com.qianwang365.confkeeper.client.spring;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.CollectionFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import com.qianwang365.confkeeper.client.spring.annotation.OverrideExsitsBean;

/**
 * @author Yate
 * @date Dec 25, 2013
 * @description TODO
 * @version 1.0
 */
public class DefaultListableBeanFactoryExt extends DefaultListableBeanFactory {
	protected static final Logger log = LoggerFactory
			.getLogger(DefaultListableBeanFactoryExt.class);

	public void initRedirectMsg(String[] packagesToScan) {
		if (packagesToScan == null)
			packagesToScan = new String[] { "com.qianwang365.confkeeper.client.spring" };

		if (packagesToScan != null) {
			for (String strPackage : packagesToScan) {
				ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
				String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
						+ ClassUtils.convertClassNameToResourcePath(strPackage
								.trim());
				pattern += (pattern.charAt(pattern.length() - 1) == '/' ? "**/*.class"
						: "/**/*.class");

				try {
					Resource[] resources = resourcePatternResolver
							.getResources(pattern);
					MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(
							resourcePatternResolver);

					for (Resource resource : resources) {
						if (resource.isReadable()) {
							MetadataReader reader = readerFactory
									.getMetadataReader(resource);
							String className = reader.getClassMetadata()
									.getClassName();
							Class beanClass = resourcePatternResolver
									.getClassLoader().loadClass(className);
							Integer overrideValue = getOverrideValue(beanClass);

							if (overrideValue != null) {
								Class originalClass = beanClass.getSuperclass();

								// while(getOverrideValue(originalClass) !=
								// null) {
								// originalClass =
								// originalClass.getSuperclass();
								// }

								String originalClassName = originalClass
										.getName();
								if (mapRedirectBeanClass.get(originalClassName) == null
										|| overrideValue > (Integer) mapRedirectBeanClass
												.get(originalClassName)[1]) {
									mapRedirectBeanClass.put(originalClassName,
											new Object[] { className,
													overrideValue });
								}
							}
						}
					}
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}
		}
	}

	@Override
	public Object autowire(Class beanClass, int autowireMode,
			boolean dependencyCheck) throws BeansException {
		return super.autowire(redirectBeanClass(beanClass), autowireMode,
				dependencyCheck);
	}

	@Override
	public Object createBean(Class beanClass) throws BeansException {
		return super.createBean(redirectBeanClass(beanClass));
	}

	@Override
	public Object createBean(Class beanClass, int autowireMode,
			boolean dependencyCheck) throws BeansException {
		return super.createBean(redirectBeanClass(beanClass), autowireMode,
				dependencyCheck);
	}

	@Override
	protected RootBeanDefinition getMergedBeanDefinition(String beanName,
			BeanDefinition bd, BeanDefinition containingBd)
			throws BeanDefinitionStoreException {
		RootBeanDefinition mbd = super.getMergedBeanDefinition(beanName, bd,
				containingBd);

		try {
			mbd.setBeanClass(redirectBeanClass(mbd.getBeanClass()));
		} catch (IllegalStateException e) {
			try {
				mbd.setBeanClassName(getRedirectBeanName(mbd.getBeanClassName()));
			} catch (Exception ee) {
			}
		}

		synchronized (this.mergedBeanDefinitions) {
			if (containingBd == null && isCacheBeanMetadata()
					&& isBeanEligibleForMetadataCaching(beanName)) {
				this.mergedBeanDefinitions.put(beanName, mbd);
			}
		}

		return mbd;
	}

	@Override
	protected RootBeanDefinition getMergedLocalBeanDefinition(String beanName)
			throws BeansException {
		RootBeanDefinition mbd = (RootBeanDefinition) this.mergedBeanDefinitions
				.get(beanName);
		if (mbd != null) {
			return mbd;
		}
		return getMergedBeanDefinition(beanName, getBeanDefinition(beanName));
	}

	@Override
	protected void clearMergedBeanDefinition(String beanName) {
		this.mergedBeanDefinitions.remove(beanName);
	}

	@Override
	protected Map findAutowireCandidates(String beanName, Class requiredType,
			DependencyDescriptor descriptor) {
		Map mapResut = super.findAutowireCandidates(beanName, requiredType,
				descriptor);

		if (mapResut.size() > 1) {
			Object[] overrideMsg = mapRedirectBeanClass.get(requiredType
					.getName());

			if (overrideMsg != null) {
				String strOverrideBeanFullName = (String) overrideMsg[0];
				String strOverrideBeanName = strOverrideBeanFullName.substring(
						strOverrideBeanFullName.lastIndexOf(".") + 1)
						.toLowerCase();

				for (Iterator it = mapResut.keySet().iterator(); it.hasNext();) {
					String candidateName = (String) it.next();

					if (candidateName.toLowerCase().contains(
							strOverrideBeanName)) {
						it.remove();
					}
				}
			}
		}

		return mapResut;
	}

	public DefaultListableBeanFactoryExt(BeanFactory parentBeanFactory) {
		super(parentBeanFactory);
	}

	protected Class redirectBeanClass(Class beanClass) {
		String originalBeanName = beanClass.getName();
		String redirectBeanName = getRedirectBeanName(originalBeanName);

		try {
			return originalBeanName.equals(redirectBeanName) ? beanClass
					: Class.forName(redirectBeanName);
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(), e);
		}

		return beanClass;
	}

	protected String getRedirectBeanName(String originalBeanName) {
		Object[] redirectMsg = mapRedirectBeanClass.get(originalBeanName);
		return redirectMsg != null ? (String) redirectMsg[0] : originalBeanName;
	}

	private Integer getOverrideValue(Class beanClass) {
		if (beanClass != null) {
			OverrideExsitsBean overrideExsitsBean = (OverrideExsitsBean) beanClass
					.getAnnotation(OverrideExsitsBean.class);

			if (overrideExsitsBean != null) {
				return overrideExsitsBean.value();
			}
		}

		return null;
	}

	protected Map<String, Object[]> mapRedirectBeanClass = new HashMap<String, Object[]>();
	private final Map mergedBeanDefinitions = CollectionFactory
			.createConcurrentMapIfPossible(16);
}
