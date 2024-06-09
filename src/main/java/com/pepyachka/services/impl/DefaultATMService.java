package com.pepyachka.services.impl;

import com.pepyachka.models.ATM;
import com.pepyachka.models.ATMFaultAction;
import com.pepyachka.models.ATMState;
import com.pepyachka.models.AccountAction;
import com.pepyachka.services.ATMService;
import com.pepyachka.strategies.ATMActionStrategy;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class DefaultATMService implements ATMService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultATMService.class);

    @Resource
    private ATMActionStrategy atmActionStrategy;

    @Override
    public void getStarted(ATM atm, Integer account) {
        atm.setActive(true);
        atm.getActionsTextArea().append(String.format("%d %s%n", account, AccountAction.STARTED_SERVICE.getTitle()));
        AccountAction randomAction = atmActionStrategy.getRandomAction();
        while (randomAction != AccountAction.END_OF_SERVICE) {
            if (atm.isActive()) {
                attemptToATMBreakDown(atm);
                if (atm.isActive()) {
                    waitBeforeAction();
                    atm.getActionsTextArea().append(String.format("%d %s%n", account, randomAction.getTitle()));
                    randomAction = atmActionStrategy.getRandomAction();
                }
            }
        }
        waitBeforeAction();
        atm.getActionsTextArea().append(String.format("%d %s%n", account, randomAction.getTitle()));
    }

    private void attemptToATMBreakDown(ATM atm) {
        ATMFaultAction atmFaultAction = atmActionStrategy.getRandomATMFaultAction();
        if (atmFaultAction != null) {
            JFrame atmMainFrame = atm.getMainFrame();
            if (atmMainFrame != null) {
                atm.setActive(false);
                String errorMessage = String.format(atmFaultAction.getTitle(), atm.getId());
                JOptionPane.showMessageDialog(atmMainFrame, errorMessage +
                        "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                atm.getAtmSupportButton().setEnabled(true);
                atm.getStateLabel().setText("Состояние: " + ATMState.ATM_UNAVAILABLE.getTitle());
            }
        }
    }

    private void waitBeforeAction() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            LOG.error("Thread was interrupted during sleep", e);
            Thread.currentThread().interrupt();
        }
    }
}
