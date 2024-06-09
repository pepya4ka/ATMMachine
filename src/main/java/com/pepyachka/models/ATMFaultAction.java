package com.pepyachka.models;

import lombok.Getter;

@Getter
public enum ATMFaultAction {
    CONNECTION_ERROR_ATM("Ошибка подключения к серверу у банкомата %d%n"),
    CARD_EATEN_BY_ATM("Банкомат %d съел карту%n");

    private final String title;

    ATMFaultAction(String title) {
        this.title = title;
    }
}
