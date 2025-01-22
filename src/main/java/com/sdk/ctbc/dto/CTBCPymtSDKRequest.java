package com.sdk.ctbc.dto;

public class CTBCPymtSDKRequest {
    private String txnAmount;
    private String txnCurr;
    private String emailAddr;

    public String getTxnAmount() {
        return this.txnAmount;
    }

    public void setTxnAmount(String txnAmount) {
        this.txnAmount = txnAmount;
    }

    public String getTxnCurr() {
        return this.txnCurr;
    }

    public void setTxnCurr(String txnCurr) {
        this.txnCurr = txnCurr;
    }

    public String getEmailAddr() {
        return this.emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

}
