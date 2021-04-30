package ru.luxoft.cources;

import ru.luxoft.cources.model.constants.CurrencyHolder;
import ru.luxoft.cources.model.money.Money;
import ru.luxoft.cources.model.score.CreditScore;
import ru.luxoft.cources.model.score.CurrentScore;
import ru.luxoft.cources.model.score.DebetScore;
import ru.luxoft.cources.model.score.Score;

import java.lang.annotation.Annotation;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class ATM {
    private Map<ScoreTypeEnum, Score> map = new EnumMap<>(ScoreTypeEnum.class);
    private CurrentScore currentScore;
    private DebetScore debetScore;
    private CreditScore creditScore;
    private int operLimit;
    private int currentOpers;
    private boolean operLimitToggl;

    public ATM(CurrentScore currentScore, DebetScore debetScore, CreditScore creditScore) {
        this.currentScore = currentScore;
        this.debetScore = debetScore;
        this.creditScore = creditScore;

        for (Annotation annotation :
                this.getClass().getAnnotations()) {
            if (annotation instanceof OperationLimitATM) {
                this.operLimit = ((OperationLimitATM) annotation).limit();
                this.operLimitToggl = true;
            }
        }
        map.put(ScoreTypeEnum.CREDIT, creditScore);
        map.put(ScoreTypeEnum.DEBET, debetScore);
        map.put(ScoreTypeEnum.CURRENT, currentScore);
    }

    public static ATM getDefaultATM() {

        CreditScore credit = new CreditScore(
                new Money(1000.0d,
                        CurrencyHolder.getCurrencyByName("RUR").getName()
                ), null, 1);
        DebetScore debet = new DebetScore(
                new Money(1000.0d,
                        CurrencyHolder.getCurrencyByName("RUR").getName()
                ), null, 1, credit);
        CurrentScore current = new CurrentScore(
                new Money(1000.0d,
                        CurrencyHolder.getCurrencyByName("RUR").getName()
                ), null, 1, debet);
        return new ATM(current, debet, credit);
    }

    public void addMoneyToScore(Money money, ScoreTypeEnum choice) {
        if (operLimitToggl && currentOpers >= operLimit) {
            System.out.println("Oper limit ends!");
            return;
        }

        map.get(choice).addMoney(money);

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
        if (!Objects.equals(currentScore, atm.currentScore)) return false;
        if (!Objects.equals(debetScore, atm.debetScore)) return false;
        return Objects.equals(creditScore, atm.creditScore);
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

    public void copyFrom(ATM atm) {
        this.currentScore = atm.currentScore;
        this.debetScore = atm.debetScore;
        this.creditScore = atm.creditScore;
        this.operLimit = atm.operLimit;
        this.currentOpers = atm.currentOpers;
        this.operLimitToggl = atm.operLimitToggl;
        this.map = atm.map;
    }

}
