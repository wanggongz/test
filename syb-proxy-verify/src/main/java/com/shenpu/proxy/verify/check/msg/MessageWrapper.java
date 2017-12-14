package com.shenpu.proxy.verify.check.msg;

import java.util.ArrayList;
import java.util.List;

/**
 * 规则校验返回消息封装类
 * 
 * @author jetty
 * 
 */
public class MessageWrapper {
	/** 规则校验消息列表 */
	private List<Message> msgList;

	public List<Message> getMsgList() {
		return msgList;
	}

	public void setMsgList(List<Message> msgList) {
		this.msgList = msgList;
	}

	/**
	 * 是否校验通过
	 * 
	 * @return
	 */
	public boolean isValid() {
		if (msgList == null || msgList.isEmpty()) {
			return true;
		} else {
			for (Message msg : msgList) {
				if (msg.isValid()) {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * 获取违反的投保规则描述
	 * 
	 * @return
	 */
	public List<String> getInvalidMessages() {
		if (isValid()) {
			return null;
		}
		if (msgList == null || msgList.isEmpty()) {
			return null;
		}
		List<String> invalidMessageList = new ArrayList<String>();
		for (Message msg : msgList) {
			if (msg.isValid()) {
				invalidMessageList.add(msg.getMsg());
			}
		}
		return invalidMessageList;
	}
}
