package com.sdk.user;

import java.util.Map;

import com.sdk.user.dto.CreateUserSDKRequest;
import com.sdk.user.dto.CreateUserSDKResponse;

public class UserManagement {
    private String apiKey;
    private String environment;

    public CreateUserSDKResponse createUser(CreateUserSDKRequest request) {
        // Simulated SDK logic
        CreateUserSDKResponse response = new CreateUserSDKResponse();
        response.setUId("12345");
        response.setStatusMsg("User created successfully");
        return response;
    }
}
