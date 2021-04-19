package ru.luxoft.cources.model.score;

import ru.luxoft.cources.model.account.Account;
import ru.luxoft.cources.model.money.Money;

public class DebetScore extends Score {
    private CreditScore creditScore;

    public DebetScore(Money balance, Account owner, Integer number, CreditScore creditScore) {
        super(balance, owner, number);
        this.creditScore = creditScore;
    }


    @Override
    public void addMoney(Money money){
        if(creditScore.getMoneyWithoutLess().getValue() < 20000) {
            System.out.println("Debit account closed, credit account balance less than 20,000!");
            return;
        }
        super.addMoney(money);
    }

    @Override
    public Money getMoney(double balanceLess){
        if(creditScore.getMoneyWithoutLess().getValue() < 20000) {
            System.out.println("Debit account closed, credit account balance less than 20,000!");
            return null;
        }

        if(this.getBalance().getValue() <= 0) {
            System.out.println("No money!");
            return null;
        }

        return super.getMoney(balanceLess);
    }

    @Override
    public Money getMoneyWithoutLess(){
        if(creditScore.getMoneyWithoutLess().getValue() < 20000) {
            System.out.println("No money on Credit Score!");
            return null;
        }

        return super.getMoneyWithoutLess();
    }


    @Override
    boolean checkBefore() {
        return true;
    }

}

