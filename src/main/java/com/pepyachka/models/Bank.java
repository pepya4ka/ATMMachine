package com.pepyachka.models;

public class Bank {
    private boolean active;

    public Bank() {
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
