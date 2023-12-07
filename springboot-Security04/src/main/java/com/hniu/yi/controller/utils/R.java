package com.hniu.yi.controller.utils;

import lombok.Data;

@Data
public class R {

    private Boolean flag; // 封装是否成功
    private Object data;  // 封装数据

    private String mes;


    public R(){}

    public R(Boolean flag){
        this.flag = flag;
    }

    public R(Boolean flag,Object data){
        this.flag = flag;
        this.data = data;
    }

    public R(Boolean flag,String mes){
        this.flag = flag;
        this.mes = mes;
    }

    public R(  String mes){
        this.flag = false;
        this.mes = mes;
    }

}
