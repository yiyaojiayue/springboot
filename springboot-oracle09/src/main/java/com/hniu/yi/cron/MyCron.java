package com.hniu.yi.cron;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

@Component
public class MyCron {

    /**
     * 每五秒执行一次
     */
    @Scheduled(cron = "*/5 * * * * ?")
    private void printNowDate() {
        long nowDateTime = System.currentTimeMillis();

        Date date = new Date(nowDateTime);

        System.out.println("固定定时任务执行:--->"+date.toString()+"，此任务为每五秒执行一次");
    }



}
