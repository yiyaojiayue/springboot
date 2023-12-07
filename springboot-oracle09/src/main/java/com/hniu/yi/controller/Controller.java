package com.hniu.yi.controller;


import com.hniu.yi.dao.BookDao;
import com.hniu.yi.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private BookDao bookDao;

    @GetMapping("/index")
    public List<Book> getNameAll(){
        return bookDao.getById("张三");
    }




}
