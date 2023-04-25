package com.company.payroll.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.BankingInfo;
import com.company.payroll.service.BankingService;

@RestController
@RequestMapping("/banking")
public class BankingController {

	private BankingService bankingService;
	
	public BankingController(BankingService bankingService) {
		this.bankingService = bankingService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<BankingInfo>> listBankingInfo() {
		return ResponseEntity.ok(bankingService.getList());	
	}
	
	@GetMapping("/list/information/{id}")
	public ResponseEntity<BankingInfo> getById(@PathVariable("id") int bid) {
		return ResponseEntity.ok(bankingService.getById(bid));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Integer> insertBankInfo(@RequestBody BankingInfo bankingInfo) {
		Integer status = bankingService.insert(bankingInfo);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> updateBankInfo(@RequestBody BankingInfo bankingInfo) {
		Integer status = bankingService.updateById(bankingInfo);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> deleteBankInfo(@PathVariable("id") int bid) {
		Integer status = bankingService.delete(bid);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
}
