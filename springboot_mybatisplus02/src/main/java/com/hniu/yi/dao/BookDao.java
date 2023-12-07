package com.hniu.yi.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hniu.yi.domain.Book;
import org.apache.ibatis.annotations.Mapper;


// 注解形式配置映射
@Mapper   // 让被容器识别到，产生自动代理对象
public interface BookDao extends BaseMapper<Book> {

}