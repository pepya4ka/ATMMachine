package com.pepyachka.services;

public interface DynamicSchedulerService {
    void startScheduledTask(Runnable task, String cronExpression);

    void stopScheduledTask();
}
