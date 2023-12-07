package com.hniu.yi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.hniu.yi.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
