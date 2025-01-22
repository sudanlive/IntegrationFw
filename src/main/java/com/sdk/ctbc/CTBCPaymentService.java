package com.sdk.ctbc;

import java.util.Map;

import com.sdk.ctbc.dto.CTBCPymtSDKRequest;
import com.sdk.ctbc.dto.CTBCPymtSDKResponse;

public class CTBCPaymentService {
    private String apiKey;
    private String environment;

    public CTBCPymtSDKResponse makePayment(CTBCPymtSDKRequest request) {
        // Simulated SDK logic
        CTBCPymtSDKResponse response = new CTBCPymtSDKResponse();
        response.setTxnId("10028467593");
        response.setStatusMsg("Transaction created successfully");
        return response;
    }
}
