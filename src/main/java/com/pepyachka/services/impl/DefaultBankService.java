package com.pepyachka.services.impl;

import com.pepyachka.models.Bank;
import com.pepyachka.services.BankService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultBankService implements BankService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultBankService.class);

    @Resource
    private Bank bank;

    @Override
    public boolean isBankActive() {
        return bank.isActive();
    }

    @Override
    public void shutDownBank() {
        LOG.debug("Bank closes");
        bank.setActive(false);
    }

    @Override
    public void openBank() {
        LOG.debug("Bank opens");
        bank.setActive(true);
    }
}
