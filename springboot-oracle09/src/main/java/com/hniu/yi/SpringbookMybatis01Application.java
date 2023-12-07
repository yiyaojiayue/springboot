package com.hniu.yi;

import com.hniu.yi.dao.BookDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbookMybatis01Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringbookMybatis01Application.class, args);


    }

}
