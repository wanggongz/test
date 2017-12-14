package com.shenpu.proxy.access.service;

import com.shenpu.proxy.access.dto.DataInfo;

public interface PolicyService {

	DataInfo modifyByPrimaryKeySelective(String applyNo);
}
