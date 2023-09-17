package com.company.payroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseObject {
	
	private int code;
	
	private String msg;
}
