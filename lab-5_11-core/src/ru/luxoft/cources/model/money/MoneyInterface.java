package ru.luxoft.cources.model.money;

public interface MoneyInterface {
    void addMoney(Money money);

    Money getMoney(double balanceLess);

    Money getMoneyWithoutLess();

}
