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

import com.company.payroll.model.Loan;
import com.company.payroll.service.LoanService;

@RestController
@RequestMapping("/loan")
public class LoanController {
	
	private LoanService loanService;
	
	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Loan>> listLoan() {
		return ResponseEntity.ok(loanService.getList());
	}
	
	@GetMapping("/{id}/list")
	public ResponseEntity<List<Loan>> listLoanByEId(@PathVariable("id")int eid) {
		return ResponseEntity.ok(loanService.getListByEId(eid));
	}
	
	@GetMapping("/list/information/{id}")
	public ResponseEntity<Loan> getById(@PathVariable("id")int lid) {
		return ResponseEntity.ok(loanService.getById(lid));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Integer> insert(@RequestBody Loan loan) {
		Integer status = loanService.insert(loan);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> update(@RequestBody Loan loan) {
		Integer status = loanService.update(loan);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> delete(@PathVariable("id")int lid) {
		Integer status = loanService.delete(lid);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
}
