package com.company.payroll.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.HmsStaffJobTitle;
import com.company.payroll.service.HmsStaffDetailService;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private HmsStaffDetailService staffDetailService;
	
	@PostMapping("/submit")
	public ResponseEntity<Integer> insertTitle(@RequestBody HmsStaffJobTitle jobTitle) {
		System.out.println("controller: " + jobTitle.getName() + "\t " + jobTitle.getDescription());
		return ResponseEntity.ok(staffDetailService.createJobTitle(jobTitle));
	}
}
