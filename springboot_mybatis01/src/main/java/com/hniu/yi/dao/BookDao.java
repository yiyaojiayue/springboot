package com.hniu.yi.dao;


import com.hniu.yi.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


// 注解形式配置映射
@Mapper   // 让被容器识别到，产生自动代理对象
public interface BookDao {

    @Select("select * from book where id=#{id}")
    public Book getById(Integer id);
}