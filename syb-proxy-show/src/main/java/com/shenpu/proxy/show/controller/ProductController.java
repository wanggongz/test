package com.shenpu.proxy.show.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.shenpu.proxy.show.domain.Product;
import com.shenpu.proxy.show.dto.DataInfo;
import com.shenpu.proxy.show.dto.Info;
import com.shenpu.proxy.show.service.product.ProductService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	private static final Logger logger = Logger.getLogger(ProductController.class);
	
	/**
	 * 多条件分页查询
	 * @param page
	 * @param rows
	 * @return
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "产品查询",notes="根据请求中的商品条件、页码和每页数量返回产品信息")
	@ApiImplicitParam(name="productInfo",value="请求体",required=true,dataType="String")
	@RequestMapping(value="/queryByPage.htm",method=RequestMethod.POST)
	@ResponseBody
	public String queryByPage(@RequestBody String productInfo){
		ObjectMapper objectMapper= new ObjectMapper();
		DataInfo dataInfo = new DataInfo();
		dataInfo.setMsg("查询成功");
		dataInfo.setSuccess(true);
		
		PageInfo<Product> pageInfo = null;
		try {
			Info request = objectMapper.readValue(productInfo, Info.class);
			pageInfo= productService.queryByPage(request.getPage(),request.getRows(),request.getProduct());
			dataInfo.setResponse(pageInfo);
		} catch (Exception e) {
			logger.error("分页查询产品异常:{}",e);
			dataInfo.setSuccess(false);
			dataInfo.setMsg("服务器忙...");
		}
		
		try {
			return objectMapper.writeValueAsString(dataInfo);
		} catch (JsonProcessingException e) {
			logger.error("jackson转换异常：{}"+e);
			return null;
		}
	}
}
