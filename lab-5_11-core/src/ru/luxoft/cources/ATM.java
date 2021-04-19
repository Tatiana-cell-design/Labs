package ru.luxoft.cources;

import ru.luxoft.cources.model.constants.CurrencyHolder;
import ru.luxoft.cources.model.money.Money;
import ru.luxoft.cources.model.score.CreditScore;
import ru.luxoft.cources.model.score.CurrentScore;
import ru.luxoft.cources.model.score.DebetScore;

import java.lang.annotation.Annotation;

public class ATM {
    private CurrentScore currentScore;
    private DebetScore debetScore;
    private CreditScore creditScore;
    private int operLimit;
    private int currentOpers;
    private boolean operLimitToggl;

    public ATM() {
        Class thisClass = this.getClass();
        for (Annotation annotation:
                thisClass.getAnnotations()) {
            if(annotation instanceof OperationLimitATM){
                this.operLimit = ((OperationLimitATM)annotation).limit();
                this.operLimitToggl = true;
            }
        }

        this.creditScore =
                new CreditScore(
                        new Money(1000.0d,
                                CurrencyHolder.getCurrencyByName("RUR").getName()
                        ), null, 1);
        this.debetScore =
                new DebetScore(
                        new Money(1000.0d,
                                CurrencyHolder.getCurrencyByName("RUR").getName()
                        ), null, 1, this.creditScore);
        this.currentScore =
                new CurrentScore(
                        new Money(1000.0d,
                                CurrencyHolder.getCurrencyByName("RUR").getName()
                        ), null, 1, this.debetScore);
    }

    public ATM(CurrentScore currentScore, DebetScore debetScore, CreditScore creditScore) {
        this.currentScore = currentScore;
        this.debetScore = debetScore;
        this.creditScore = creditScore;

        Class thisClass = this.getClass();
        for (Annotation annotation:
                thisClass.getAnnotations()) {
            if(annotation instanceof OperationLimitATM){
                this.operLimit = ((OperationLimitATM)annotation).limit();
                this.operLimitToggl = true;
            }
        }
    }
    public void addMoneyToScore(Money money, ScoreTypeEnum choice){
        if(operLimitToggl && currentOpers >= operLimit){
            System.out.println("Oper limit ends!");
            return;
        }

        switch (choice){
            case CREDIT:
                this.creditScore.addMoney(money);
                break;
            case DEBET:
                this.debetScore.addMoney(money);
                break;
            case CURRENT:
                this.currentScore.addMoney(money);
                break;
        }
        currentOpers++;
    }

    public CurrentScore getCurrentScore() {
         return currentScore;
    }

    public void setCurrentScore(CurrentScore currentScore) {
         this.currentScore = currentScore;
    }

    public DebetScore getDebetScore() {
         return debetScore;
    }

    public void setDebetScore(DebetScore debetScore) {
         this.debetScore = debetScore;
    }

    public CreditScore getCreditScore() {
         return creditScore;
    }

    public void setCreditScore(CreditScore creditScore) {
        this.creditScore = creditScore;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ATM atm = (ATM) o;

        if (operLimit != atm.operLimit) return false;
        if (currentOpers != atm.currentOpers) return false;
        if (operLimitToggl != atm.operLimitToggl) return false;
        if (currentScore != null ? !currentScore.equals(atm.currentScore) : atm.currentScore != null) return false;
        if (debetScore != null ? !debetScore.equals(atm.debetScore) : atm.debetScore != null) return false;
        return creditScore != null ? creditScore.equals(atm.creditScore) : atm.creditScore == null;
    }

    @Override
    public int hashCode() {
        int result = currentScore != null ? currentScore.hashCode() : 0;
        result = 31 * result + (debetScore != null ? debetScore.hashCode() : 0);
        result = 31 * result + (creditScore != null ? creditScore.hashCode() : 0);
        result = 31 * result + operLimit;
        result = 31 * result + currentOpers;
        result = 31 * result + (operLimitToggl ? 1 : 0);
        return result;
    }

    private void copy(ATM atm){
        this.currentScore = atm.currentScore;
        this.debetScore = atm.debetScore;
        this.creditScore = atm.creditScore;
        this.operLimit =  atm.operLimit;
        this.currentOpers = atm.currentOpers;
        this.operLimitToggl = atm.operLimitToggl;
    }


}
