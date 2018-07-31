package com.yj.foodtracesystem.controllerApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 6:42 2018/7/31
 */
@Component
public class ScheduledTask {
    private static final SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
    private static final Logger loggre= LoggerFactory.getLogger(ScheduledTask.class);

    /*@Scheduled(fixedRate = 3000)
    public void printCurrentTime() {
        loggre.info("log time:"+date.format(new Date()));
        System.out.println("current time:" + date.format(new Date()));
    }*/

    @Scheduled(cron="4-40 * * * * ?")
    public void printCronCurrentTime() {
        loggre.info("cron log time:"+date.format(new Date()));
        System.out.println("cron current time:" + date.format(new Date()));
    }


}
