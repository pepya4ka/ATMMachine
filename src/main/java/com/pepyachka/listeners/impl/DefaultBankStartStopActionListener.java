package com.pepyachka.listeners.impl;

import com.pepyachka.models.ATM;
import com.pepyachka.ui.BankFrame;
import com.pepyachka.models.BankState;
import com.pepyachka.listeners.BankStartStopActionListener;
import com.pepyachka.services.ATMService;
import com.pepyachka.services.BankService;
import com.pepyachka.services.DynamicSchedulerService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class DefaultBankStartStopActionListener implements BankStartStopActionListener {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultBankStartStopActionListener.class);

    private final Set<Integer> accountsId = new ConcurrentSkipListSet<>(Arrays.asList(1, 2, 3, 4, 5));
    private final Random randomize = new Random();

    @Resource
    private BankService bankService;
    @Resource
    private DynamicSchedulerService dynamicSchedulerService;
    @Resource
    private DynamicSchedulerService dynamicSchedulerService1;
    @Resource
    private DynamicSchedulerService dynamicSchedulerService2;
    @Resource
    private ATMService atmService;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton startStopButton) {
            if (bankService.isBankActive()) {
                bankService.shutDownBank();
                dynamicSchedulerService.stopScheduledTask();
                dynamicSchedulerService1.stopScheduledTask();
                dynamicSchedulerService2.stopScheduledTask();
                startStopButton.setText(BankState.GET_STARTED_BANK_JOB.getTitle());//set GET_STARTED_BANK_JOB, opposite his state
            } else {
                bankService.openBank();
                dynamicSchedulerService.startScheduledTask(() -> updateText(startStopButton, 0), "0/5 * * * * *");// every 5 seconds
                dynamicSchedulerService1.startScheduledTask(() -> updateText(startStopButton, 1), "0/5 * * * * *");// every 5 seconds
                dynamicSchedulerService2.startScheduledTask(() -> updateText(startStopButton, 2), "0/5 * * * * *");// every 5 seconds
                startStopButton.setText(BankState.FINISHED_BANK_JOB.getTitle());//set FINISHED_BANK_JOB, opposite his state
            }
        }
    }

    private void updateText(JButton startStopButton, int i) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(startStopButton);
        if (frame instanceof BankFrame bankFrame) {
            Integer randomAccount = getRandomElementFromSet(accountsId);
            if (randomAccount != null) {
                ATM atm = bankFrame.getAtm(i);
                atmService.getStarted(atm, randomAccount);
                accountsId.add(randomAccount);
            }
        }
    }

    public synchronized Integer getRandomElementFromSet(Set<Integer> set) {
        List<Integer> list = Arrays.asList(set.toArray(new Integer[0]));
        int randomIndexAccount = randomize.nextInt(list.size());
        Integer account = list.get(randomIndexAccount);
        set.remove(account);
        return account;
    }
}
