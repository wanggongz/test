package com.shenpu.proxy.access.domain;

import java.io.Serializable;

public class Order implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderId;

    private String orderNo;

    private String applyNo;

    private String payMoney;

    private String platformCode;

    private String reqMsg;

    private String rspMsg;

    private String payState;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo == null ? null : applyNo.trim();
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney == null ? null : payMoney.trim();
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode == null ? null : platformCode.trim();
    }

    public String getReqMsg() {
        return reqMsg;
    }

    public void setReqMsg(String reqMsg) {
        this.reqMsg = reqMsg == null ? null : reqMsg.trim();
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg == null ? null : rspMsg.trim();
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState == null ? null : payState.trim();
    }

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderNo=" + orderNo + ", applyNo=" + applyNo + ", payMoney=" + payMoney
				+ ", platformCode=" + platformCode + ", reqMsg=" + reqMsg + ", rspMsg=" + rspMsg + ", payState="
				+ payState + "]";
	}
    
}