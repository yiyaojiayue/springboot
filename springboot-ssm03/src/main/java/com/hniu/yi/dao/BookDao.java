package com.hniu.yi.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hniu.yi.domain.Book;
import org.apache.ibatis.annotations.Mapper;

/*
数据层
 */

@Mapper
public interface BookDao extends BaseMapper<Book> {


}
