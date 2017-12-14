package com.shenpu.proxy.blacklist.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shenpu.base.utils.ExcelUtil;
import com.shenpu.base.utils.FieldUtil;
import com.shenpu.base.utils.RadomUtil;
import com.shenpu.proxy.blacklist.domain.OccupationDictionary;
import com.shenpu.proxy.blacklist.mapper.OccupationDictionaryMapper;
import com.shenpu.proxy.blacklist.repository.OccupationDictionaryRepository;
import com.shenpu.proxy.blacklist.service.OccupationDictionaryService;
/**
 * 职业字典业务实现类
 * @author Administrator
 */
@Service
public class OccupationDictionaryServiceImpl implements OccupationDictionaryService{

	@Autowired
	private OccupationDictionaryMapper occupationDictionaryMapper;
	
	@Autowired
	private OccupationDictionaryRepository odRepository;
	
	/**
	 * 导入数据到数据库
	 * fileName：文件路径
	 * Integer：返回导入数量
	 */
	@Override
	public Integer importExcel(String fileName) {
		Class<?> clazz = new OccupationDictionary().getClass();
		List<Map<String, Object>> list = ExcelUtil.readExcelContent(fileName, clazz);
		Integer i=0;
		try {
			for(Map<String, Object> map: list){
				OccupationDictionary od = (OccupationDictionary)FieldUtil.map2Entity(map, clazz);
				od.setId(RadomUtil.get32BitUUID());
				occupationDictionaryMapper.insertSelective(od);
				i+=1;
			}
			return i;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 保存数据到es
	 */
	public void saveAll(){
		long count = odRepository.count();
		if(count==0){
			List<OccupationDictionary> list = occupationDictionaryMapper.selectByAll();
			for(OccupationDictionary od: list){
				try {
					odRepository.save(od);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 根据id查询	
	 * @param id
	 * @return
	 */
	public OccupationDictionary queryById(String id){
		return occupationDictionaryMapper.selectByPrimaryKey(id);
	}
	
}
