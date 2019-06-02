package Misc;

/*
package Misc;

import ATM.Account;
import ATM.AccountActions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class TwoTextFieldRun implements ActionListener {

    JFrame jFrame;
    static JTextArea jTextArea1;
    static JTextArea jTextArea2;
    JButton jButton;

    static Runnable textArea1, textArea2;
    static Thread myThread1, myThread2;

    static boolean fl = false;//работает или нет

    static ArrayList<Account> accounts;
    static HashSet<Integer> setNumberAccounts;

    public void go() {
        jFrame = new JFrame("1 и 2");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanelforButton = new JPanel();
        JPanel jPanelforTextField = new JPanel();


        jTextArea1 = new JTextArea(2, 3);
        JScrollPane scroller = new JScrollPane(jTextArea1);
        jTextArea1.setLineWrap(true);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea2 = new JTextArea(2, 3);
        JScrollPane scroller1 = new JScrollPane(jTextArea2);
        jTextArea2.setLineWrap(true);
        scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        jButton = new JButton("Начать работу");
        jButton.addActionListener(this);
        jPanelforButton.add(jButton);

        jPanelforTextField.setLayout(new BoxLayout(jPanelforTextField, BoxLayout.Y_AXIS));
        jPanelforTextField.add(scroller);
        jPanelforTextField.add(scroller1);

        jFrame.getContentPane().add(BorderLayout.CENTER, jPanelforTextField);
        jFrame.getContentPane().add(BorderLayout.EAST, jPanelforButton);

        jFrame.setSize(500, 250);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {

//        accounts = new ArrayList<>();
//        accounts.add(new Account());
//        accounts.add(new Account());
//        accounts.add(new Account());
//        accounts.add(new Account());
//        accounts.add(new Account());
//        accounts.add(new Account());

        setNumberAccounts = new HashSet<>();
        for (int i = 0; i < accounts.size(); i++)
            setNumberAccounts.add(i);

        new TwoTextFieldRun().go();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (fl == false) {
            fl = true;
            jButton.setText("Закончить работу");

            textArea1 = new MyRunnable1();
            myThread1 = new Thread(textArea1);

            textArea2 = new MyRunnable2();
            myThread2 = new Thread(textArea2);

            myThread1.start();
            myThread2.start();
        } else {
            fl = false;
            for (Account account : accounts)
                account.setBegin();
            jButton.setText("Начать работу");
            myThread1.stop();
            myThread2.stop();
        }
    }

    public class MyRunnable1 implements Runnable {

        @Override
        public void run() {
            chooseAccount1();
        }
    }

    public class MyRunnable2 implements Runnable {

        @Override
        public void run() {
            chooseAccount2();
        }
    }


    public void chooseAccount1() {
        while (fl) {
            int number = new Random().nextInt(accounts.size());
            while (!setNumberAccounts.contains(number))
                number = new Random().nextInt(accounts.size());
            runAccoutActions(myThread1, jTextArea1, number);
            setNumberAccounts.add(number);
        }
    }

    public void chooseAccount2() {
        while (fl) {
            int number = new Random().nextInt(accounts.size());
            while (!setNumberAccounts.contains(number))
                number = new Random().nextInt(accounts.size());
            runAccoutActions(myThread2, jTextArea2, number);
            setNumberAccounts.add(number);

        }
    }

    public void runAccoutActions(Thread myThread, JTextArea jMyTextArea, int number) {
        try {
            setNumberAccounts.remove(number);
            StringBuffer temp = new StringBuffer(accounts.get(number).accoutAction());
            jMyTextArea.append(String.valueOf(number) + " " + temp.toString() + "\n");
            myThread.sleep(1500);
            temp.replace(0, temp.length(), accounts.get(number).accoutAction());
            while (!temp.substring(0).equals(AccountActions.END_OF_SERVICE.getTitle())) {
                jMyTextArea.append(temp.toString() + "\n");
                myThread.sleep(1500);
                temp.replace(0, temp.length(), accounts.get(number).accoutAction());
            }
            jMyTextArea.append(temp.toString() + "\n");
            myThread.sleep(1500);
            return;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


*/
public class TwoTextFieldRun {
}