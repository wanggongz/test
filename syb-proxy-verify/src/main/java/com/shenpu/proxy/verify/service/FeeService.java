package com.shenpu.proxy.verify.service;

import java.util.List;

import com.shenpu.proxy.verify.domain.Fee;

/**
 * 费率业务层
 * @author Administrator
 */
public interface FeeService {
	/**
	 * 导入excel
	 * @param fileName
	 */
	public Integer importExcel(String fileName);
	
	/**
	 * 查询所有
	 */
	public List<Fee> queryAll();
	


	public Float getInsureFee(Fee fee);

	
}
