package com.company.payroll.controller.api;

import java.util.Optional;

import com.company.payroll.service.StaffBankingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.StaffBankingInfo;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/banking")
public class BankingController {
	
	@Autowired
	private StaffBankingInfoService staffBankingInfoService;

	@Operation(summary="Get banking list")
	@GetMapping
	public ResponseEntity<PageInfo<StaffBankingInfo>> listBankingInfo(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffBankingInfoService.list(page, offset));
	}
	
	@Operation(summary="Get banking info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<StaffBankingInfo>> getById(@Parameter(description="Banking id") @PathVariable("id") int bid) {
		return ResponseEntity.ok(staffBankingInfoService.findById(bid));
	}
	
	@Operation(summary="Update banking info.")
	@PutMapping("/{id}")
	public ResponseEntity<StaffBankingInfo> update(@RequestBody StaffBankingInfo bankingInfo) {
		return ResponseEntity.ok(staffBankingInfoService.update(bankingInfo));
	}
}
