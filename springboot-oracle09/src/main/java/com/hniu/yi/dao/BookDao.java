package com.hniu.yi.dao;


import com.hniu.yi.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


// 注解形式配置映射
@Mapper   // 让被容器识别到，产生自动代理对象
public interface BookDao {

    @Select("select * from a1 where name=#{name}")
    public List<Book> getById(String name);
}