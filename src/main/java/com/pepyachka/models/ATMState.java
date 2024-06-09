package com.pepyachka.models;

import lombok.Getter;

@Getter
public enum ATMState {
    ATM_UNAVAILABLE("Банкомат неисправен"),
    ATM_AVAILABLE("Банкомат полностью исправен");

    private final String title;

    ATMState(String title) {
        this.title = title;
    }
}
