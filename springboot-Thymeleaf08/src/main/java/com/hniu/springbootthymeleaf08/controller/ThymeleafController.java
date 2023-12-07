package com.hniu.springbootthymeleaf08.controller;

import com.hniu.springbootthymeleaf08.ado.Registration;
import com.hniu.springbootthymeleaf08.ado.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class ThymeleafController {

    @GetMapping("index")
    public String GetIndex(Model model){
        model.addAttribute("name","bigsai");
        return "index";
    }
    @GetMapping("index2")
    public String Index2(Model model){
        Student student = new Student();
        student.setName("李华");
        student.setAge(18);
        student.setSex("1");
        student.setIsvip(true);
        student.setInterest(Arrays.asList("运动","游戏","旅游"));
        model.addAttribute("student", student);
        return "index2";
    }
    @GetMapping("index3")
    public String Index3(Model model){

        Registration registration = new Registration();
        registration.setProblems(Arrays.asList("糖尿病","近视","甲状腺","皮肤","口腔","减肥","孕期","失眠"));
        model.addAttribute("name", "黄芝易");
        model.addAttribute("hao", "1497463");
        model.addAttribute("Service", "免费咨询");
        model.addAttribute("fuz", "复诊是指你希望咨询的症状在本院已确诊。");
        model.addAttribute("registration", registration);


        return "index3";
    }
    @GetMapping("/wx")
    public String hello(){
        return "wx";
    }

}
