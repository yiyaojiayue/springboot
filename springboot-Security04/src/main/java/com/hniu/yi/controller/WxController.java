package com.hniu.yi.controller;

import com.hniu.yi.controller.utils.R;
import com.hniu.yi.domain.WxTest;
import com.hniu.yi.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx")
public class WxController {

    @Autowired
    private IBookService iBookService;

    @PostMapping("/t1")
    public R WxText(String name,String mes,String name2){

        WxTest wxTest = new WxTest(name, name2, mes);
        boolean flag = iBookService.savaWx(wxTest);
        return new R(flag, flag ? "添加成功^-^" : "添加失败!!!!");

    }

}
