package com.shenpu.proxy.pay.dto;

public class ResultDataDto {

	private boolean success;
	private String msg;
	public boolean isSuccess() {
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
	
}
