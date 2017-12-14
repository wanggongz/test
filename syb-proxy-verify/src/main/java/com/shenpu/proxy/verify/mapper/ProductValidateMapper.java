package com.shenpu.proxy.verify.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.shenpu.proxy.verify.domain.ProductValidate;

@Mapper
public interface ProductValidateMapper {
    int insert(ProductValidate record);

    int insertSelective(ProductValidate record);
    
    String selectXmlByProductCode(String productCode);
}