package com.pepyachka.ui;

import com.pepyachka.models.ATM;
import com.pepyachka.listeners.ATMSupportListener;
import com.pepyachka.listeners.BankStartStopActionListener;
import com.pepyachka.models.ATMState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;


public class BankFrame extends JFrame {

    private static final int NUMBER_OF_ATMS = 3;

    private final List<ATM> atms = new ArrayList<>();

    private final BankStartStopActionListener bankStartStopActionListener;
    private final ATMSupportListener atmSupportListener;

    public BankFrame(
            BankStartStopActionListener bankStartStopActionListener,
            ATMSupportListener atmSupportListener
    ) throws HeadlessException {
        this.bankStartStopActionListener = bankStartStopActionListener;
        this.atmSupportListener = atmSupportListener;
        initComponents();
    }

    private void initComponents() {
        setTitle("Система банкоматов");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel atmsPanel = initAtmsPanel();
        JPanel buttonsPanel = initButtonsPanel();

        getContentPane().add(BorderLayout.CENTER, atmsPanel);
        getContentPane().add(BorderLayout.EAST, buttonsPanel);

        setSize(1000, 500);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Init panel for information about of ATM's
     *
     * @return JPanel
     */
    private JPanel initAtmsPanel() {
        JPanel atmsPanel = new JPanel();
        atmsPanel.setName("atmsPanel");
        atmsPanel.setLayout(new GridLayout(6, 2));

        //create ATM's
        IntStream.range(0, NUMBER_OF_ATMS).forEach(i -> atmInfoFactory(atmsPanel, i));

        atmsPanel.revalidate();
        return atmsPanel;
    }

    /**
     * Init panel for buttons
     *
     * @return JPanel
     */
    private JPanel initButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setName("buttonsPanel");
        buttonsPanel.setLayout(new GridLayout(4, 1));

        //create start-stop button
        Font startStopButtonFont = new Font("TimesRoman", Font.BOLD, 15);
        JButton startStopButton = new JButton("Начать работу");
        startStopButton.setFont(startStopButtonFont);
        startStopButton.addActionListener(bankStartStopActionListener);
        buttonsPanel.add(startStopButton);

        //create support buttons for ATM's
        IntStream.range(0, NUMBER_OF_ATMS).forEach(i -> atmSupportButtonFactory(buttonsPanel, i));

        return buttonsPanel;
    }

    private void atmInfoFactory(JPanel atmsPanel, int atmNumber) {
        JLabel atmIdLabel = new JLabel(String.format("%d банкомат", atmNumber));
        JLabel numberOfCompletedOperationsLabel = new JLabel("Количество завершенных операций: ");
        JLabel statusOfATMLabel = new JLabel("Состояние: " + ATMState.ATM_AVAILABLE.getTitle());

        JTextArea statusOfATMTextArea = new JTextArea(2, 3);
        statusOfATMTextArea.setName("statusOfAtm" + atmNumber);
        JScrollPane statusOfATMScroller = new JScrollPane(statusOfATMTextArea);
        statusOfATMTextArea.setLineWrap(true);
        statusOfATMScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        statusOfATMScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        statusOfATMTextArea.setEditable(false);//restrict modify the text in field

        ATM atm = new ATM();
        atm.setId(atmNumber);
        atm.setMainFrame(this);
        atm.setActionsTextArea(statusOfATMTextArea);
        atm.setOperationCountLabel(numberOfCompletedOperationsLabel);
        atm.setStateLabel(statusOfATMLabel);
        atms.add(atm);

        atmsPanel.add(atmIdLabel);
        atmsPanel.add(numberOfCompletedOperationsLabel);
        atmsPanel.add(statusOfATMLabel);
        atmsPanel.add(statusOfATMScroller);
    }

    private void atmSupportButtonFactory(JPanel buttonsPanel, int i) {
        ATMSupportButton atmSupportButton = new ATMSupportButton(String.format("Вызвать тех. поддержку для банкомата %d", i), new ATM());
        atmSupportButton.addActionListener(atmSupportListener);
        atmSupportButton.setEnabled(false);

        Optional.ofNullable(atms.get(i)).ifPresent(atm -> {
            atm.setAtmSupportButton(atmSupportButton);
            atmSupportButton.setAtm(atm);
        });

        buttonsPanel.add(atmSupportButton);
    }

    public ATM getAtm(int i) {
        return atms.get(i);
    }
}
