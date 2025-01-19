package com.sdk.pymt.dto;

public class MakePymtSDKResponse {
    private String txnId;
    private String statusMsg;

    public String getTxnId() {
        return this.txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getStatusMsg() {
        return this.statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

}
