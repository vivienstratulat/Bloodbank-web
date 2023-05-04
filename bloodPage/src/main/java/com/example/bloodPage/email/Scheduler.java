package com.example.bloodPage.email;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {

    @Scheduled(cron="0 * 0 * * ?")
    public void cronJob(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now=new Date();
        String strDate=sdf.format(now);
        System.out.println("Java cron job expression::"+strDate);

    }
}
