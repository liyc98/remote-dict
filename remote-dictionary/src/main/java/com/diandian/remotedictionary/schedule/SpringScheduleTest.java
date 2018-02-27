package com.diandian.remotedictionary.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description of SpringScheduleTest
 * Copyright 2017 Meorient, Inc. All rights reserved.
 *
 * @author yuancheng.li
 * @version $Id: $
 * @created on 2017年12月29日
 */
@Component
public class SpringScheduleTest {

    private static final Logger log = LoggerFactory.getLogger(SpringScheduleTest.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

}
