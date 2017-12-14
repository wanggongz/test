package com.shenpu.proxy.access.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenpu.base.utils.RadomUtil;
import com.shenpu.proxy.access.domain.Order;
import com.shenpu.proxy.access.domain.Policy;
import com.shenpu.proxy.access.dto.DataInfo;
import com.shenpu.proxy.access.mapper.InsuredMapper;
import com.shenpu.proxy.access.mapper.OrderMapper;
import com.shenpu.proxy.access.mapper.PolicyMapper;
import com.shenpu.proxy.access.mq.PrintRabbitMqSender;
import com.shenpu.proxy.access.service.PolicyService;

@Service
public class PolicyServiceImpl implements PolicyService{
	
	private final static Logger logger = LogManager.getLogger(PolicyServiceImpl.class);

	@Autowired 
	private PolicyMapper policyMapper;
	
	@Autowired 
	private InsuredMapper insuredMapper;
	
	@Autowired 
	private OrderMapper orderMapper;
	
	@Autowired 
	private PrintRabbitMqSender printRabbitMqSender;
	
	private final int ACCESS_SUCCESS=1;
	
	@Transactional
	@Override
	public DataInfo modifyByPrimaryKeySelective(String applyNo) {
		DataInfo info = new DataInfo();
		info.setSuccess(false);
		Policy policy = policyMapper.selectByApplyNo(applyNo);
		if(policy==null){
			info.setMsg("流水号不存在");
			return info;
		}
		//是否已经核保成功
		if(policy.getIsVerify()==1){
			//金额是否一致
			Order order = orderMapper.selectByApplyNo(applyNo);
			Long sumPremium = insuredMapper.selectSumPremiumByApplyNo(applyNo);
			if(null == order){
				logger.info("applyNo:"+applyNo+",处于未支付成功状态。");
				info.setMsg("未支付成功");
			}else if(sumPremium == null || sumPremium == 0) {
				logger.info("applyNo:"+applyNo+",核保保费处于错误状态。");
				info.setMsg("核保保费处于错误状态");
			}else if(order.getPayMoney().equals(sumPremium.toString())) {
				//1，生成保单号
				policy.setPolicyNo(RadomUtil.get32BitUUID());
				//2，根据流水号更新状态为承保成功
				policy.setIsAccess(ACCESS_SUCCESS);
				policyMapper.updateByPrimaryKeySelective(policy);
				logger.info("流水号："+policy.getApplyNo()+"承包成功");
				//3，将消息发送给打印，生成电子保单、发送邮件、短信
				printRabbitMqSender.send(applyNo);
				info.setMsg("承包成功");
				info.setSuccess(true);
			}else {
				logger.info("流水号"+applyNo+":,核保保费与所交保费不一致。");
				info.setMsg("核保保费与所交保费不一致。");
			}
		}else{
			info.setMsg("未核保成功");
		}
		return info;
	}

}
