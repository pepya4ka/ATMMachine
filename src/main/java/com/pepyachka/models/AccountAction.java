package com.pepyachka.models;

import lombok.Getter;

@Getter
public enum AccountAction {
    STARTED_SERVICE("Начал обслуживание"),
    ADDED_BALANCE("Пополнил баланс"),
    PULL_OFF("Снял со счета"),
    TRANSFERRED("Перевел на другой счет"),
    END_OF_SERVICE("Закончил обслуживание");

    private final String title;

    AccountAction(String title) {
        this.title = title;
    }
}
