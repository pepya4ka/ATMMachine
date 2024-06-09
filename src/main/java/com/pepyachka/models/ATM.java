package com.pepyachka.models;

import com.pepyachka.ui.ATMSupportButton;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.math.BigInteger;

@Setter
@Getter
public class ATM {
    private int id;
    private BigInteger numberOfOperations;
    private BigInteger totalAmountOfMoney;
    private boolean active;

    private JFrame mainFrame;
    private ATMSupportButton atmSupportButton;
    private JLabel operationCountLabel;
    private JLabel stateLabel;
    private JTextArea actionsTextArea;

    public ATM() {
        this.active = false;
    }

    public boolean isNotActive() {
        return !isActive();
    }

}
