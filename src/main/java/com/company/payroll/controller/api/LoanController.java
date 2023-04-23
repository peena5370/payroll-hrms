package com.company.payroll.controller.api;

import java.time.LocalDate;
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

import com.company.payroll.model.Loan;
import com.company.payroll.service.LoanService;
import com.company.payroll.utils.Generator;

@RestController
@RequestMapping("/loan")
public class LoanController {
	
	@Autowired
	private LoanService loanService;
	
	@GetMapping("/list")
	public List<Loan> listLoan() {
		return loanService.listLoan();
	}
	
	@GetMapping("/list/employee/{id}")
	public List<Loan> listLoanForEmployee(@PathVariable("id")int sapid) {
		return loanService.listLoanBySapId(sapid);
	}
	
	@PostMapping("/insert/{id}")
	public Integer inserLoan(@PathVariable("id")int sapid, @RequestBody Loan loan) {
		LocalDate ld = LocalDate.now();
		String date = ld.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String refnum = date + "-" + Generator.generateRandomString(8);
		String reason = loan.getReason();
		Double loanamount = loan.getLoanamount();
		
		Loan ln = new Loan(refnum, reason, loanamount, ld, sapid);
		
		return loanService.insertLoan(ln);
	}
	
	@PutMapping("/list/information/{id}/update")
	public Integer updateLoan(@PathVariable("id")int lid, @RequestBody Loan loan) {
		int loanStatus = loan.getLoanStatus();
		int mid = loan.getMId();
		LocalDate dateApproved = loan.getDateApproved();
		
		Loan ln = new Loan(lid, loanStatus, dateApproved, mid);
		
		return loanService.updateLoan(ln);
	}
}
