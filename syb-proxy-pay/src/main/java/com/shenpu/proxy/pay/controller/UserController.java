//package com.shenpu.proxy.pay.controller;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.shenpu.proxy.pay.domain.User;
//import com.shenpu.proxy.pay.mapper.UserMapper;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * 
// */
//@RestController
//public class UserController {
//
//    @Autowired
//    UserMapper userMapper;
//
//    @SuppressWarnings({ "rawtypes", "unchecked" })
//	@RequestMapping("/query/{page}/{pageSize}")
//    public PageInfo query(@PathVariable Integer page, @PathVariable Integer pageSize) {
//        if(page!= null && pageSize!= null){
//            PageHelper.startPage(page, pageSize);
//        }
//        List<User> users = userMapper.list();
//        return new PageInfo(users);
//    }
//}
//
