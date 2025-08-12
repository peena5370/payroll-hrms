package com.company.payroll.response;

public record CommonResponse(int statusCode, String statusMessage, Object data) {
}
