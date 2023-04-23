package com.company.payroll.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.Banking;
import com.company.payroll.service.BankingService;

//@RestController
//@RequestMapping("/banking")
public class BankingController {
	
	@Autowired
	private BankingService bankingService;
	
	@GetMapping("/list")
	public List<Banking> listBankInfo() {
		return bankingService.listBankInfo();	
	}
	
	@GetMapping("/list/information/{id}")
	public Banking getBankInfoById(@PathVariable("id") int id) {
		return bankingService.getBankInfoById(id);
	}
	
	@PostMapping("/insert")
	public Integer insertBankInfo(@RequestBody Banking banking) {	
		return bankingService.insertBankInfo(banking);
	}
	
	@PutMapping("/list/information/{id}/update")
	public Integer updateBankInfo(@RequestBody Banking banking) {
		return bankingService.updateBankInfoById(banking);
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public Integer deleteBankInfo(@PathVariable("id") int id) {
		return bankingService.deleteBankInfo(id);
	}

}
