package com.shenpu.proxy.show.service.product;

import com.github.pagehelper.PageInfo;
import com.shenpu.proxy.show.domain.Product;

public interface ProductService {
	
	/**
	 * 分页查询
	 */
	PageInfo<Product> queryByPage(Integer page, Integer rows,Product product);
	
	
	/**
	 * 添加
	 */
	void add(Product product);
	
}
