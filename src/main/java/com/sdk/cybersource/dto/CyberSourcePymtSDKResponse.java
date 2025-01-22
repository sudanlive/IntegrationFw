package com.sdk.cybersource.dto;

public class CyberSourcePymtSDKResponse {
    private String txn_id;
    private String status_msg;

    public String getTxn_id() {
        return this.txn_id;
    }

    public void setTxn_id(String txn_id) {
        this.txn_id = txn_id;
    }

    public String getStatus_msg() {
        return this.status_msg;
    }

    public void setStatus_msg(String status_msg) {
        this.status_msg = status_msg;
    }

}
