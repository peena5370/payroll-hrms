package com.company.payroll.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.ResponseObject;

@RestController
public class SysLoginController {

	@PostMapping("/login")
	public ResponseEntity<ResponseObject> loginValidate() {
		return null;
	}
	
	@PostMapping("/logout")
	public ResponseEntity<ResponseObject> logoutValidate() {
		return null;
	}
}
