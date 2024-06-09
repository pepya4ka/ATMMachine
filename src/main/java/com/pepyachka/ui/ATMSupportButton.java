package com.pepyachka.ui;

import com.pepyachka.models.ATM;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Setter
@Getter
public class ATMSupportButton extends JButton {

    private ATM atm;

    public ATMSupportButton(String text, ATM atm) {
        super(text);
        this.atm = atm;
    }
}
