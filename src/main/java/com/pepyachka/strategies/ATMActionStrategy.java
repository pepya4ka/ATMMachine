package com.pepyachka.strategies;

import com.pepyachka.models.ATMFaultAction;
import com.pepyachka.models.AccountAction;

public interface ATMActionStrategy {
    AccountAction getRandomAction();

    ATMFaultAction getRandomATMFaultAction();
}
