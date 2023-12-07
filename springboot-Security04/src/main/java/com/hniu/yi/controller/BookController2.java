package com.hniu.yi.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hniu.yi.domain.Book;
import com.hniu.yi.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
表现层
 */

//@RestController
@RequestMapping("/books")
public class BookController2 {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public List<Book> select(){
        return bookService.list();
    }

    @GetMapping("/{id}")
    public Book select(@PathVariable Integer id){
        return bookService.getById(id);
    }

    @GetMapping("/page/{row}")
    public IPage<Book> selectpage(@PathVariable Integer row){
        return bookService.selectpage(row);
    }

    @PutMapping
    public Boolean update(@RequestBody Book book){
        return bookService.modify(book);
    }

    @PostMapping
    public Boolean insert(@RequestBody Book book){
        return bookService.savaBook(book);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id){
        return bookService.delete(id);
    }


}
