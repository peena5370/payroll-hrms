package com.company.payroll.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.BankingInfo;
import com.company.payroll.service.BankingService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/banking")
public class BankingController {
	
	@Autowired
	private BankingService bankingService;

	@Operation(summary="Get banking list")
	@GetMapping
	public ResponseEntity<PageInfo<BankingInfo>> listBankingInfo(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(bankingService.list(page, offset));	
	}
	
	@Operation(summary="Get banking info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<BankingInfo>> getById(@Parameter(description="Banking id") @PathVariable("id") int bid) {
		return ResponseEntity.ok(bankingService.findById(bid));
	}
	
	@Operation(summary="Update banking info.")
	@PutMapping("/{id}")
	public ResponseEntity<BankingInfo> update(@RequestBody BankingInfo bankingInfo) {
		return ResponseEntity.ok(bankingService.update(bankingInfo));
	}
}
