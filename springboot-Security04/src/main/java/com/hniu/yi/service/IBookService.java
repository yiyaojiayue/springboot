package com.hniu.yi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hniu.yi.domain.Book;
import com.hniu.yi.domain.WxTest;

/*
业务层
 */

public interface IBookService extends IService<Book> {

    boolean savaBook(Book book);

    boolean modify(Book book);

    boolean delete(Integer id);

    IPage<Book> selectpage(Integer row);

    IPage<Book> selectpage(Integer row,Book book);

    public boolean savaWx(WxTest wxTest) ;


}
