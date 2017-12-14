package com.shenpu.proxy.verify.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shenpu.proxy.verify.domain.Fee;

@Mapper
public interface FeeMapper {

    int insert(Fee record);

    int insertSelective(Fee record);

    Fee selectByPrimaryKey(String feeId);

    List<Fee> selectByFee(Fee fee);

    List<Fee> selectAll();
}