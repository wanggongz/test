package com.shenpu.proxy.pay.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.shenpu.proxy.pay.domain.WxPay;

/** 
* @ClassName: WxPayDao 
* @Description: 微信支付信息记录 
* @author TAO.WANG
* @date 2016年4月18日 上午9:58:25 
*/
@Mapper
public interface WxPayMapper {
	//插入微信支付记录
	int insert(WxPay wxPay);
	//插入金额错误支付记录
	int insertErrorOrder(WxPay wxPay);
	//根据订单号更新微信支付记录
	int updateByOrderNo(WxPay wxPay);

	WxPay selectByPrimaryKey(String id);
	//根据订单号查询微信支付记录
	List<WxPay> selectByOrderNo(WxPay wxPay);
}