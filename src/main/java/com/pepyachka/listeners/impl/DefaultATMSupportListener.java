package com.pepyachka.listeners.impl;

import com.pepyachka.listeners.ATMSupportListener;
import com.pepyachka.models.ATMState;
import com.pepyachka.ui.ATMSupportButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

public class DefaultATMSupportListener implements ATMSupportListener {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultATMSupportListener.class);

    private static final String SUCCESS_MESSAGE = "Тех.поддержка исправила неисправность в работе банкомата %d";

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof ATMSupportButton atmSupportButton) {
            Optional.ofNullable(atmSupportButton.getAtm()).ifPresent(atm -> {
                atm.setActive(true);
                atm.getStateLabel().setText("Состояние: " + ATMState.ATM_AVAILABLE.getTitle());
                String message = String.format(SUCCESS_MESSAGE, atm.getId());
                JOptionPane.showMessageDialog(atm.getMainFrame(), message, "Сообщение", JOptionPane.INFORMATION_MESSAGE);
                atmSupportButton.setEnabled(false);
            });
        }
    }
}
