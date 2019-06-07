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

    public ReturnAddOrPull accoutAction() {
        ReturnAddOrPull returnAction = new ReturnAddOrPull();
        Random randomize = new Random();
        int random = randomize.nextInt(101);
        int add = randomize.nextInt(50) * 10;
        if (add == 0) add = 1;
        if (begin == false) {
            begin = true;
            returnAction.action.replace(0, returnAction.action.length(), AccountActions.STARTED_SERVICE.getTitle());
        } else {
            if (random < 25) {
                amountMoney += add;
                returnAction.action.replace(0, returnAction.action.length(), AccountActions.ADDED_BALANCE.getTitle());
                returnAction.addOrPull = add;
            }
            if (random >= 25 && random < 50) {
                int pull = new Random().nextInt(amountMoney);
                while (pull > amountMoney)
                    pull = (int) Math.random() * amountMoney;
                amountMoney -= pull;
                returnAction.action.replace(0, returnAction.action.length(), AccountActions.PULL_OFF.getTitle());
                returnAction.addOrPull = -pull;
            }
            if (random >= 50) {
                begin = false;
                returnAction.action.replace(0, returnAction.action.length(), AccountActions.END_OF_SERVICE.getTitle());
            }

        }
        return returnAction;

    }

}
