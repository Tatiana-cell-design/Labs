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
    private Money balance;
    private Account owner;
    private Integer number;
    private Map<String, Integer> methodConstraintMap;
    private Map<String, Integer> methodCallMap;
    private boolean methodConstraintToggl;

    public Score(Money balance, Account owner, Integer number) {
        this.balance = balance;
        this.owner = owner;
        this.number = number;
        this.methodConstraintMap = new HashMap<String, Integer>();
        this.methodCallMap = new HashMap<String, Integer>();


        Class thisClass = this.getClass();
        for (Method method:
                thisClass.getDeclaredMethods()) {
            for (Annotation annotation:
                    method.getDeclaredAnnotations()) {
                if(annotation instanceof MethodLimit){
                    int limit = ((MethodLimit)annotation).limit();
                    methodConstraintMap.put(method.getName(), limit);
                    methodCallMap.put(method.getName(), 0);
                    methodConstraintToggl = true;
                }
            }
        }

    }

    protected void logIfneeded(String methodName){
        Class thisClass = this.getClass();
        for (Annotation annotation:
                thisClass.getAnnotations()) {
            if(annotation instanceof Loggable){
                System.out.println("We call method " + methodName);
            }
        }
    }

    protected boolean isMethodAvailableByOperLimit(String methodName){
        if(methodConstraintMap.containsKey(methodName)){
            int currentCalls = methodCallMap.get(methodName);
            int limitCalls = methodConstraintMap.get(methodName);

            if(currentCalls >= limitCalls){
                return false;
            }

            currentCalls++;
            methodCallMap.put(methodName, currentCalls);
        }
        return true;
    }

    public Money getBalance() {
        logIfneeded("getNumber");
        if(!isMethodAvailableByOperLimit("getBalance")){
            System.out.println("Method limit is over!");
            return null;
        }
        return balance;
    }

    public void setBalance(Money balance) {
        logIfneeded("getNumber");
        if(!isMethodAvailableByOperLimit("getBalance")){
            System.out.println("Method limit is over!");
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
        if(!isMethodAvailableByOperLimit("getBalance")){
            System.out.println("Method limit is over!");
            return null;
        }
        return number;
    }

    public void setNumber(Integer number) {
        logIfneeded("getNumber");
        if(!isMethodAvailableByOperLimit("getBalance")){
            System.out.println("Method limit is over!");
            return;
        }

        this.number = number;
    }

    @Override
    public void addMoney(Money money){
        logIfneeded("getBalance");
        if(!isMethodAvailableByOperLimit("getBalance")){
            System.out.println("Method limit is over!");
            return;
        }

        double usdValueIn = money.getValue() * money.getCurrency().getUsdCource() ;
        double usdValueThis = this.balance.getValue() * this.balance.getCurrency().getUsdCource();

        if(checkBefore()) {
            this.balance.setValue((usdValueThis + usdValueIn) * this.balance.getCurrency().getUsdCource());
        } else {
            System.out.println("No check!");
            return;
        }
    }


    @Override
    public Money getMoney(double balanceLess){
        logIfneeded("getBalance");
        if(!isMethodAvailableByOperLimit("getBalance")){
            System.out.println("Method limit is over!");
            return null;
        }

        if(balanceLess > 30000) {
            throw new IllegalArgumentException("Exceeded the limit of transactions on the account (30,000)!");

        }

        this.balance.setValue(this.balance.getValue() - balanceLess);

        return this.balance;
    }

    @Override
    public Money getMoneyWithoutLess(){
        logIfneeded("getBalance");
        if(!isMethodAvailableByOperLimit("getBalance")){
            System.out.println("Method limit is over!");
            return null;
        }


        return this.balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;
        Score score = (Score) o;
        return getBalance().equals(score.getBalance()) && getOwner().equals(score.getOwner()) && getNumber().equals(score.getNumber());
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

