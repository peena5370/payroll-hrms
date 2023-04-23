package com.company.payroll.controller.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.Leave;
import com.company.payroll.service.LeaveService;
import com.company.payroll.utils.Generator;

@RestController
@RequestMapping("/leave")
public class LeaveController {
	
	@Autowired
	private LeaveService leaveService;
	
	@GetMapping("/list")
	public List<Leave> listLeave() {
		return leaveService.listLeave();
	}
	
	@GetMapping("/list/employee/{id}")
	public List<Leave> listLeaveForEmployee(@PathVariable("id")int sapid) {
		return leaveService.listLeaveBySapId(sapid);
	}
	
	@PostMapping("/insert")
	public Integer insertLeave(@RequestBody Leave leave) {
		LocalDate ld = LocalDate.now();
		String date = ld.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String refnum = date + "-" + Generator.generateRandomString(8);
		String leaveType = leave.getLeaveType();
		String reason = leave.getReason();
		LocalDateTime dateFrom = leave.getDateFrom();
		LocalDateTime dateTo = leave.getDateTo();
		int sapid = leave.getESapId();
		
		Leave lv = new Leave(refnum, leaveType, reason, ld, dateFrom, dateTo, sapid);
		
		return leaveService.insertLeave(lv);
	}
	
	@PutMapping("/list/information/{id}/update")
	public Integer updateLeaveStatus(@PathVariable("id")int id, @RequestBody Leave leave) {
		int leaveStatus = leave.getLeaveStatus();
		int mid = leave.getMId();
		LocalDate approvedDate = leave.getApprovedDate();
		
		Leave lv = new Leave(id, leaveStatus, approvedDate, mid);
		
		return leaveService.updateLeave(lv);
	}

}
