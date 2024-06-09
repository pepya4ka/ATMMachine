package com.pepyachka.services;

public interface BankService {
    boolean isBankActive();

    void shutDownBank();

    void openBank();
}
