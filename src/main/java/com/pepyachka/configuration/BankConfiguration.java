package com.pepyachka.configuration;

import com.pepyachka.ui.BankRunner;
import com.pepyachka.ui.BankFrame;
import com.pepyachka.models.Bank;
import com.pepyachka.listeners.ATMSupportListener;
import com.pepyachka.listeners.BankStartStopActionListener;
import com.pepyachka.listeners.impl.DefaultATMSupportListener;
import com.pepyachka.listeners.impl.DefaultBankStartStopActionListener;
import com.pepyachka.scheduler.BankThreadPoolTaskScheduler;
import com.pepyachka.services.ATMService;
import com.pepyachka.services.DynamicSchedulerService;
import com.pepyachka.services.impl.DefaultATMService;
import com.pepyachka.services.impl.DefaultDynamicSchedulerService;
import com.pepyachka.strategies.ATMActionStrategy;
import com.pepyachka.strategies.impl.DefaultATMActionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BankConfiguration {

    @Bean
    public BankFrame bankFrame() {
        return new BankFrame(startStopActionListener(), atmSupportListener());
    }

    @Bean
    @Scope("prototype")
    public BankThreadPoolTaskScheduler bankThreadPoolTaskScheduler() {
        return new BankThreadPoolTaskScheduler();
    }

    @Bean
    public BankStartStopActionListener startStopActionListener() {
        return new DefaultBankStartStopActionListener();
    }

    @Bean
    @Scope("prototype")
    public DynamicSchedulerService dynamicSchedulerService() {
        return new DefaultDynamicSchedulerService();
    }

    @Bean
    public ATMSupportListener atmSupportListener() {
        return new DefaultATMSupportListener();
    }

    @Bean
    public ATMActionStrategy atmActionStrategy() {
        return new DefaultATMActionStrategy();
    }

    @Bean
    public ATMService atmService() {
        return new DefaultATMService();
    }

    @Bean
    public BankRunner bankRunner() {
        return new BankRunner();
    }

    @Bean
    public Bank bank() {
        return new Bank();
    }
}
