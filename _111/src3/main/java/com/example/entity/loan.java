package com.example.entity;

import java.io.Serializable;
import java.util.Date;

public class loan implements Serializable {
    private float MonthPayment;
    private float TotalInterestPaid;
    private float TotalRepaymentAmount;
    private String LoanType;
    private float LoanAmount;
    private String LoanTerm;
    private String RepaymentMethod;
    private float AnnualInterestRate;
    private Date LoanDate;
    private String  id;
    private String category;
    private String remark;

    public float getMonthPayment() {
        return MonthPayment;
    }

    public void setMonthPayment(float monthPayment) {
        MonthPayment = monthPayment;
    }

    public float getTotalInterestPaid() {
        return TotalInterestPaid;
    }

    public void setTotalInterestPaid(float totalInterestPaid) {
        TotalInterestPaid = totalInterestPaid;
    }

    public float getTotalRepaymentAmount() {
        return TotalRepaymentAmount;
    }

    public void setTotalRepaymentAmount(float totalRepaymentAmount) {
        TotalRepaymentAmount = totalRepaymentAmount;
    }

    public String getLoanType() {
        return LoanType;
    }

    public void setLoanType(String loanType) {
        LoanType = loanType;
    }

    public float getLoanAmount() {
        return LoanAmount;
    }

    public void setLoanAmount(float loanAmount) {
        LoanAmount = loanAmount;
    }

    public String getLoanTerm() {
        return LoanTerm;
    }

    public void setLoanTerm(String loanTerm) {
        LoanTerm = loanTerm;
    }

    public String getRepaymentMethod() {
        return RepaymentMethod;
    }

    public void setRepaymentMethod(String repaymentMethod) {
        RepaymentMethod = repaymentMethod;
    }

    public float getAnnualInterestRate() {
        return AnnualInterestRate;
    }

    public void setAnnualInterestRate(float annualInterestRate) {
        AnnualInterestRate = annualInterestRate;
    }

    public Date getLoanDate() {
        return LoanDate;
    }

    public void setLoanDate(Date loanDate) {
        LoanDate = loanDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "loan{" +
                "MonthPayment=" + MonthPayment +
                ", TotalInterestPaid=" + TotalInterestPaid +
                ", TotalRepaymentAmount=" + TotalRepaymentAmount +
                ", LoanType='" + LoanType + '\'' +
                ", LoanAmount=" + LoanAmount +
                ", LoanTerm='" + LoanTerm + '\'' +
                ", RepaymentMethod='" + RepaymentMethod + '\'' +
                ", AnnualInterestRate=" + AnnualInterestRate +
                ", LoanDate=" + LoanDate +
                ", id=" + id +
                ", category='" + category + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
