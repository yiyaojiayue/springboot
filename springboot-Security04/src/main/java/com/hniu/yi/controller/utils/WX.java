package com.hniu.yi.controller.utils;

import com.alibaba.druid.support.json.JSONUtils;

import java.util.HashMap;

public class WX {

    public static void main(String[] args) {

        HashMap<String, Object> map = new HashMap<>();

        map.put("touser", "oV4Ls6RKViuCzYTfws1kG3L_Ktls");
        map.put("template_id", "J5L127tJ-ph6J0Rpy56qSJ5ttDbdcMa30PjmYKyZ2Mg");
        map.put("page", "https:www.baidu.com");

        HashMap<String, Object> data = new HashMap<>();
        data.put("thing1", formatParam("心情不好"));
        data.put("name2", formatParam("易"));
        data.put("name3", formatParam("张三"));
        data.put("time4", formatParam("2023-09-25 16:08:20"));
        data.put("thing19", formatParam("已完成"));
        map.put("data", data);
        String s = JSONUtils.toJSONString(map);

        System.out.println(s);

    }


    public  static  HashMap<String,Object> formatParam(String value){
        HashMap<String, Object> data = new HashMap<>();
        data.put("value", value);
        return data;
    }

}
