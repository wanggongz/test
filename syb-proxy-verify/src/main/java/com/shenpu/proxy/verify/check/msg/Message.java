package com.shenpu.proxy.verify.check.msg;

/**
 * 规则校验返回消息类
 * 如果valid==true，说明满足产品定义中该规则的某个IF条件，表明规则校验不通过，错误提示信息封装到msg属性上
 * 如果valid==false，说明不满足产品定义中该规则的任何一个IF条件，表明规则校验通过
 * @author zhugl
 * 
 */
public class Message {
	/** 提示信息 */
	private String msg;
	/** 类型 */
	private String type;
	/** 是否符合该规则中的条件，默认不符合，即默认通过该条规则*/
	private boolean valid = false;
	/** 是否继续 */
	private boolean continued = true;
	/** 返回值 */
	private String returnValue;

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public boolean isContinued() {
		return continued;
	}

	public void setContinued(boolean continued) {
		this.continued = continued;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
