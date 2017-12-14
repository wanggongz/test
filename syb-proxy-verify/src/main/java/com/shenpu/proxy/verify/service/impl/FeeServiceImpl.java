package com.shenpu.proxy.verify.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenpu.base.utils.ExcelUtil;
import com.shenpu.base.utils.FieldUtil;
import com.shenpu.base.utils.RadomUtil;
import com.shenpu.proxy.verify.domain.Fee;
import com.shenpu.proxy.verify.mapper.FeeMapper;
import com.shenpu.proxy.verify.service.FeeService;

/**
 * 费率业务实现类
 * @author Administrator
 */
@Service("feeService")
public class FeeServiceImpl implements FeeService{
	
	@Autowired
	private FeeMapper feeMapper;
	
	/**
	 * 导入excel到数据库
	 */
	@Override
	@Transactional
	public Integer importExcel(String fileName) {
		//获取文件中的数据
		Class<?> clazz = new Fee().getClass();
		List<Map<String, Object>> list = ExcelUtil.readExcelContent(fileName, clazz);
		Integer integer = 0;
		//保存数据到数据库
		try {
			for(Map<String, Object> map: list){
				Fee fee = (Fee)FieldUtil.map2Entity(map, clazz);
				fee.setFeeId(RadomUtil.get32BitUUID());
				fee.setInsurId(null);
				feeMapper.insertSelective(fee);
				integer += 1;
			}
			return integer;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return integer;
	}

	public Float getInsureFee(Fee fe){
		Fee fee = feeMapper.selectByFee(fe).get(0);

		float policyYear = (float)fee.getPolicyYear();
		float cashValue = Float.parseFloat(fee.getCashValue());
		Float f = policyYear*cashValue;
		return f;
	}

	/**
	 * 查询所有
	 */
	public List<Fee> queryAll(){

		List<Fee> fees = feeMapper.selectAll();
		return fees;
	}

}
