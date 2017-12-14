package com.shenpu.proxy.blacklist.service;

import com.shenpu.proxy.blacklist.domain.OccupationDictionary;

/**
 * 职业字典业务接口
 * @author Administrator
 */
public interface OccupationDictionaryService {
	/**
	 * 导入
	 * @param fileName
	 * @return
	 */
	public Integer importExcel(String fileName);
	
	/**
	 * 根据id查询	
	 * @param id
	 * @return
	 */
	public OccupationDictionary queryById(String id);
	
	public void saveAll();
}
