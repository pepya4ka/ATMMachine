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

    private JPanel jPanel;//в нем находятся JLabels и Scrollers
    private JPanel jPanelForButton;//панель для кнопок

    JLabel jLabelJPanel1;//1 банкомат
    static JTextArea jTextAreaJPanel1;

    JLabel jLabelJPanel2;//2 банкомат
    static JTextArea jTextAreaJPanel2;

    JLabel jLabelJPanel3;//3 банкомат
    static JTextArea jTextAreaJPanel3;

    static JButton jButtonBeginAndEnd;//кнопка начать/закончить
    static JButton jButtonTechnicalSupport1;//кнопка вызова технической поддержки для банкомата 1
    static JButton jButtonTechnicalSupport2;//кнопка вызова технической поддержки для банкомата 2
    static JButton jButtonTechnicalSupport3;//кнопка вызова технической поддержки для банкомата 3

    static Runnable textArea1, textArea2, textArea3;
    static Thread myThread1, myThread2, myThread3;

    static MyDrawPanel drawPanel;
    Color xColor;
    static Runnable runnableCircle, runnableGo;
    static Thread threadCircle, threadGo;

    static ArrayList<Account> accounts;
    static HashSet<Integer> setNumberAccounts;

    static boolean fl = false;//работает или нет
    static boolean bankomat1 = true;//работа банкомата 1
    static boolean bankomat2 = true;//работа банкомата 1
    static boolean bankomat3 = true;//работа банкомата 1

    public void go() {

        jFrame = new JFrame("Система банкоматов");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jLabelJPanel1 = new JLabel("1 банкомат");
        jTextAreaJPanel1 = new JTextArea(2, 3);
        JScrollPane scroller1 = new JScrollPane(jTextAreaJPanel1);
        jTextAreaJPanel1.setLineWrap(true);
        scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jTextAreaJPanel1.setEditable(false);//запрещает изменять текст в поле

        jLabelJPanel2 = new JLabel("2 банкомат");
        jTextAreaJPanel2 = new JTextArea(2, 3);
        JScrollPane scroller2 = new JScrollPane(jTextAreaJPanel2);
        jTextAreaJPanel2.setLineWrap(true);
        scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jTextAreaJPanel2.setEditable(false);//запрещает изменять текст в поле

        jLabelJPanel3 = new JLabel("3 банкомат");
        jTextAreaJPanel3 = new JTextArea(2, 3);
        JScrollPane scroller3 = new JScrollPane(jTextAreaJPanel3);
        jTextAreaJPanel3.setLineWrap(true);
        scroller3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jTextAreaJPanel3.setEditable(false);//запрещает изменять текст в поле

        jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 2));
        jPanel.add(jLabelJPanel1);
        jPanel.add(scroller1);
        jPanel.add(jLabelJPanel2);
        jPanel.add(scroller2);
        jPanel.add(jLabelJPanel3);
        jPanel.add(scroller3);


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
            StringBuffer temp = new StringBuffer(accounts.get(number).accoutAction());
            jTextAreaJPanel1.append(String.valueOf(number) + " " + temp.toString() + "\n");
            myThread1.sleep(2500 - new Random().nextInt(500));
            if ((new Random().nextInt(50)) < 2) {
                bankomat1 = false;
                JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 1\n" +
                        "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                jButtonTechnicalSupport1.setEnabled(!bankomat1);
                do {
                    myThread1.sleep(500);
                }
                while (!bankomat1);
            }
            temp.replace(0, temp.length(), accounts.get(number).accoutAction());
            while (!temp.substring(0).equals(AccountActions.END_OF_SERVICE.getTitle())) {
                jTextAreaJPanel1.append(temp.toString() + "\n");
                myThread1.sleep(2500 - new Random().nextInt(500));
                if ((new Random().nextInt(50)) < 2) {
                    bankomat1 = false;
                    JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 1\n" +
                            "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    jButtonTechnicalSupport1.setEnabled(!bankomat1);
                    do {
                        myThread1.sleep(500);
                    }
                    while (!bankomat1);
                }
                temp.replace(0, temp.length(), accounts.get(number).accoutAction());
            }
            jTextAreaJPanel1.append(temp.toString() + "\n");
            myThread1.sleep(2500 - new Random().nextInt(500));
            if ((new Random().nextInt(25)) < 2) {
                bankomat1 = false;
                JOptionPane.showMessageDialog(jFrame, "Банкомат 1 съел карту\n" +
                        "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                jButtonTechnicalSupport1.setEnabled(!bankomat1);
                do {
                    myThread1.sleep(500);
                }
                while (!bankomat1);
            } else if ((new Random().nextInt(50)) < 2) {
                bankomat1 = false;
                JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 1\n" +
                        "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                jButtonTechnicalSupport1.setEnabled(!bankomat1);
                do {
                    myThread1.sleep(500);
                }
                while (!bankomat1);
            }
            return;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runAccountActions2(int number) {
        try {
            setNumberAccounts.remove(number);
            StringBuffer temp = new StringBuffer(accounts.get(number).accoutAction());
            jTextAreaJPanel2.append(String.valueOf(number) + " " + temp.toString() + "\n");
            myThread2.sleep(2500 - new Random().nextInt(500));
            if ((new Random().nextInt(55))< 2) {
                bankomat2 = false;
                JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 2\n" +
                        "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                jButtonTechnicalSupport2.setEnabled(!bankomat2);
                do {
                    myThread2.sleep(500);
                }
                while (!bankomat2);
            }
            temp.replace(0, temp.length(), accounts.get(number).accoutAction());
            while (!temp.substring(0).equals(AccountActions.END_OF_SERVICE.getTitle())) {
                jTextAreaJPanel2.append(temp.toString() + "\n");
                myThread2.sleep(2500 - new Random().nextInt(500));
                if ((new Random().nextInt(55)) < 2) {
                    bankomat2 = false;
                    JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 2\n" +
                            "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    jButtonTechnicalSupport2.setEnabled(!bankomat2);
                    do {
                        myThread2.sleep(500);
                    }
                    while (!bankomat2);
                }
                temp.replace(0, temp.length(), accounts.get(number).accoutAction());
            }
            jTextAreaJPanel2.append(temp.toString() + "\n");
            myThread2.sleep(2500 - new Random().nextInt(500));
            if ((new Random().nextInt(25)) < 2) {
                bankomat2 = false;
                JOptionPane.showMessageDialog(jFrame, "Банкомат 2 съел карту\n" +
                        "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                jButtonTechnicalSupport2.setEnabled(!bankomat2);
                do {
                    myThread2.sleep(500);
                }
                while (!bankomat2);
            } else if ((new Random().nextInt(55)) < 2) {
                bankomat2 = false;
                JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 2\n" +
                        "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                jButtonTechnicalSupport2.setEnabled(!bankomat2);
                do {
                    myThread2.sleep(500);
                }
                while (!bankomat2);
            }
            return;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runAccountActions3(int number) {
        try {
            setNumberAccounts.remove(number);
            StringBuffer temp = new StringBuffer(accounts.get(number).accoutAction());
            jTextAreaJPanel3.append(String.valueOf(number) + " " + temp.toString() + "\n");
            myThread3.sleep(2500 - new Random().nextInt(500));
            if ((new Random().nextInt(10)) < 2) {
                bankomat3 = false;
                JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 3\n" +
                        "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                jButtonTechnicalSupport3.setEnabled(!bankomat3);
                do {
                    myThread3.sleep(500);
                }
                while (!bankomat3);
            }
            temp.replace(0, temp.length(), accounts.get(number).accoutAction());
            while (!temp.substring(0).equals(AccountActions.END_OF_SERVICE.getTitle())) {
                jTextAreaJPanel3.append(temp.toString() + "\n");
                myThread3.sleep(2500 - new Random().nextInt(500));
                if ((new Random().nextInt(10)) < 2) {
                    bankomat3 = false;
                    JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 3\n" +
                            "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    jButtonTechnicalSupport3.setEnabled(!bankomat3);
                    do {
                        myThread3.sleep(500);
                    }
                    while (!bankomat3);
                }
                temp.replace(0, temp.length(), accounts.get(number).accoutAction());
            }
            jTextAreaJPanel3.append(temp.toString() + "\n");
            myThread3.sleep(2500 - new Random().nextInt(500));
            if ((new Random().nextInt(25)) < 1) {
                bankomat3 = false;
                JOptionPane.showMessageDialog(jFrame, "Банкомат 3 съел карту\n" +
                        "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                jButtonTechnicalSupport3.setEnabled(!bankomat3);
                do {
                    myThread3.sleep(500);
                }
                while (!bankomat3);
            } else if ((new Random().nextInt(10)) < 2) {
                bankomat3 = false;
                JOptionPane.showMessageDialog(jFrame, "Ошибка подключения к серверу у банкомата 3\n" +
                        "Пожалуйста, вызовете тех.поддержку", "Ошибка", JOptionPane.ERROR_MESSAGE);
                jButtonTechnicalSupport3.setEnabled(!bankomat3);
                do {
                    myThread3.sleep(500);
                }
                while (!bankomat3);
            }
            return;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
