package com.pepyachka.strategies.impl;

import com.pepyachka.models.ATMFaultAction;
import com.pepyachka.models.AccountAction;
import com.pepyachka.strategies.ATMActionStrategy;

import java.util.Random;

public class DefaultATMActionStrategy implements ATMActionStrategy {

    private static final Random RANDOM = new Random();
    private static final int CHANCE_END_OF_SERVICE = 60;
    private static final int ATM_FAULT_PROBABILITY = 10;

    @Override
    public AccountAction getRandomAction() {
        int randomNumber = RANDOM.nextInt(100);

        //CHANCE_END_OF_SERVICE chance for END_OF_SERVICE
        if (randomNumber < CHANCE_END_OF_SERVICE) {
            return AccountAction.END_OF_SERVICE;
        }

        // (100 - CHANCE_END_OF_SERVICE) chance for the remaining actions
        AccountAction[] actions = AccountAction.values();
        AccountAction randomAction;
        do {
            randomAction = actions[RANDOM.nextInt(actions.length)];
        } while (randomAction == AccountAction.END_OF_SERVICE || randomAction == AccountAction.STARTED_SERVICE);

        return randomAction;
    }

    @Override
    public ATMFaultAction getRandomATMFaultAction() {
        int chance = RANDOM.nextInt(100) + 1;
        ATMFaultAction faultAction = null;
        // ATM_FAULT_PROBABILITY chance of fault
        if (chance <= ATM_FAULT_PROBABILITY) {
            ATMFaultAction[] actions = ATMFaultAction.values();
            int index = RANDOM.nextInt(actions.length);
            faultAction = actions[index];
        }
        return faultAction;
    }
}
