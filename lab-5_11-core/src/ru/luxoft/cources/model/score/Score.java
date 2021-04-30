package ru.luxoft.cources.model.score;

import ru.luxoft.cources.Loggable;
import ru.luxoft.cources.MethodLimit;
import ru.luxoft.cources.model.account.Account;
import ru.luxoft.cources.model.money.Money;
import ru.luxoft.cources.model.money.MoneyInterface;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class Score implements MoneyInterface {
    private static final String LIMIT_IS_OVER_MSG = "Method limit is over!";
    private final Map<String, Integer> methodConstraintMap;
    private final Map<String, Integer> methodCallMap;
    private Money balance;
    private Account owner;
    private Integer number;

    protected Score(Money balance, Account owner, Integer number) {
        this.balance = balance;
        this.owner = owner;
        this.number = number;
        this.methodConstraintMap = new HashMap<>();
        this.methodCallMap = new HashMap<>();


        for (Method method :
                this.getClass().getDeclaredMethods()) {
            MethodLimit annotation = method.getAnnotation(MethodLimit.class);
            if (annotation != null) {
                int limit = annotation.limit();
                methodConstraintMap.put(method.getName(), limit);
                methodCallMap.put(method.getName(), 0);
            }
        }

    }

    protected void logIfneeded(String methodName) {
        for (Annotation annotation :
                this.getClass().getAnnotations()) {
            if (annotation instanceof Loggable) {
                System.out.println("We call method " + methodName);
            }
        }
    }

    protected boolean isNotMethodAvailableByOperLimit(String methodName) {
        if (methodConstraintMap.containsKey(methodName)) {
            int currentCalls = methodCallMap.get(methodName);
            int limitCalls = methodConstraintMap.get(methodName);

            if (currentCalls >= limitCalls) {
                return true;
            }

            currentCalls++;
            methodCallMap.put(methodName, currentCalls);
        }
        return false;
    }

    public Money getBalance() {
        logIfneeded("getBalance");
        if (isNotMethodAvailableByOperLimit("getBalance")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return null;
        }
        return balance;
    }

    public void setBalance(Money balance) {
        logIfneeded("setBalance");
        if (isNotMethodAvailableByOperLimit("setBalance")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return;
        }
        this.balance = balance;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Integer getNumber() {
        logIfneeded("getNumber");
        if (isNotMethodAvailableByOperLimit("getNumber")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return null;
        }
        return number;
    }

    public void setNumber(Integer number) {
        logIfneeded("setNumber");
        if (isNotMethodAvailableByOperLimit("setNumber")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return;
        }

        this.number = number;
    }

    @Override
    public void addMoney(Money money) {
        logIfneeded("addMoney");
        if (isNotMethodAvailableByOperLimit("addMoney")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return;
        }

        double usdValueIn = money.getValue() * money.getCurrency().getUsdCource();
        double usdValueThis = this.balance.getValue() * this.balance.getCurrency().getUsdCource();

        if (checkBefore()) {
            this.balance.setValue((usdValueThis + usdValueIn) * this.balance.getCurrency().getUsdCource());
        } else {
            System.out.println("No check!");
        }
    }


    @Override
    public Money getMoney(double balanceLess) {
        logIfneeded("getMoney");
        if (isNotMethodAvailableByOperLimit("getMoney")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return null;
        }

        if (balanceLess > 30000) {
            throw new IllegalArgumentException("Exceeded the limit of transactions on the account (30,000)!");

        }

        this.balance.setValue(this.balance.getValue() - balanceLess);

        return this.balance;
    }

    @Override
    public Money getMoneyWithoutLess() {
        logIfneeded("getMoneyWithoutLess");
        if (isNotMethodAvailableByOperLimit("getMoneyWithoutLess")) {
            System.out.println(LIMIT_IS_OVER_MSG);
            return null;
        }


        return this.balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Score score = (Score) o;
        return balance.equals(score.getBalance())
                && owner.equals(score.getOwner())
                && number.equals(score.getNumber());
    }

    @Override
    public int hashCode() {
        int result = balance != null ? balance.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;

    }

    @Override
    public String toString() {
        return "Score{" +
                "balance=" + balance +
                ", owner=" + owner +
                ", number=" + number +
                '}';
    }

    abstract boolean checkBefore();
}

