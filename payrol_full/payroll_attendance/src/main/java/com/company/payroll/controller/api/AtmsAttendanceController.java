package com.company.payroll.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.AtmsStaffAttendance;
import com.company.payroll.service.AtmsAttendanceService;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/attendance")
public class AtmsAttendanceController {

	@Autowired
	private AtmsAttendanceService atmsAttendanceService;
	
	@GetMapping("/{staffId}")
	public ResponseEntity<PageInfo<AtmsStaffAttendance>> getAllStaffAttendanceById() {
		return null;
	}
	
	@PostMapping("check_in_1/{staffId}")
	public ResponseEntity<Integer> checkInOne() {
		return null;
	}
	
	@PutMapping("check_out_1/{staffId}")
	public ResponseEntity<Integer> checkOutOne() {
		return null;
	}
	
	@PutMapping("check_in_2/{staffId}")
	public ResponseEntity<Integer> checkInTwo() {
		return null;
	}
	
	@PutMapping("check_out_2/{staffId}")
	public ResponseEntity<Integer> checkOutTwo() {
		return null;
	}
	
	@PutMapping("check_in_3/{staffId}")
	public ResponseEntity<Integer> checkInThree() {
		return null;
	}
	
	@PutMapping("check_out_3/{staffId}")
	public ResponseEntity<Integer> checkOutThree() {
		return null;
	}

}
