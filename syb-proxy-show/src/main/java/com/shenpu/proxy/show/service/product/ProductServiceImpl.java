package com.shenpu.proxy.show.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shenpu.base.utils.FieldUtil;
import com.shenpu.proxy.show.domain.Product;
import com.shenpu.proxy.show.mapper.ProductMapper;
import com.shenpu.proxy.show.utils.RedisUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public PageInfo<Product> queryByPage(Integer page, Integer rows,Product product) {
		if(page==null){
			page =1;
		}
		if(rows==null){
			rows = 10;
		}
		String pro = "";
		if(product!=null){
			pro = product.toString();
		}
		//获取缓存
		String rediskey = FieldUtil.getRediskey()+"?page="+page+"&rows="+rows+"&product="+pro;
		Object object = redisUtil.hmGet("product", rediskey);
		PageInfo<Product> pageInfo;
		if(object!=null){
			pageInfo = (PageInfo<Product>)object;
		}else{
			PageHelper.startPage(page,rows);
			List<Product> products = productMapper.selectByProduct(product);
			pageInfo = new PageInfo<Product>(products);
			redisUtil.hmSet("product", rediskey, pageInfo);
		}
		return pageInfo;
	}

	
	@Override
	@Transactional
	public void add(Product product) {
		productMapper.insert(product);
	}

}
