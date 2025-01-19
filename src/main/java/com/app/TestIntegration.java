package com.app;

import com.app.intg.pymt.dto.MakePymtRequest;
import com.app.intg.pymt.dto.MakePymtResponse;
import com.app.intg.user.dto.CreateUserRequest;
import com.app.intg.user.dto.CreateUserResponse;
import com.fw.intg.IntegrationFramework;

public class TestIntegration {
    public static void main(String[] args) {
        try {
            IntegrationFramework framework = new IntegrationFramework();

            // Testing payment Integration

            // Create a payment request DTO
            MakePymtRequest pymtRequest = new MakePymtRequest();
            pymtRequest.setTransactionAmount("100");
            pymtRequest.setTransactionCurrency("KRW");
            pymtRequest.setEmailAddress("sudan@coupang.com");

            // Execute the integration
            MakePymtResponse pymtResponse = (MakePymtResponse) framework.execute("MakePaymentWithSDK", pymtRequest);

            // Print the response
            System.out.println("Txn ID: " + pymtResponse.getTransactionId());
            System.out.println("Status: " + pymtResponse.getStatusMessage());

            // Testing user Integration

            // Create a request DTO
            CreateUserRequest userRequest = new CreateUserRequest();
            userRequest.setFirstName("Sudan");
            userRequest.setLastName("Manick");
            userRequest.setEmail("sudan@coupang.com");

            // Execute the integration
            CreateUserResponse userResponse = (CreateUserResponse) framework.execute("CreateUserWithSDK", userRequest);

            // Print the response
            System.out.println("User ID: " + userResponse.getUserId());
            System.out.println("Status: " + userResponse.getStatusMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
