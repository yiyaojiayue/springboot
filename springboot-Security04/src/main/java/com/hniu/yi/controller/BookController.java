package com.hniu.yi.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hniu.yi.controller.utils.R;
import com.hniu.yi.domain.Book;
import com.hniu.yi.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


/*
表现层
 */

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public R select(){
        List<Book> list = bookService.list();
        return new R(true,list);
    }

    @GetMapping("/{id}")
    public R select(@PathVariable Integer id){
        return new R(true, bookService.getById(id));
    }

//    @GetMapping("/page/{row}")
//    public R selectpage(@PathVariable Integer row,Book book){
//        IPage<Book> page = bookService.selectpage(row);
//        // 如果当前页码值大于总页码值，那么重新执行操作，使用最大页面值当作当前页面值
//        if (row>page.getPages()){
//            page = bookService.selectpage((int) page.getPages());
//        }
//
//        return new R(true,page);
//    }



    @GetMapping("/page/{row}")
    public R selectpage(@PathVariable Integer row,Book book){
        IPage<Book> page = bookService.selectpage(row,book);
        // 如果当前页码值大于总页码值，那么重新执行操作，使用最大页面值当作当前页面值

        if (row>page.getPages()){
            page = bookService.selectpage((int) page.getPages(),book);
        }

        return new R(true,page);
    }



    @PutMapping
    public R update(@RequestBody Book book)   {
        return new R(bookService.modify(book));
    }

    @PostMapping
    public R insert(@RequestBody Book book) throws IOException {
        if (book.getName().equals("123")) throw new IOException();
        boolean flag = bookService.savaBook(book);
        return new R(flag, flag ? "添加成功^-^" : "添加失败!!!!");
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id){
        return new R(bookService.delete(id));
    }




}
