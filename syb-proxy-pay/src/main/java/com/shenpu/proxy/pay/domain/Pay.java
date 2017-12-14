package com.shenpu.proxy.pay.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Pay implements Serializable{
	private static final long serialVersionUID = 5478581611918663494L;
	private String payId;
    private BigDecimal payMoney;
    private String payState;
    private Date payTime;
    private String orderNo;
    private String payChannel;
    private Date insertTime;
    private Date updateTime;
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public BigDecimal getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}
	public String getPayState() {
		return payState;
	}
	public void setPayState(String payState) {
		this.payState = payState;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}