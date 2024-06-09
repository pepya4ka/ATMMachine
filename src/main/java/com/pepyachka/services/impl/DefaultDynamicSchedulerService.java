package com.pepyachka.services.impl;

import com.pepyachka.scheduler.BankThreadPoolTaskScheduler;
import com.pepyachka.services.DynamicSchedulerService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ScheduledFuture;

public class DefaultDynamicSchedulerService implements DynamicSchedulerService {

    private ScheduledFuture<?> scheduledFuture;

    @Resource
    private BankThreadPoolTaskScheduler taskScheduler;

    @Override
    public void startScheduledTask(Runnable task, String cronExpression) {
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            scheduledFuture = taskScheduler.schedule(task, new CronTrigger(cronExpression));
        }
    }

    @Override
    public void stopScheduledTask() {
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(false);
        }
    }
}
