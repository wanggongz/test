package com.shenpu.proxy.verify.utils;

import java.util.ArrayList;
import java.util.List;

import com.shenpu.base.utils.JaxbUtil;
import com.shenpu.base.utils.JaxbUtil.CollectionWrapper;
import com.shenpu.proxy.verify.check.bo.IfBO;
import com.shenpu.proxy.verify.check.bo.InstrBO;
import com.shenpu.proxy.verify.check.bo.ProductBO;
import com.shenpu.proxy.verify.check.bo.RuleBO;



public class Test {
	
	
	
	
	public static String toxml() {
		ProductBO productBO = new ProductBO();
		List<InstrBO> instrs = new ArrayList<InstrBO>();
		InstrBO instrBO = new InstrBO();
		instrBO.setBeginDate("2017-05-06");
		instrBO.setHasProductSpec("false");
		instrBO.setId("112");
		instrs.add(instrBO);
		
//		InstrBO instrBO1 = new InstrBO();
//		instrBO1.setBeginDate("2017-05-03");
//		instrBO1.setHasProductSpec("false");
//		instrBO1.setId("113");
//		instrs.add(instrBO1);
		
		RuleBO insRuleBo = new RuleBO();
		insRuleBo.setId("112");
		insRuleBo.setName("校验。。。。。。。。。。。。");
		List<IfBO> ifs1 = new ArrayList<>();
		IfBO ifBO1 = new IfBO();
		ifBO1.setId("if");
		ifBO1.setMsg("判断=============");
		ifBO1.setTest("getAge()>1");
		ifs1.add(ifBO1);
		insRuleBo.setIfs(ifs1);
//		instrBO.setRuleBO(insRuleBo);
		
		
		
		List<RuleBO> rules = new ArrayList<RuleBO>();
		RuleBO ruleBO = new RuleBO();
		ruleBO.setId("11111");
		ruleBO.setName("getAge");
		rules.add(ruleBO);
		List<IfBO> ifs = new ArrayList<>();
		IfBO ifBO = new IfBO();
		ifBO.setId("if");
		ifBO.setMsg("判断=============");
		ifBO.setTest("getAge()>1");
		ifs.add(ifBO);
		ruleBO.setIfs(ifs);
		productBO.setInstrs(instrs);
		productBO.setRules(rules);
		
		JaxbUtil jaxbUtil = new JaxbUtil(ProductBO.class,CollectionWrapper.class);
		String xml = jaxbUtil.toXml(productBO, "utf-8");
		System.out.println(xml);
		return xml;
	}
	
	public static ProductBO toObj(String xmlStr) {
		JaxbUtil jaxbUtil = new JaxbUtil(ProductBO.class);
		ProductBO productBO = jaxbUtil.fromXml(xmlStr,true);
		return productBO;
	}
}
