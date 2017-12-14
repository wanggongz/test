package com.shenpu.proxy.verify.test;

import java.util.ArrayList;
import java.util.List;
import com.shenpu.base.utils.HttpUtil;
import com.shenpu.base.utils.JaxbUtil;
import com.shenpu.base.utils.RadomUtil;
import com.shenpu.proxy.verify.dto.AppntDTO;
import com.shenpu.proxy.verify.dto.BeneDTO;
import com.shenpu.proxy.verify.dto.BodyDTO;
import com.shenpu.proxy.verify.dto.HeadDTO;
import com.shenpu.proxy.verify.dto.InsuredDTO;
import com.shenpu.proxy.verify.dto.PacketDTO;
import com.shenpu.proxy.verify.dto.PolicyDTO;

public class PolicyTest {
	
	@org.junit.Test
	public void Test(){
		//核保测试(1本地2本地路由3服务器路由)
		testVerify(1);
		//试算测试
//		testTrail();
	}
	
	
	@org.junit.Test
	public void Test2(){
		String stringEntityPost = HttpUtil.stringEntityPost("http://localhost:7001/bus/refresh?destination=xiaoxiao:**", "" ,"utf-8");
		System.out.println(stringEntityPost);
	}
	
	public static void main(String[] args) {
		String data = "{\"Filedata\":\"d:\\haha.png\"}";
		String stringEntityPost = HttpUtil.stringEntityPost("http://cli.im/deqr/", data ,"utf-8");
		System.out.println(stringEntityPost);
	}
	
	
	/**
	 * 测试核保
	 */
	public void testVerify(int i){
		try {
			JaxbUtil jaxbUtil = new JaxbUtil(PacketDTO.class);
			PacketDTO packetDTO = addPolicy();
			String xmlStr = jaxbUtil.toXml(packetDTO, "utf-8");
			String stringEntityPost = null;
			if(i==1){//本地
				stringEntityPost = HttpUtil.stringEntityPost("http://localhost:9011/verify", xmlStr ,"utf-8");
			}
			if(i==2){//本地路由
				stringEntityPost = HttpUtil.stringEntityPost("http://localhost:9999/verify/verify", xmlStr ,"utf-8");
			}
			if(i==3){//服务器路由
				stringEntityPost = HttpUtil.stringEntityPost("http://192.168.1.254:59999/verify/verify", xmlStr ,"utf-8");
			}
			System.out.println("stringEntityPost============"+stringEntityPost);
		} catch (Exception e) {
			System.out.println("异常！！"+e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试试算
	 */
	public void testTrail(){
		try {
			JaxbUtil jaxbUtil = new JaxbUtil(PacketDTO.class);
			PacketDTO packetDTO = addPolicy();
			String xmlStr = jaxbUtil.toXml(packetDTO, "utf-8");
			String stringEntityPost = HttpUtil.stringEntityPost("http://192.168.1.254:59999/verifyss/trial", xmlStr ,"utf-8");
			System.out.println("stringEntityPost============"+stringEntityPost);
		} catch (Exception e) {
			System.out.println("异常！！"+e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 测试参数信息
	 * @return
	 */
	public static PacketDTO addPolicy(){
		PacketDTO packetDTO= new PacketDTO();
		BodyDTO bodyDTO = new BodyDTO();
		List<BeneDTO> beneList=new ArrayList<BeneDTO>();
			BeneDTO beneDTO1=new BeneDTO();
			beneDTO1.setAddress("铜川");
			beneDTO1.setBeneGrade("1");
			beneDTO1.setBeneName("受益人1");
			//校验属性
			beneDTO1.setIdType("01");
			beneDTO1.setSex("F");
			beneDTO1.setBirthday("1988-05-13");
			beneDTO1.setIdNo("612524198805138866");
			beneDTO1.setEmail("1@1");
			beneDTO1.setMobile("18219694903");
			beneDTO1.setBenefitRelation("04");
			
			beneDTO1.setProvId("130000");
			beneDTO1.setCityId("130200");
			beneDTO1.setCountyId("130205");
			beneDTO1.setProvId("130000");
			beneDTO1.setCityId("130200");
			beneDTO1.setCountyId("130205");
			
			beneList.add(beneDTO1);
//			BeneDTO beneDTO2=new BeneDTO();
//			beneDTO2.setAddress("山东");
//			beneDTO2.setBeneGrade("2");
//			beneDTO2.setBeneName("受益人2");
//			
//			//校验属性
//			beneDTO2.setIdType("01");
//			beneDTO2.setBirthday("1980-05-13");
//			beneDTO2.setIdNo("61252419800513665");
//			beneDTO2.setSex("M");
//			beneDTO2.setEmail("1@1");
//			beneDTO2.setMobile("18219966903");
//			beneDTO2.setBenefitRelation("03");
//			beneList.add(beneDTO2);
		bodyDTO.setBeneList(beneList);
		
		List<InsuredDTO> insuredList = new ArrayList<InsuredDTO>();
			InsuredDTO insuredDTO1 = new InsuredDTO();
			insuredDTO1.setAddress("岳西");
			insuredDTO1.setInsuredGrade("1");
			insuredDTO1.setInsuredName("老王");
			insuredDTO1.setZipCode("10000");
			insuredDTO1.setOccupation("G60");
			insuredDTO1.setPremium("1");//总保费
			insuredDTO1.setNativeplace("CHN");//ZMB
			insuredDTO1.setMarriage("1");
			insuredDTO1.setInsuredType("1");
			insuredDTO1.setYearIncom("1000");
			insuredDTO1.setWeight("90");
			insuredDTO1.setHeight("177");
			//需要验证的属性
			insuredDTO1.setProvId("140000");
			insuredDTO1.setBirthday("1970-0513");
			insuredDTO1.setIdType("01");
			insuredDTO1.setEmail("1@1");
			insuredDTO1.setIdNo("612524197005133582");
			insuredDTO1.setSex("F");
			insuredDTO1.setMobile("18219964903");
			insuredDTO1.setInsuredRelation("05");
			insuredDTO1.setNativeplace("CHN");
			insuredDTO1.setOccupation("H02");
			insuredList.add(insuredDTO1);
		bodyDTO.setInsuredList(insuredList);
		//投保人信息
		AppntDTO appBeneDTOnt = new AppntDTO();
			appBeneDTOnt.setAddress("陇南");
			appBeneDTOnt.setAppntName("小王");
			appBeneDTOnt.setEmail("176007623@11.com");
			appBeneDTOnt.setBirthday("1988-05-13");
			appBeneDTOnt.setMobile("18219633901");
			appBeneDTOnt.setIdNo("612524198805133572");
			appBeneDTOnt.setIdType("01");
			appBeneDTOnt.setZipCode("176766");
			appBeneDTOnt.setSex("M");
			//
			appBeneDTOnt.setProvId("110000");
			appBeneDTOnt.setDegree("7");
			appBeneDTOnt.setOccupation("N02");
			appBeneDTOnt.setOccupationName("影片商");
			appBeneDTOnt.setNativeplace("CHN");
			appBeneDTOnt.setMarriage("1");
			appBeneDTOnt.setIsStatutory("0");
			appBeneDTOnt.setIsself("0");
			appBeneDTOnt.setYearIncom("1000");
		bodyDTO.setAppnt(appBeneDTOnt);
		
		PolicyDTO policyDTO = new PolicyDTO();
		policyDTO.setIssuedInfo("1");//
		policyDTO.setEffectiveDate("1");
		policyDTO.setFaceamount("1000");
		policyDTO.setIsSuccess("1");
		policyDTO.setIssuedInfo("1");
		
		policyDTO.setPayintv("1");
		policyDTO.setPolicyTerm("10");
		policyDTO.setPolicyTermUnit("1");
		policyDTO.setProductName("孝亲宝老年恶性肿瘤疾病保险");
		policyDTO.setProductCode("11");
		policyDTO.setPremiumTerm("10");
		bodyDTO.setPolicyDTO(policyDTO );
		bodyDTO.setApplyCode(RadomUtil.get32BitUUID());
		
		HeadDTO headDTO = new HeadDTO();
		headDTO.setUser("testUser");
		headDTO.setPassword("password");
		headDTO.setRequestId(RadomUtil.get32BitUUID());
		headDTO.setRequestType("xxxx");
		packetDTO.setBodyDTO(bodyDTO);
		packetDTO.setHeadDTO(headDTO);
		return packetDTO;
	}
}
