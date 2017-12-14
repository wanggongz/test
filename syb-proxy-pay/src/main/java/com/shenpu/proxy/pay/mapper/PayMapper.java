package com.shenpu.proxy.pay.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.shenpu.proxy.pay.domain.Pay;

@Mapper
public interface PayMapper {

	int insert(Pay pay);

	int updatePayByOrderNo(Pay pay);
	
	List<Pay> selectByOrderNo(Pay pay);
}