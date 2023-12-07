package com.hniu.springbootthymeleaf08.ado;

import lombok.Data;

import java.util.List;

/*
   name: 患者姓名
   outpatientId: 门诊号
   Service：免费咨询
   prompt： 问题描述提示
   problems： 咨询的问题
 */

@Data
public class Registration {
        public String name;
        public String outpatientId;
        public String Service;
        public String prompt;
        public List<String> problems;
}
