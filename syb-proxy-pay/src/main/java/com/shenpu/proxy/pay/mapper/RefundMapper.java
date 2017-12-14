package com.shenpu.proxy.pay.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.shenpu.proxy.pay.domain.Refund;

@Mapper
public interface RefundMapper {
    
	Refund selectById(@Param("refundId")String refundId);
	

    int insert(Refund record);

    int insertSelective(Refund record);

}