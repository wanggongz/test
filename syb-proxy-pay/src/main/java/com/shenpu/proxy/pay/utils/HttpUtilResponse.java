package com.shenpu.proxy.pay.utils;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;

import com.google.common.base.Throwables;

public class HttpUtilResponse {
	private final static String DEFAULT_CHARSET = "UTF-8";
	
	private int statuscode;
	private byte[] data;
	private Header[] headers;
	
	public int getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}
	public String getContent() {
		return this.getContent(DEFAULT_CHARSET);
	}
	public String getContent(String charset) {
		String content;
		try {
			content = new String(data,charset);
		} catch (UnsupportedEncodingException e) {
			throw Throwables.propagate(e);
		}
		return content;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public Header[] getHeaders() {
		return headers;
	}
	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}
	public String getFirstHeaderValue(String name){
		for(Header header:headers){
			if(header.getName().equals(name))
				return header.getValue();
		}
		return null;
	}
}
