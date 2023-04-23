package com.company.payroll.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ResponseObject {
	private int code;
	private String msg;
	private String httpUrl;
}
