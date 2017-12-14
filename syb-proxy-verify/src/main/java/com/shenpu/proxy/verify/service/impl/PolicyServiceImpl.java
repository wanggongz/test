package com.shenpu.proxy.verify.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenpu.base.utils.FieldUtil;
import com.shenpu.base.utils.RadomUtil;
import com.shenpu.proxy.verify.client.AccessClient;
import com.shenpu.proxy.verify.config.FileConfig;
import com.shenpu.proxy.verify.domain.Appnt;
import com.shenpu.proxy.verify.domain.Bene;
import com.shenpu.proxy.verify.domain.Insured;
import com.shenpu.proxy.verify.domain.Policy;
import com.shenpu.proxy.verify.dto.BeneDTO;
import com.shenpu.proxy.verify.dto.BodyDTO;
import com.shenpu.proxy.verify.dto.InsuredDTO;
import com.shenpu.proxy.verify.dto.PacketDTO;
import com.shenpu.proxy.verify.dto.PolicyDTO;
import com.shenpu.proxy.verify.mapper.AppntMapper;
import com.shenpu.proxy.verify.mapper.BeneMapper;
import com.shenpu.proxy.verify.mapper.InsuredMapper;
import com.shenpu.proxy.verify.mapper.PolicyMapper;
import com.shenpu.proxy.verify.service.PolicyService;

@Service("policyService")
public class PolicyServiceImpl implements PolicyService{
	
	@Autowired
	private PolicyMapper policyMapper;
	
	@Autowired
	private AppntMapper appntMapper;
	
	@Autowired
	private InsuredMapper insuredMapper;
	
	@Autowired
	private BeneMapper beneMapper;
	
	@Autowired
	private AccessClient accessClient;
	
	@Autowired
	private FileConfig fileConfig;
	
	private static final Logger logger = Logger.getLogger(PolicyServiceImpl.class);
	
	@Transactional
	public Map<String, Object> queryByApplyNo(String applyNo){
		
		Map<String, Object> map = new HashMap<>();
		
		Appnt appnt = appntMapper.selectByApplyNo(applyNo);
		List<Insured> insureds = insuredMapper.selectByApplyNo(applyNo);
		List<Bene> benes = beneMapper.selectByApplyNo(applyNo);
		Policy policy = policyMapper.selectByApplyNo(applyNo);
		
		map.put("appnt", appnt);
		map.put("insureds", insureds);
		map.put("benes", benes);
		map.put("policy", policy);
		
		return map;
	}
	

	@Override
	@Transactional
	public boolean addPolicy(PacketDTO packetDTO) {
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String applyNo = RadomUtil.get64BitUUID();
			String policyNo = RadomUtil.get32BitUUID();
			BodyDTO bodyDTO = packetDTO.getBodyDTO();
			//1，转换数据
			//投保人信息

			Appnt appnt = (Appnt)FieldUtil.entityFillEntity(bodyDTO.getAppnt(), Appnt.class);
			appnt.setAppntId(RadomUtil.get32BitUUID());
			appnt.setInsertTime(sdf.format(new Date()));
			appnt.setApplyNo(applyNo);
			appnt.setPolicyNo(policyNo);
			appntMapper.insertSelective(appnt);
			//被投保人信息
			List<Insured> insuredList = new ArrayList<>();
			List<InsuredDTO> insuredDTOList = bodyDTO.getInsuredList();
			for(InsuredDTO insuredDTO : insuredDTOList) {
				Insured insured = (Insured)FieldUtil.entityFillEntity(insuredDTO, Insured.class);
				insured.setInsuredId(RadomUtil.get32BitUUID());
				insured.setInsertTime(sdf.format(new Date()));
				insured.setApplyNo(applyNo);
				insured.setPolicyNo(policyNo);
				insuredList.add(insured);
			}
			
			insuredMapper.insertBatch(insuredList);
			
			//受益人信息
			List<Bene> beneList = new ArrayList<>();
			List<BeneDTO> beneDTOList = bodyDTO.getBeneList();
			for(BeneDTO beneDTO : beneDTOList) {
				Bene bene = (Bene)FieldUtil.entityFillEntity(beneDTO, Bene.class);
				bene.setBeneId(RadomUtil.get32BitUUID());
				bene.setInsertTime(sdf.format(new Date()));
				bene.setApplyNo(applyNo);
				bene.setPolicyNo(policyNo);
				beneList.add(bene);
			}
			beneMapper.insertBatch(beneList);
			
			//保单信息
			PolicyDTO policyDTO = bodyDTO.getPolicyDTO();
			Policy policy = (Policy)FieldUtil.entityFillEntity(policyDTO, Policy.class);
			policy.setPolicyId(RadomUtil.get32BitUUID());
			policy.setInsertTime(sdf.format(new Date()));
			policy.setApplyNo(applyNo);
			policy.setPolicyNo(policyNo);
			policy.setIsVerify(Integer.valueOf(policyDTO.getIsSuccess()));
			policyMapper.insertSelective(policy);
			logger.info("流水号："+policyNo+"，核保成功");
			return true;
		} catch (Exception e) {
			logger.error("数据转换异常:{}",e);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 查询已支付成功、并且核保成功但是为承包成功的信息
	 */
	@Override
	@Transactional
	public void updateException() {
		
		Policy p = new Policy();
		p.setIsAccess(-1);
		p.setOrderState("1");
		p.setIsVerify(1);
		
		//查询异常并修改异常
		List<Policy> list = policyMapper.selectByPolicy(p);
		if(list!=null&&list.size()!=0){
			policyMapper.batchUpdate(list);
			//查询是否生成保单
			List<String> applyNos = new ArrayList<>();
			for(Policy policy: list){
				String applyNo = policy.getApplyNo();
				String string = fileConfig.getPdfRealPath()+applyNo+"_policy.pdf";
				File file = new File(string);
				//未生成保单，前去生成。
				if(!file.exists()){
					applyNos.add(applyNo);
				}
			}
			accessClient.sendPrint(applyNos);
		}
	}
	
}
