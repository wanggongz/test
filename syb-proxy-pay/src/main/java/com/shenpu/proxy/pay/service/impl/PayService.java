package com.shenpu.proxy.pay.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenpu.proxy.pay.domain.Pay;
import com.shenpu.proxy.pay.domain.WxPay;
import com.shenpu.proxy.pay.mapper.PayMapper;
import com.shenpu.proxy.pay.mapper.WxPayMapper;

/**
 * 
 * @desc 支付的service
 *
 * @author hcy
 *
 * 2016年10月20日
 */
@Service("payCommonService")
public class PayService {

	private final static Logger logger = LoggerFactory.getLogger(PayService.class);
	
	@Autowired
	private PayMapper payDao;
	
	@Autowired
	private WxPayMapper wxPayDao;
	
	
	
	
	/**
	 * 保存微信支付信息
	 * @param wxPayBakMsg
	 * @throws ParseException 
	 * @throws JMSException 
	 */
	public void saveWxPayInfo(Pay pay) throws ParseException{
		List<Pay> payList = payDao.selectByOrderNo(pay);
		if (payList.size() > 0) {
			payDao.updatePayByOrderNo(pay);
			logger.info("已经有数据，更新完毕");
		} else {
			if (payDao.insert(pay) > 0) {
				WxPay wxPay = new WxPay();
				wxPay.setOutTradeNo(pay.getOrderNo());
				wxPay.setPayId(pay.getPayId());
				wxPay.setInsertTime(new Date());
				wxPay.setTotalFee(pay.getPayMoney() + "");
				wxPay.setTradeState("0");
				if (wxPayDao.insert(wxPay) <= 0) {
					throw new RuntimeException("微信交易记录表插入失败");
				}
			} else {
				throw new RuntimeException("交易总表记录插入失败");
			}
			
			
		}
	}
	
	
	/**
	 * 更新微信支付信息
	 * @param wxPayBakMsg
	 * @throws ParseException 
	 * @throws JMSException 
	 */
	public boolean updatePayByOrderNo(Pay pay) throws ParseException {
		int updatePayByOrderNo = payDao.updatePayByOrderNo(pay);
		if(updatePayByOrderNo>0){
			return true;
		}
		return false;
	}
	
	
	
	
	
	
}
