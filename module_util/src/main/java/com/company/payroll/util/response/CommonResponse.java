package com.company.payroll.util.response;

public record CommonResponse(int statusCode, String statusMessage, Object data) {
}
