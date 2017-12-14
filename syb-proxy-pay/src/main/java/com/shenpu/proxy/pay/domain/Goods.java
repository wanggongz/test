package com.shenpu.proxy.pay.domain;

import java.io.Serializable;

public class Goods implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String goods_id; //必填 32 商品的编号
	private String wxpay_goods_id;//可选 32 微信支付定义的统一商品编号
	private String goods_name; //必填 256 商品名称
	private String quantity;//必填 商品数量
	private String price;//单位：分
	private String goods_category;//商品种类
	private String body;//可选 1000 商品描述信息
	
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getWxpay_goods_id() {
		return wxpay_goods_id;
	}
	public void setWxpay_goods_id(String wxpay_goods_id) {
		this.wxpay_goods_id = wxpay_goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getGoods_category() {
		return goods_category;
	}
	public void setGoods_category(String goods_category) {
		this.goods_category = goods_category;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "Goods [goods_id=" + goods_id + ", wxpay_goods_id=" + wxpay_goods_id + ", goods_name=" + goods_name
				+ ", quantity=" + quantity + ", price=" + price + ", goods_category=" + goods_category + ", body="
				+ body + "]";
	}
	
}
