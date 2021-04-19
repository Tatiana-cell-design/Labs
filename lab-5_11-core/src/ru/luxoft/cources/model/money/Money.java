package ru.luxoft.cources.model.money;

import ru.luxoft.cources.model.constants.CurrencyHolder;

public class Money {
    private Currency currency;
    private double value;

    public Money(double value, String currencyName) {
        this.currency = CurrencyHolder.getCurrencyByName(currencyName);
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
