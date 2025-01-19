package com.sdk.pymt;

import java.util.Map;

import com.sdk.pymt.dto.MakePymtSDKRequest;
import com.sdk.pymt.dto.MakePymtSDKResponse;

public class PaymentService {
    private String apiKey;
    private String environment;

    public MakePymtSDKResponse makePayment(MakePymtSDKRequest request) {
        // Simulated SDK logic
        MakePymtSDKResponse response = new MakePymtSDKResponse();
        response.setTxnId("10028467593");
        response.setStatusMsg("Transaction created successfully");
        return response;
    }
}
