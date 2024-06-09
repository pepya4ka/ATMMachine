package com.pepyachka.ui;

import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;

import java.awt.EventQueue;

public class BankRunner implements CommandLineRunner {

    /**
     * Pull in the JFrame to be displayed.
     */
    @Resource
    private BankFrame frame;

    @Override
    public void run(String... args) throws Exception {
        /* display the form using the AWT EventQueue */
        EventQueue.invokeLater(() -> frame.setVisible(true));
    }
}
