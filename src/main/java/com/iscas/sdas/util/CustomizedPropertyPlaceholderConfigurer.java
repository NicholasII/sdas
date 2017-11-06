// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) braces fieldsfirst ansi nonlb space 
// Source File Name:   CustomizedPropertyPlaceholderConfigurer.java

package com.iscas.sdas.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class CustomizedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private static Map ctxPropertiesMap = new HashMap();

	public CustomizedPropertyPlaceholderConfigurer() {
	}

	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		String keyStr;
		String value;
		for (Iterator iterator = props.keySet().iterator(); iterator.hasNext(); ctxPropertiesMap.put(keyStr, value)) {
			Object key = iterator.next();
			keyStr = key.toString();
			value = props.getProperty(keyStr);
		}

	}

	public static Object getContextProperty(String name) {
		return ctxPropertiesMap.get(name);
	}

}
