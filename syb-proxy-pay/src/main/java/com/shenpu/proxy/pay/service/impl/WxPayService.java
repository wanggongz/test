package com.shenpu.proxy.pay.service.impl;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenpu.proxy.pay.domain.Pay;
import com.shenpu.proxy.pay.domain.WxPay;
import com.shenpu.proxy.pay.mapper.PayMapper;
import com.shenpu.proxy.pay.mapper.WxPayMapper;

/**
 * @desc 支付的service
 * @author hcy
 * 2016年10月20日
 */
@Service("wxPayService")
public class WxPayService {

	@Autowired
	private WxPayMapper wxPayDao;
	
	@Autowired
	private PayMapper payDao;
	
	private final static Logger logger = LoggerFactory.getLogger(WxPayService.class);

	/**
	 * 查询交易数据
	 * @param wxPayBakMsg
	 * @throws ParseException 
	 * @throws JMSException 
	 */
	public WxPay queryPayInfoById(String tradeId) throws ParseException {
		return wxPayDao.selectByPrimaryKey(tradeId);
	}
	
	/**
	 * 更新交易数据
	 * @param wxPayBakMsg
	 * @throws ParseException 
	 * @throws JMSException 
	 */
	public boolean updateWxPayByOrderNo(WxPay wxPay) throws ParseException{
		int flag = wxPayDao.updateByOrderNo(wxPay);
		String orderNo = wxPay.getOutTradeNo(); 
		if(flag>0){
			//增加更新交易总表中记录的状态和完成时间
			 Pay pay = new Pay();
			 pay.setOrderNo(orderNo);
			 pay.setPayState("2");
			 pay.setUpdateTime(new Date());
			 int flag1 = payDao.updatePayByOrderNo(pay);
			 if (flag1>0) {
				 return true;
			 }else{
				 logger.error("订单号{}总表交易记录更新异常", orderNo);
			 }
		}else{
			logger.error("订单号{}微信交易记录更新异常", orderNo);
		}
		return false;
	}
	
	
}
