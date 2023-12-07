package com.hniu.yi.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hniu.yi.dao.BookDao;
import com.hniu.yi.dao.WxTestDao;
import com.hniu.yi.domain.Book;
import com.hniu.yi.domain.WxTest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements IBookService{

    @Autowired
    private BookDao bookDao;

    @Autowired
    private WxTestDao wxTestDao;

    @Override
    public boolean savaBook(Book book) {
        return bookDao.insert(book)>0;
    }

    @Override
    public boolean modify(Book book) {
        return bookDao.updateById(book)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return bookDao.deleteById(id)>0;
    }

    @Override
    public IPage<Book> selectpage(Integer row) {
        Page<Book> bookPage = new Page<>(row, 8);
        IPage<Book> iPage = bookDao.selectPage(bookPage,null);
        return iPage;
    }

    @Override
    public IPage<Book> selectpage(Integer row, Book book) {

        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(book.getType()), Book::getType, book.getName());
        lqw.like(Strings.isNotEmpty(book.getName()), Book::getName, book.getName());
        lqw.like(Strings.isNotEmpty(book.getDescription()), Book::getDescription, book.getDescription());

        Page<Book> bookPage = new Page<>(row, 8);
        IPage<Book> iPage = bookDao.selectPage(bookPage,lqw);
        return iPage;
    }

    @Override
    public boolean savaWx(WxTest wxTest) {
        return wxTestDao.insert(wxTest)>0 ;
    }
}
