package com.pepyachka.models;

import lombok.Getter;

@Getter
public enum BankState {
    FINISHED_BANK_JOB("Закончить работу"),
    GET_STARTED_BANK_JOB("Начать работу");

    private final String title;

    BankState(String title) {
        this.title = title;
    }

}
