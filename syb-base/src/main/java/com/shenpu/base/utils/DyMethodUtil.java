package com.shenpu.base.utils;

import java.util.Map;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

/**
 * 动态加载方法
 *
 */
public class DyMethodUtil {

	public static Object invokeMethod(String jexlExp, Map<String, Object> map) {
		JexlEngine jexl = new JexlEngine();
		JexlContext jexlContext = new MapContext();
		Expression expression = jexl.createExpression(jexlExp);
		for (String key : map.keySet()) {
			jexlContext.set(key, map.get(key));
		}
		if (null == expression.evaluate(jexlContext)) {
			return "";
		}
		return expression.evaluate(jexlContext);
	}
}
