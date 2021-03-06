package com.hepexta.refactoring.simplification.replaceconditwithstrategy;

import com.hepexta.refactoring.simplification.replaceconditwithstrategy.strategy.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Loan {

    private final double commitment;
    private final double outstanding;
    private final LoanStrategy strategy;
    private Date start;
    private Date maturity;
    private Date expiry;
    private List<Payment> payments = new ArrayList<>();

    private double riskRating;
    private CapitalStrategy capitalStrategy;

    public Loan(LoanStrategy strategy, double commitment, Date start, Date maturity, Date expiry, double highRiskRating, CapitalStrategy capitalStrategy) {
        this.riskRating = highRiskRating;
        this.maturity = maturity;
        this.start = start;
        this.expiry = expiry;
        this.strategy = strategy;
        this.commitment = commitment;
        this.outstanding = 1;
        this.capitalStrategy = capitalStrategy;
    }

    public static Loan newTermLoan(double amount, Date start, Date maturity, double highRiskRating) {
        return new Loan(new TermROC(), amount, start, maturity, null, highRiskRating, new CapitalStrategyTermLoan());
    }

    public static Loan newRevolver(double commitment, Date start, Date expiry, double riskRating) {
        return new Loan(new Resolver(), commitment, start, null, expiry, riskRating, new CapitalStrategyRevolver());
    }

    public double capital() {
        return capitalStrategy.capital(this);
    }

    public double duration() {
        return capitalStrategy.duration(this);
    }

    public double riskFactor() {
        return riskRating*2;
    }

    public double unusedRiskFactor() {
        return riskRating;
    }

    public double outstandingRiskAmount() {
        return outstanding;
    }

    public double unusedRiskAmount() {
        return (commitment - outstanding);
    }


    public float getUnusedPercentage() {
        return 4;
    }

    public void payment(double amount, Date date) {
        this.payments.add(Payment.payment(amount, date));
    }

    public Date getExpiry() {
        return expiry;
    }

    public Date getMaturity() {
        return maturity;
    }

    public double getCommitment() {
        return commitment;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public Date getStart() {
        return start;
    }
}
