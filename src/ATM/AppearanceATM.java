package ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class AppearanceATM implements ActionListener {

    private static JFrame jFrame;

    private JPanel jPanel;//в нем находятся JLabels
    private JPanel jPanelForScroller;//в нем находятся Scroller's
    private JPanel jPanelForButton;//панель для кнопок

    private JLabel jLabelJPanel1;//1 банкомат
    private JLabel jLabelNumberOperation1;//Кол-во операций 1 банкомата
    private JLabel jLabelEvent1;//Состояние банкомата 1
    private static JTextArea jTextAreaJPanel1;

    private JLabel jLabelJPanel2;//2 банкомат
    private JLabel jLabelNumberOperation2;//Кол-во операций 2 банкомата
    private JLabel jLabelEvent2;//Состояние банкомата 2
    private static JTextArea jTextAreaJPanel2;

    private JLabel jLabelJPanel3;//3 банкомат
    private JLabel jLabelNumberOperation3;//Кол-во операций 3 банкомата
    private JLabel jLabelEvent3;//Состояние банкомата 3
    private static JTextArea jTextAreaJPanel3;

    private static JButton jButtonBeginAndEnd;//кнопка начать/закончить
    private static JButton jButtonTechnicalSupport1;//кнопка вызова технической поддержки для банкомата 1
    private static JButton jButtonTechnicalSupport2;//кнопка вызова технической поддержки для банкомата 2
    private static JButton jButtonTechnicalSupport3;//кнопка вызова технической поддержки для банкомата 3

    private static Runnable textArea1, textArea2, textArea3;
    private static Thread myThread1, myThread2, myThread3;

    private static MyDrawPanel drawPanel;
    private Color xColor;
    private static Runnable runnableCircle;
    private static Thread threadCircle;

    private static ArrayList<Account> accounts;
    private static HashSet<Integer> setNumberAccounts;

    private static boolean fl = false;//работает или нет
    private static boolean bankomat1 = true;//работа банкомата 1
    private static boolean bankomat2 = true;//работа банкомата 1
    private static boolean bankomat3 = true;//работа банкомата 1

    private static int numberOperation1;//Количество операци банкомата 1
    private static int numberOperation2;//Количество операци банкомата 2
    private static int numberOperation3;//Количество операци банкомата 3
    private static int bankomatCashAmount1;//Сумма денег в банкомата 1
    private static int bankomatCashAmount2;//Сумма денег в банкомата 1
    private static int bankomatCashAmount3;//Сумма денег в банкомата 1

    private void go() {

        jFrame = new JFrame("Система банкоматов");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jLabelJPanel1 = new JLabel("1 банкомат");
        jLabelNumberOperation1 = new JLabel("Количество завершенных операций: ");
        jLabelEvent1 = new JLabel("Состояние: ");
        jTextAreaJPanel1 = new JTextArea(2, 3);
        JScrollPane scroller1 = new JScrollPane(jTextAreaJPanel1);
        jTextAreaJPanel1.setLineWrap(true);
        scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jTextAreaJPanel1.setEditable(false);//запрещает изменять текст в поле

        jLabelJPanel2 = new JLabel("2 банкомат");
        jLabelNumberOperation2 = new JLabel("Количество завершенных операций: ");
        jLabelEvent2 = new JLabel("Состояние: ");
        jTextAreaJPanel2 = new JTextArea(2, 3);
        JScrollPane scroller2 = new JScrollPane(jTextAreaJPanel2);
        jTextAreaJPanel2.setLineWrap(true);
        scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jTextAreaJPanel2.setEditable(false);//запрещает изменять текст в поле

        jLabelJPanel3 = new JLabel("3 банкомат");
        jLabelNumberOperation3 = new JLabel("Количество завершенных операций: ");
        jLabelEvent3 = new JLabel("Состояние: ");
        jTextAreaJPanel3 = new JTextArea(2, 3);
        JScrollPane scroller3 = new JScrollPane(jTextAreaJPanel3);
        jTextAreaJPanel3.setLineWrap(true);
        scroller3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jTextAreaJPanel3.setEditable(false);//запрещает изменять текст в поле

        jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(6, 2));
        jPanel.add(jLabelJPanel1);
        jPanel.add(jLabelNumberOperation1);
        jPanel.add(jLabelEvent1);
        jPanel.add(scroller1);

        jPanel.add(jLabelJPanel2);
        jPanel.add(jLabelNumberOperation2);
        jPanel.add(jLabelEvent2);
        jPanel.add(scroller2);


        jPanel.add(jLabelJPanel3);
        jPanel.add(jLabelNumberOperation3);
        jPanel.add(jLabelEvent3);
        jPanel.add(scroller3);


        jPanel.revalidate();

//        jPanelForScroller = new JPanel();
//        jPanelForScroller.setLayout(new GridLayout(3, 1));
//        jPanelForScroller.add(scroller1);
//        jPanelForScroller.add(scroller2);
//        jPanelForScroller.add(scroller3);
        numberOperation1 = 0;
        numberOperation2 = 0;
        numberOperation3 = 0;
        bankomatCashAmount1 = 100;
        bankomatCashAmount2 = 150;
        bankomatCashAmount3 = 400;


        Font BigFontTR = new Font("TimesRoman", Font.BOLD, 15);//Шрифт текста на кнопке
        jButtonBeginAndEnd = new JButton("Начать работу");
        jButtonBeginAndEnd.setFont(BigFontTR);
        jButtonBeginAndEnd.addActionListener(this);
        jButtonTechnicalSupport1 = new JButton("Вызвать тех. поддержку для банкомата 1");
        jButtonTechnicalSupport1.addActionListener(this);
        jButtonTechnicalSupport1.setEnabled(!bankomat1);
        jButtonTechnicalSupport2 = new JButton("Вызвать тех. поддержку для банкомата 2");
        jButtonTechnicalSupport2.addActionListener(this);
        jButtonTechnicalSupport2.setEnabled(!bankomat2);
        jButtonTechnicalSupport3 = new JButton("Вызвать тех. поддержку для банкомата 3");
        jButtonTechnicalSupport3.addActionListener(this);
        jButtonTechnicalSupport3.setEnabled(!bankomat3);


        jPanelForButton = new JPanel();
        jPanelForButton.setLayout(new GridLayout(4, 1));
        jPanelForButton.add(jButtonBeginAndEnd);
        jPanelForButton.add(jButtonTechnicalSupport1);
        jPanelForButton.add(jButtonTechnicalSupport2);
        jPanelForButton.add(jButtonTechnicalSupport3);

        drawPanel = new MyDrawPanel();

        jFrame.getContentPane().add(BorderLayout.CENTER, jPanel);
//        jFrame.getContentPane().add(BorderLayout.CENTER, jPanelForScroller);
        jFrame.getContentPane().add(BorderLayout.EAST, jPanelForButton);
        jFrame.getContentPane().add(BorderLayout.NORTH, drawPanel);

        jFrame.setSize(1000, 500);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Начать работу":
                fl = true;
                jButtonBeginAndEnd.setText("Закончить работу");

                runnableCircle = new RunnableForCircle();
                textArea1 = new textArea1();
                textArea2 = new textArea2();
                textArea3 = new textArea3();

                threadCircle = new Thread(runnableCircle);
                myThread1 = new Thread(textArea1);
                myThread2 = new Thread(textArea2);
                myThread3 = new Thread(textArea3);

                threadCircle.start();
                myThread1.start();
                try {
                    myThread1.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                myThread2.start();
                try {
                    myThread2.sleep(15);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                myThread3.start();
                try {
                    myThread3.sleep(5);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Закончить работу":
                jButtonBeginAndEnd.setText("Начать работу");

                threadCircle.stop();
                myThread1.stop();
                myThread2.stop();
                myThread3.stop();

                xColor = Color.red;
                jFrame.repaint();
                fl = false;
                break;
            case "Вызвать тех. поддержку для банкомата 1":
                bankomat1 = true;
                jButtonTechnicalSupport1.setEnabled(!bankomat1);
                JOptionPane.showMessageDialog(jFrame, "Тех.поддержка исправила неисправность в работе банкомата 1", "Сообщение", 1);
                break;
            case "Вызвать тех. поддержку для банкомата 2":
                bankomat2 = true;
                jButtonTechnicalSupport2.setEnabled(!bankomat2);
                JOptionPane.showMessageDialog(jFrame, "Тех.поддержка исправила неисправность в работе банкомата 2", "Сообщение", 1);
                break;
            case "Вызвать тех. поддержку для банкомата 3":
                bankomat3 = true;
                jButtonTechnicalSupport3.setEnabled(!bankomat3);
                JOptionPane.showMessageDialog(jFrame, "Тех.поддержка исправила неисправность в работе банкомата 3", "Сообщение", 1);
                break;
        }
    }

    public static void main(String[] args) {
        accounts = new ArrayList<>();
        accounts.add(new Account());
        accounts.add(new Account());
        accounts.add(new Account());
        accounts.add(new Account());
        accounts.add(new Account());
        accounts.add(new Account());

        setNumberAccounts = new HashSet<>();
        for (int i = 0; i < accounts.size(); i++)
            setNumberAccounts.add(i);

        new AppearanceATM().go();
    }

    public class RunnableForCircle implements Runnable {

        @Override
        public void run() {
            try {
                circle();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class MyDrawPanel extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(xColor);
            g.fillOval(1, 1, 8, 8);
        }
    }

    public void circle() throws InterruptedException {
        for (; ; ) {
            xColor = Color.gray;
            jFrame.repaint();
            Thread.sleep(200);
            xColor = Color.green;
            jFrame.repaint();
            Thread.sleep(1700);
        }
    }

    public class textArea1 implements Runnable {

        @Override
        public void run() {
            chooseAccount1();
        }
    }

    public class textArea2 implements Runnable {

        @Override
        public void run() {
            chooseAccount2();
        }
    }

    public class textArea3 implements Runnable {

        @Override
        public void run() {
            chooseAccount3();
        }
    }

    public void chooseAccount1() {
        while (fl && bankomat1) {
            int number = new Random().nextInt(accounts.size());
            while (!setNumberAccounts.contains(number))
                number = new Random().nextInt(accounts.size());
            runAccountActions1(number);
            setNumberAccounts.add(number);
        }
    }

    public void chooseAccount2() {
        while (fl && bankomat2) {
            int number = new Random().nextInt(accounts.size());
            while (!setNumberAccounts.contains(number))
                number = new Random().nextInt(accounts.size());
            runAccountActions2(number);
            setNumberAccounts.add(number);
        }
    }

    public void chooseAccount3() {
        while (fl && bankomat3) {
            int number = new Random().nextInt(accounts.size());
            while (!setNumberAccounts.contains(number))
                number = new Random().nextInt(accounts.size());
            runAccountActions3(number);
            setNumberAccounts.add(number);
        }
    }

    public void runAccountActions1(int number) {
        try {
            setNumberAccounts.remove(number);
            ReturnAddOrPull returnAddOrPull = accounts.get(number).accoutAction();
            StringBuffer temp = new StringBuffer(returnAddOrPull.action);
            if (bankomatCashAmount1 > 0) jLabelEvent1.setText("Состояние: банкомат полностью исправен");
            jTextAreaJPanel1.append(String.valueOf(number) + " " + temp.toString() + "\n");
            numberOperation1++;
            jLabelNumberOperation1.setText("Количество завершенных операций: " + numberOperation1);
            myThread1.sleep(2500 - new Random().nextInt(500));
            int tempError = new Random().nextInt(50);
            if (tempError < 1) {//рандом ошибки
                bankomat1 = false;
                JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 1\n" +
                        "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                jButtonTechnicalSupport1.setEnabled(!bankomat1);
                do {
                    myThread1.sleep(500);
                }
                while (!bankomat1);
            }
            returnAddOrPull = accounts.get(number).accoutAction();
            temp.replace(0, temp.length(), String.valueOf(returnAddOrPull.action));
            if (bankomatCashAmount1 > 0) jLabelEvent1.setText("Состояние: банкомат полностью исправен");
            while (!temp.substring(0).equals(AccountActions.END_OF_SERVICE.getTitle())) {
                if (bankomatCashAmount1 != 0) {
                    jLabelEvent1.setText("Состояние: банкомат полностью исправен");
                if ( (bankomatCashAmount1 < Math.abs(returnAddOrPull.addOrPull)) && (returnAddOrPull.addOrPull < 0) && temp.substring(0).equals((AccountActions.PULL_OFF.getTitle())) ) {
                    returnAddOrPull.addOrPull = -bankomatCashAmount1;
                    jTextAreaJPanel1.append(String.valueOf(AccountActions.PULL_OFF.getTitle()) + " " + Math.abs(returnAddOrPull.addOrPull) + "\n");
                    bankomatCashAmount1 += returnAddOrPull.addOrPull;
                    numberOperation1++;
                    jLabelNumberOperation1.setText("Количество завершенных операций: " + numberOperation1);
                    jLabelEvent1.setText("Состояние: банкомат недоступен для снятия");
                    myThread1.sleep(2500 - new Random().nextInt(500));
                    returnAddOrPull = accounts.get(number).accoutAction();
                    temp.replace(0, temp.length(), String.valueOf(returnAddOrPull.action));
                    continue;
                }
                    jTextAreaJPanel1.append(temp.toString() + " " + Math.abs(returnAddOrPull.addOrPull) + "\n");
                    bankomatCashAmount1 += returnAddOrPull.addOrPull;
                    if (bankomatCashAmount1 > 0) jLabelEvent1.setText("Состояние: банкомат полностью исправен");
                    numberOperation1++;
                    jLabelNumberOperation1.setText("Количество завершенных операций: " + numberOperation1);
                    myThread1.sleep(2500 - new Random().nextInt(500));
                    tempError = new Random().nextInt(50);
                    if (tempError < 1) {//рандом ошибки
                        bankomat1 = false;
                        JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 1\n" +
                                "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        jButtonTechnicalSupport1.setEnabled(!bankomat1);
                        do {
                            myThread1.sleep(500);
                        }
                        while (!bankomat1);
                    }
                    returnAddOrPull = accounts.get(number).accoutAction();
                    temp.replace(0, temp.length(), String.valueOf(returnAddOrPull.action));
                } else {
                    jLabelEvent1.setText("Состояние: банкомат недоступен для снятия");
                    if (!temp.substring(0).equals(AccountActions.PULL_OFF.getTitle())) {
                        jTextAreaJPanel1.append(temp.toString() + " " + Math.abs(returnAddOrPull.addOrPull) + "\n");
                        bankomatCashAmount1 += returnAddOrPull.addOrPull;
                        if (bankomatCashAmount1 > 0) jLabelEvent1.setText("Состояние: банкомат полностью исправен");
                        numberOperation1++;
                        jLabelNumberOperation1.setText("Количество завершенных операций: " + numberOperation1);
                        myThread1.sleep(2500 - new Random().nextInt(500));
                        tempError = new Random().nextInt(50);
                        if (tempError < 1) {//рандом ошибки
                            bankomat1 = false;
                            JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 1\n" +
                                    "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            jButtonTechnicalSupport1.setEnabled(!bankomat1);
                            do {
                                myThread1.sleep(500);
                            }
                            while (!bankomat1);
                        }
                        returnAddOrPull = accounts.get(number).accoutAction();
                        temp.replace(0, temp.length(), String.valueOf(returnAddOrPull.action));
                    } else {
                        returnAddOrPull = accounts.get(number).accoutAction();
                        temp.replace(0, temp.length(), String.valueOf(returnAddOrPull.action));
                    }
                }
            }
            jTextAreaJPanel1.append(temp.toString() + "\n");
            numberOperation1++;
            jLabelNumberOperation1.setText("Количество завершенных операций: " + numberOperation1);
            myThread1.sleep(2500 - new Random().nextInt(500));
            tempError = new Random().nextInt(50);
            if (tempError < 1) {//рандом ошибки
                bankomat1 = false;
                JOptionPane.showMessageDialog(jFrame, "Банкомат 1 съел карту\n" +
                        "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                jButtonTechnicalSupport1.setEnabled(!bankomat1);
                do {
                    myThread1.sleep(500);
                }
                while (!bankomat1);
            } else {
                tempError = new Random().nextInt(50);
                if (tempError < 1) {//рандом ошибки
                    bankomat1 = false;
                    JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 1\n" +
                            "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    jButtonTechnicalSupport1.setEnabled(!bankomat1);
                    do {
                        myThread1.sleep(500);
                    }
                    while (!bankomat1);
                }
            }
            return;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runAccountActions2(int number) {
        try {
            setNumberAccounts.remove(number);
            ReturnAddOrPull returnAddOrPull = accounts.get(number).accoutAction();
            StringBuffer temp = new StringBuffer(returnAddOrPull.action);
            if (bankomatCashAmount2 > 0) jLabelEvent2.setText("Состояние: банкомат полностью исправен");
            jTextAreaJPanel2.append(String.valueOf(number) + " " + temp.toString() + "\n");
            numberOperation2++;
            jLabelNumberOperation2.setText("Количество завершенных операций: " + numberOperation2);
            myThread2.sleep(2500 - new Random().nextInt(500));
            int tempError = new Random().nextInt(50);
            if (tempError < 1) {//рандом ошибки
                bankomat2 = false;
                JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 2\n" +
                        "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                jButtonTechnicalSupport2.setEnabled(!bankomat2);
                do {
                    myThread2.sleep(500);
                }
                while (!bankomat2);
            }
            returnAddOrPull = accounts.get(number).accoutAction();
            temp.replace(0, temp.length(), String.valueOf(returnAddOrPull.action));
            if (bankomatCashAmount2 > 0) jLabelEvent2.setText("Состояние: банкомат полностью исправен");
            while (!temp.substring(0).equals(AccountActions.END_OF_SERVICE.getTitle())) {
                if (bankomatCashAmount2 != 0) {
                    jLabelEvent2.setText("Состояние: банкомат полностью исправен");
                    if ( (bankomatCashAmount2 < Math.abs(returnAddOrPull.addOrPull)) && (returnAddOrPull.addOrPull < 0) && temp.substring(0).equals((AccountActions.PULL_OFF.getTitle())) ) {
                        returnAddOrPull.addOrPull = -bankomatCashAmount2;
                        jTextAreaJPanel2.append(String.valueOf(AccountActions.PULL_OFF.getTitle()) + " " + Math.abs(returnAddOrPull.addOrPull) + "\n");
                        bankomatCashAmount2 += returnAddOrPull.addOrPull;
                        numberOperation2++;
                        jLabelNumberOperation2.setText("Количество завершенных операций: " + numberOperation2);
                        jLabelEvent2.setText("Состояние: банкомат недоступен для снятия");
                        myThread2.sleep(2500 - new Random().nextInt(500));
                        returnAddOrPull = accounts.get(number).accoutAction();
                        temp.replace(0, temp.length(), String.valueOf(returnAddOrPull.action));
                        continue;
                    }
                    jTextAreaJPanel2.append(temp.toString() + " " + Math.abs(returnAddOrPull.addOrPull) + "\n");
                    bankomatCashAmount2 += returnAddOrPull.addOrPull;
                    if (bankomatCashAmount2 > 0) jLabelEvent2.setText("Состояние: банкомат полностью исправен");
                    numberOperation2++;
                    jLabelNumberOperation2.setText("Количество завершенных операций: " + numberOperation2);
                    myThread2.sleep(2500 - new Random().nextInt(500));
                    tempError = new Random().nextInt(50);
                    if (tempError < 1) {//рандом ошибки
                        bankomat2 = false;
                        JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 2\n" +
                                "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        jButtonTechnicalSupport2.setEnabled(!bankomat2);
                        do {
                            myThread2.sleep(500);
                        }
                        while (!bankomat2);
                    }
                    returnAddOrPull = accounts.get(number).accoutAction();
                    temp.replace(0, temp.length(), String.valueOf(returnAddOrPull.action));
                } else {
                    jLabelEvent2.setText("Состояние: банкомат недоступен для снятия");
                    if (!temp.substring(0).equals(AccountActions.PULL_OFF.getTitle())) {
                        jTextAreaJPanel2.append(temp.toString() + " " + Math.abs(returnAddOrPull.addOrPull) + "\n");
                        bankomatCashAmount2 += returnAddOrPull.addOrPull;
                        if (bankomatCashAmount2 > 0) jLabelEvent2.setText("Состояние: банкомат полностью исправен");
                        numberOperation2++;
                        jLabelNumberOperation2.setText("Количество завершенных операций: " + numberOperation2);
                        myThread2.sleep(2500 - new Random().nextInt(500));
                        tempError = new Random().nextInt(50);
                        if (tempError < 1) {//рандом ошибки
                            bankomat2 = false;
                            JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 2\n" +
                                    "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            jButtonTechnicalSupport2.setEnabled(!bankomat2);
                            do {
                                myThread2.sleep(500);
                            }
                            while (!bankomat2);
                        }
                        returnAddOrPull = accounts.get(number).accoutAction();
                        temp.replace(0, temp.length(), String.valueOf(returnAddOrPull.action));
                    } else {
                        returnAddOrPull = accounts.get(number).accoutAction();
                        temp.replace(0, temp.length(), String.valueOf(returnAddOrPull.action));
                    }
                }
            }
            jTextAreaJPanel2.append(temp.toString() + "\n");
            numberOperation2++;
            jLabelNumberOperation2.setText("Количество завершенных операций: " + numberOperation2);
            myThread2.sleep(2500 - new Random().nextInt(500));
            tempError = new Random().nextInt(50);
            if (tempError < 1) {//рандом ошибки
                bankomat2 = false;
                JOptionPane.showMessageDialog(jFrame, "Банкомат 2 съел карту\n" +
                        "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                jButtonTechnicalSupport2.setEnabled(!bankomat2);
                do {
                    myThread2.sleep(500);
                }
                while (!bankomat2);
            } else {
                tempError = new Random().nextInt(50);
                if (tempError < 1) {//рандом ошибки
                    bankomat2 = false;
                    JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 2\n" +
                            "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    jButtonTechnicalSupport2.setEnabled(!bankomat2);
                    do {
                        myThread2.sleep(500);
                    }
                    while (!bankomat2);
                }
            }
            return;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runAccountActions3(int number) {
        try {
            setNumberAccounts.remove(number);
            ReturnAddOrPull returnAddOrPull = accounts.get(number).accoutAction();
            StringBuffer temp = new StringBuffer(returnAddOrPull.action);
            if (bankomatCashAmount3 > 0) jLabelEvent3.setText("Состояние: банкомат полностью исправен");
            jTextAreaJPanel3.append(String.valueOf(number) + " " + temp.toString() + "\n");
            numberOperation3++;
            jLabelNumberOperation3.setText("Количество завершенных операций: " + numberOperation3);
            myThread3.sleep(2500 - new Random().nextInt(500));
            int tempError = new Random().nextInt(50);
            if (tempError < 1) {//рандом ошибки
                bankomat3 = false;
                JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 3\n" +
                        "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                jButtonTechnicalSupport3.setEnabled(!bankomat3);
                do {
                    myThread3.sleep(500);
                }
                while (!bankomat3);
            }
            returnAddOrPull = accounts.get(number).accoutAction();
            temp.replace(0, temp.length(), String.valueOf(returnAddOrPull.action));
            if (bankomatCashAmount3 > 0) jLabelEvent3.setText("Состояние: банкомат полностью исправен");
            while (!temp.substring(0).equals(AccountActions.END_OF_SERVICE.getTitle())) {
                if (bankomatCashAmount3 != 0) {
                    jLabelEvent3.setText("Состояние: банкомат полностью исправен");
                    if ( (bankomatCashAmount3 < Math.abs(returnAddOrPull.addOrPull)) && (returnAddOrPull.addOrPull < 0) && temp.substring(0).equals((AccountActions.PULL_OFF.getTitle())) ) {
                        returnAddOrPull.addOrPull = -bankomatCashAmount3;
                        jTextAreaJPanel3.append(String.valueOf(AccountActions.PULL_OFF.getTitle()) + " " + Math.abs(returnAddOrPull.addOrPull) + "\n");
                        bankomatCashAmount3 += returnAddOrPull.addOrPull;
                        numberOperation3++;
                        jLabelNumberOperation3.setText("Количество завершенных операций: " + numberOperation3);
                        jLabelEvent3.setText("Состояние: банкомат недоступен для снятия");
                        myThread3.sleep(2500 - new Random().nextInt(500));
                        returnAddOrPull = accounts.get(number).accoutAction();
                        temp.replace(0, temp.length(), String.valueOf(returnAddOrPull.action));
                        continue;
                    }
                    jTextAreaJPanel3.append(temp.toString() + " " + Math.abs(returnAddOrPull.addOrPull) + "\n");
                    bankomatCashAmount3 += returnAddOrPull.addOrPull;
                    if (bankomatCashAmount3 > 0) jLabelEvent3.setText("Состояние: банкомат полностью исправен");
                    numberOperation3++;
                    jLabelNumberOperation3.setText("Количество завершенных операций: " + numberOperation3);
                    myThread3.sleep(2500 - new Random().nextInt(500));
                    tempError = new Random().nextInt(50);
                    if (tempError < 1) {//рандом ошибки
                        bankomat3 = false;
                        JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 3\n" +
                                "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        jButtonTechnicalSupport3.setEnabled(!bankomat3);
                        do {
                            myThread3.sleep(500);
                        }
                        while (!bankomat3);
                    }
                    returnAddOrPull = accounts.get(number).accoutAction();
                    temp.replace(0, temp.length(), String.valueOf(returnAddOrPull.action));
                } else {
                    jLabelEvent3.setText("Состояние: банкомат недоступен для снятия");
                    if (!temp.substring(0).equals(AccountActions.PULL_OFF.getTitle())) {
                        jTextAreaJPanel3.append(temp.toString() + " " + Math.abs(returnAddOrPull.addOrPull) + "\n");
                        bankomatCashAmount3 += returnAddOrPull.addOrPull;
                        if (bankomatCashAmount3 > 0) jLabelEvent3.setText("Состояние: банкомат полностью исправен");
                        numberOperation3++;
                        jLabelNumberOperation3.setText("Количество завершенных операций: " + numberOperation3);
                        myThread3.sleep(2500 - new Random().nextInt(500));
                        tempError = new Random().nextInt(50);
                        if (tempError < 1) {//рандом ошибки
                            bankomat3 = false;
                            JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 3\n" +
                                    "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            jButtonTechnicalSupport3.setEnabled(!bankomat3);
                            do {
                                myThread3.sleep(500);
                            }
                            while (!bankomat3);
                        }
                        returnAddOrPull = accounts.get(number).accoutAction();
                        temp.replace(0, temp.length(), String.valueOf(returnAddOrPull.action));
                    } else {
                        returnAddOrPull = accounts.get(number).accoutAction();
                        temp.replace(0, temp.length(), String.valueOf(returnAddOrPull.action));
                    }
                }
            }
            jTextAreaJPanel3.append(temp.toString() + "\n");
            numberOperation3++;
            jLabelNumberOperation3.setText("Количество завершенных операций: " + numberOperation3);
            myThread3.sleep(2500 - new Random().nextInt(500));
            tempError = new Random().nextInt(50);
            if (tempError < 1) {//рандом ошибки
                bankomat3 = false;
                JOptionPane.showMessageDialog(jFrame, "Банкомат 3 съел карту\n" +
                        "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                jButtonTechnicalSupport3.setEnabled(!bankomat3);
                do {
                    myThread3.sleep(500);
                }
                while (!bankomat3);
            } else {
                tempError = new Random().nextInt(50);
                if (tempError < 1) {//рандом ошибки
                    bankomat3 = false;
                    JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 3\n" +
                            "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    jButtonTechnicalSupport3.setEnabled(!bankomat3);
                    do {
                        myThread3.sleep(500);
                    }
                    while (!bankomat3);
                }
            }
            return;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
