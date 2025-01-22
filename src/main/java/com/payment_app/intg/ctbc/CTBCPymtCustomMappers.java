package com.payment_app.intg.ctbc;

public class CTBCPymtCustomMappers {
    public static String mapEmail(String email) {
        // Custom logic to transform the email
        return email.toLowerCase();
    }

    public static String mapStatus(String status) {
        // Custom logic to transform the status
        return "Processed, " + status;
    }
}
