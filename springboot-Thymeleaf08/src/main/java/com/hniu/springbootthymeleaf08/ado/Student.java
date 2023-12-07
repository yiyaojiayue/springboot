package com.hniu.springbootthymeleaf08.ado;

import lombok.Data;

import java.util.List;

@Data
public class Student {
    public String name;
    public Integer age;
    public String sex;
    public boolean isvip;
    public List<String> interest;
}
