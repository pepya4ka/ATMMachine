package ATM;

import ATM.AccountActions;

import java.util.Random;

public class Account {

    static int numberOfAccounts = 1;
    int accountNumber;
    int amountMoney;
    boolean begin = false;//проверка на начало

    Account(int amountMoney) {
        accountNumber = numberOfAccounts;
        numberOfAccounts++;
        this.amountMoney = amountMoney;
    }

    Account() {
        accountNumber = numberOfAccounts;
        numberOfAccounts++;
        amountMoney = 500;
    }

    public void setBegin() {
        begin = false;
    }

    public String accoutAction() {
        Random randomize = new Random();
        int random = randomize.nextInt(101);
        int add = randomize.nextInt(50) * 10;
        if (add == 0) add = 1;
        StringBuffer returnAction = new StringBuffer();
        if (begin == false) {
            begin = true;
            returnAction.replace(0, returnAction.length(), AccountActions.STARTED_SERVICE.getTitle());
        } else {
            if (random < 25) {
                amountMoney += add;
                returnAction.replace(0, returnAction.length(), AccountActions.ADDED_BALANCE.getTitle() + " " + Integer.toString(add));
            }
            if (random >= 25 && random < 50) {
                int pull = new Random().nextInt(amountMoney);
                while (pull > amountMoney)
                    pull = (int) Math.random() * amountMoney;
                amountMoney -= pull;
                returnAction .replace(0, returnAction.length(), AccountActions.PULL_OFF.getTitle() + " " + Integer.toString(pull));
            }
            if (random >= 50) {
                begin = false;
                returnAction.replace(0, returnAction.length(), AccountActions.END_OF_SERVICE.getTitle());
            }

        }
        return returnAction.toString();

    }

}
