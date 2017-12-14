package com.shenpu.proxy.verify.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * 基于DOM和XPATH解析XML处理基类
 * 
 * @author jetty
 * 
 */
public class XMLUtil {

	private Document document = null;
	private XPath xpath = null;

	/**
	 * 根据待解析的xml文件路径初始化XmlHelper
	 * 
	 * @param xmlPath
	 */
	public XMLUtil(String xmlPath) throws Exception {
		try {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true);
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			InputSource ins = new InputSource(new FileInputStream(new File(xmlPath)));
			ins.setEncoding("utf-8");
			document = builder.parse(ins);
			XPathFactory factory = XPathFactory.newInstance();
			xpath = factory.newXPath();
		} catch (Exception e) {
			throw e;
		}
	}

	public XMLUtil(InputStream is) throws Exception {
		try {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true);
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			InputSource ins = new InputSource(is);
			ins.setEncoding("utf-8");
			document = builder.parse(ins);
			XPathFactory factory = XPathFactory.newInstance();
			xpath = factory.newXPath();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 根据节点表达式获取xml中的节点信息
	 * 
	 * @param express
	 * @return
	 * @throws Exception
	 */
	public NodeList getNodes(String express) throws Exception {
		XPathExpression expr = xpath.compile(express);
		Object result = expr.evaluate(document, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		return nodes;
	}
}
