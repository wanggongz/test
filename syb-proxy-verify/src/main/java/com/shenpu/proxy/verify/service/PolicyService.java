package com.shenpu.proxy.verify.service;

import java.util.Map;

import com.shenpu.proxy.verify.dto.PacketDTO;

public interface PolicyService {

	//保存保单信息
	boolean addPolicy(PacketDTO packetDTO);
	
	Map<String, Object> queryByApplyNo(String applyNo);

	//批量修改
	void updateException();
	
}
