package com.shenpu.proxy.pay.domain;

import java.io.Serializable;

public class Refund implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String refundId;

    private String orderNo;

    private String applyNo;

    private String refundMoney;

    private String reqMsg;

    private String rspMsg;

    private Integer refundState;

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId == null ? null : refundId.trim();
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

    public String getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(String refundMoney) {
        this.refundMoney = refundMoney == null ? null : refundMoney.trim();
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

    public Integer getRefundState() {
        return refundState;
    }

    public void setRefundState(Integer refundState) {
        this.refundState = refundState;
    }

	@Override
	public String toString() {
		return "Refund [refundId=" + refundId + ", orderNo=" + orderNo + ", applyNo=" + applyNo + ", refundMoney="
				+ refundMoney + ", reqMsg=" + reqMsg + ", rspMsg=" + rspMsg + ", refundState=" + refundState + "]";
	}
    
    
}