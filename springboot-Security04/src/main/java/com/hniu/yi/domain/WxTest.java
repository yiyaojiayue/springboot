package com.hniu.yi.domain;

import lombok.Data;

@Data
public class WxTest {
    private Integer id;
    private String auditor;
    private String name;
    private String description;
    private String audit_time;

    public WxTest(String name, String auditor,  String description) {
        this.auditor = auditor;
        this.name = name;
        this.description = description;
    }
}


