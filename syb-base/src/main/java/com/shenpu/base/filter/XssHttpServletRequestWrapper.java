package com.shenpu.base.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 过滤输入内容中的的特殊符号，防御跨站脚本攻击.<br/>
 * 对每个post请求的参数过滤一些关键字，替换成安全的，例如：< > ' " \ / # &
 * 方法是实现一个自定义的HttpServletRequestWrapper，然后在Filter里面调用它，替换掉getParameter函数.<br/>
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}

	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	/**
	 * 对单一参数值进行过滤.
	 */
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		return cleanXSS(value);
	}

	public String getHeader(String name) {
		String value = super.getHeader(name);
		return cleanXSS(value);
	}

	/**
	 * HTML过滤危险内容.
	 */
	private String cleanXSS(String value) {

		if (StringUtils.isBlank(value)) {
			return null;
		}
		// 最宽松的一个过滤方法。不要对<,>,',"进行编码，避免长文本显示出问题
		// String res = Jsoup.clean(value, Whitelist.relaxed());
		String escapedHtml = StringEscapeUtils.escapeHtml4(value);

		return escapedHtml;
	}

}