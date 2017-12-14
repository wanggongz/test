package com.shenpu.proxy.blacklist.repository.es;

import com.shenpu.proxy.blacklist.domain.AddressDictionary;

public interface AddressDictionaryEsRepository {
	/**
	 * 根据code和parentcode查询
	 * @return
	 */
	public AddressDictionary queryByPlaceCodeAndUpPlaceCode(String placeCode,String upPlaceCode);
	
	/**
	 * 根据code查询
	 */
	public AddressDictionary queryByPlaceCode(String placeCode);
	
}
