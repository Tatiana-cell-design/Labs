package ru.luxoft.cources.model.score;

import ru.luxoft.cources.Loggable;
import ru.luxoft.cources.model.account.Account;
import ru.luxoft.cources.model.money.Money;

@Loggable
public class CreditScore extends Score {
    public CreditScore(Money balance, Account owner, Integer number) {
        super(balance, owner, number);
    }

    @Override
    boolean checkBefore() {
        return true;
    }
}
