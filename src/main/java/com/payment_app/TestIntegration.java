package com.payment_app;

import com.fw.intg.IntegrationFramework;
import com.payment_app.dto.PymtRequest;
import com.payment_app.dto.PymtResponse;
import com.payment_app.dto.UserRequest;
import com.payment_app.dto.UserResponse;

public class TestIntegration {
    public static void main(String[] args) {
        try {
            IntegrationFramework framework = new IntegrationFramework();

            // Testing CTBC payment SDK Integration
            // Create a payment request DTO
            PymtRequest pymtRequest = new PymtRequest();
            pymtRequest.setTransactionAmount("100");
            pymtRequest.setTransactionCurrency("KRW");
            pymtRequest.setEmailAddress("sudan@coupang.com");

            // Execute the CTBC integration
            PymtResponse pymtResponse = (PymtResponse) framework.execute("CTBC_MakePaymentWithSDK", pymtRequest);

            // Print the response for CTBC integration
            System.out.println("~~~~~~~~~~~~~~~~~~~~CTBC Integration with SDK~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Txn ID: " + pymtResponse.getTransactionId());
            System.out.println("Status: " + pymtResponse.getStatusMessage());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            // Testing CyberSource payment SDK Integration
            // Execute the CyberSource integration
            pymtResponse = (PymtResponse) framework.execute("CyberSource_MakePaymentWithSDK", pymtRequest);

            // Print the response for CyberSource integration
            System.out.println("~~~~~~~~~~~~~~~~~~~~CyberSource Integration with SDK~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Txn ID: " + pymtResponse.getTransactionId());
            System.out.println("Status: " + pymtResponse.getStatusMessage());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            // Testing create user SDK Integration
            // Create a request DTO
            UserRequest userRequest = new UserRequest();
            userRequest.setFirstName("Sudan");
            userRequest.setLastName("Manick");
            userRequest.setEmail("sudan@coupang.com");

            // Execute the integration
            UserResponse userResponse = (UserResponse) framework.execute("CreateUserWithSDK", userRequest);

            // Print the response
            System.out.println("~~~~~~~~~~~~~~~~~~~~Create User SDK Integration~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("User ID: " + userResponse.getUserId());
            System.out.println("Status: " + userResponse.getStatusMessage());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            // Testing Get user Rest Integration
            // Execute the integration
            userResponse = (UserResponse) framework.execute("CreateUserWithREST", null);

            // Print the response
            System.out.println("~~~~~~~~~~~~~~~~~~~~Get User Rest Integration~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("User ID: " + userResponse.getUserId());
            System.out.println("Status: " + userResponse.getStatusMessage());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
