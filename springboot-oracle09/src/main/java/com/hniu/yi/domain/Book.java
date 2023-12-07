package com.hniu.yi.domain;


import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "a1")
public class Book {

    private String name;
    private String subject;
    private double source;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", source=" + source +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getSource() {
        return source;
    }

    public void setSource(double source) {
        this.source = source;
    }
}