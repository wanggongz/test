package com.shenpu.proxy.pay.domain;

import java.io.Serializable;

public class Order implements Serializable {

	private static final long serialVersionUID = 2304923286304445077L;

	private String body;

	private String payplatformname;

	private String paytype;

	private String totalfee;

	private String orderno;

	private String notify_url;

	private String pay_success_url;

	private String nonce_str;
	
	private String sign;
	
	private String status;
	
	private String remark;
	
	private String cnlUserCode;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTotalfee() {
		return totalfee;
	}

	public void setTotalfee(String totalfee) {
		this.totalfee = totalfee;
	}

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPayplatformname() {
		return payplatformname;
	}

	public void setPayplatformname(String payplatformname) {
		this.payplatformname = payplatformname;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getPay_success_url() {
		return pay_success_url;
	}

	public void setPay_success_url(String pay_success_url) {
		this.pay_success_url = pay_success_url;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getCnlUserCode() {
		return cnlUserCode;
	}

	public void setCnlUserCode(String cnlUserCode) {
		this.cnlUserCode = cnlUserCode;
	}

}