package com.shenpu.proxy.pay.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shenpu.proxy.pay.domain.User;

import java.util.List;


@Mapper
public interface UserMapper {
    public User selectById(@Param("id") int id);
    public List<User> list();
}
