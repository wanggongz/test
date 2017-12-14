package com.shenpu.proxy.verify.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenpu.proxy.verify.mapper.ProductValidateMapper;
import com.shenpu.proxy.verify.service.ProductValidateService;

@Service
public class ProductValidateServiceImpl implements ProductValidateService{

	@Autowired ProductValidateMapper productValidateMapper;
	
	@Override
	public String queryXmlByProductCode(String productCode) {
		String xmlString = productValidateMapper.selectXmlByProductCode(productCode);
		return xmlString;
	}

	
}
