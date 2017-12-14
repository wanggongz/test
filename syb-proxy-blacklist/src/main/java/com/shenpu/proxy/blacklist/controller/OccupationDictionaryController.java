package com.shenpu.proxy.blacklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.shenpu.proxy.blacklist.service.OccupationDictionaryService;
/**
 * 职业字典控制层
 * @author Administrator
 */
@Controller
@RequestMapping("/occupationDictionary")
public class OccupationDictionaryController {
	
	@Autowired
	private OccupationDictionaryService occupationDictionaryService;
	
	/**
	 * 导入Excel到数据库
	 * @param fileName：文件路径
	 * @return
	 */
	@RequestMapping(value = "/import.htm",method = RequestMethod.GET)
	@ResponseBody
	public String importExcel(String fileName){
		if(fileName==null){
			fileName="D:/职业代码表.xlsx";
		}
		Integer importExcel = occupationDictionaryService.importExcel(fileName);
		return "成功导入"+importExcel+"条数据";
	}
	
	/**
	 * 导入数据到es
	 */
	@RequestMapping(value = "/saveAll",method = RequestMethod.GET)
	@ResponseBody
	public String saveAll(){
		occupationDictionaryService.saveAll();
		return "ok";
	}
	
}
