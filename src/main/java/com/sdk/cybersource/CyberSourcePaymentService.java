package com.sdk.cybersource;

import java.util.Map;

import com.sdk.cybersource.dto.CyberSourcePymtSDKRequest;
import com.sdk.cybersource.dto.CyberSourcePymtSDKResponse;

public class CyberSourcePaymentService {
    private String apiKey;
    private String environment;

    public CyberSourcePymtSDKResponse makePayment(CyberSourcePymtSDKRequest request) {
        // Simulated SDK logic
        CyberSourcePymtSDKResponse response = new CyberSourcePymtSDKResponse();
        response.setTxn_id("10028467593");
        response.setStatus_msg("Transaction created successfully");
        return response;
    }
}
