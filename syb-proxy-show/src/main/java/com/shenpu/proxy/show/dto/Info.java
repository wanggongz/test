package com.shenpu.proxy.show.dto;

import java.io.Serializable;

import com.shenpu.proxy.show.domain.Product;

/**
 * 产品展示请求类
 */
public class Info implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer page;
	
	private Integer rows;
	
	private Product product;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "Info [page=" + page + ", rows=" + rows + ", product=" + product + "]";
	}
	
}
