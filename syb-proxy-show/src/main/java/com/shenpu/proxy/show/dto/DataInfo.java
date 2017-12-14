package com.shenpu.proxy.show.dto;

import java.io.Serializable;
/**
 * json相应数据
 */
public class DataInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean success;
	
	private String msg;
	
	private Object response;
	
	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "DataInfo [success=" + success + ", msg=" + msg + ", response=" + response + "]";
	}
}
