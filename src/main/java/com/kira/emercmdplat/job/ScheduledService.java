package com.kira.emercmdplat.job;

import com.kira.emercmdplat.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: kira
 * @Date: 2020/7/24 12:37
 * @Description:定时任务
 */
@Component
public class ScheduledService {

    @Autowired
    private PushService ps;

    @Scheduled(cron = "0 */1 * * * ?")
    public void scheduled() {
        ps.transPointFromRedisToDB();
    }
}
