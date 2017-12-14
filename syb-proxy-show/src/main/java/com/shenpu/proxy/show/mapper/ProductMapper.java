package com.shenpu.proxy.show.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shenpu.proxy.show.domain.Product;

@Mapper
public interface ProductMapper {
    
	/**
	 * 多条件查询
	 * @param product
	 * @return
	 */
	List<Product> selectByProduct(Product product);

    int insert(Product record);

    int insertSelective(Product record);

}