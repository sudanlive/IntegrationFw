package com.app.intg.pymt.dto;

public class MakePymtRequest {
    private String transactionAmount;
    private String transactionCurrency;
    private String emailAddress;


    public String getTransactionAmount() {
        return this.transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionCurrency() {
        return this.transactionCurrency;
    }

    public void setTransactionCurrency(String transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
