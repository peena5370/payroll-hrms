package com.company.payroll.util.response;

public record CommonResponse(
        int statusCode,
        String statusMessage,
        Object data) {
    public static final String COMMON_ERROR_MESSAGE = "API returned uncommon status. Please check backend log for status.";
}