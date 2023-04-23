package com.company.payroll.controller.api;

import java.time.LocalDateTime;
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

import com.company.payroll.model.Account;
import com.company.payroll.service.AccountService;

import com.company.payroll.utils.PasswordEncription;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/list")
	public List<Account> listAccount(){
//		For layui table data use
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("code", 0);
//		map.put("msg", "");
//		map.put("count", accountService.countAccount());
//		map.put("data", accountService.listAccount());
//		String result = new Gson().toJson(map);
		return accountService.listAccount();
		
	}
	
	@GetMapping("/list/employee")
	public List<Account> listAccountForEmployee(){
		return accountService.listAccountForEmployee();
		
	}
	
	@GetMapping("/list/information/{id}")
	public Account getAccountById(@PathVariable("id")int id) {
		return accountService.getAccountById(id);
	}
	
	@GetMapping("/list/count/all")
	public Integer getAllAccountCount() {
		return accountService.countAccount();
	}
	
	@GetMapping("/list/count/active")
	public Integer getActiveAccountCount() {
		return accountService.countAccountByStatus();
	}
	
	@PostMapping("/register")
	public Integer registerAccount(@RequestBody Account account) {
		String username = account.getUsername();
		String password = account.getPassword();
		String key = PasswordEncription.getSaltvalue(30);
		String hash = PasswordEncription.generateSecurePassword(password, key);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		Account acc = new Account(username, hash, key, ldt, 1);

		return accountService.insertAccount(acc);
	}
	
	@PutMapping("/list/information/{id}/update/password")
	public Integer updateListPassword(@PathVariable("id")int id, @RequestBody Account account) {
		String username = account.getUsername();
		String password = account.getPassword();
		String key = PasswordEncription.getSaltvalue(30);
		String hash = PasswordEncription.generateSecurePassword(password, key);
		
		Account acc = new Account(id, username, hash, key, LocalDateTime.now());

		return accountService.updateAccountByAdmin(acc);
	}
	
	@PutMapping("/profile/update/password")
	public Integer updateAccountPassword(@RequestBody Account account) {	
		String username = account.getUsername();
		String password = account.getPassword();
		String key = PasswordEncription.getSaltvalue(30);
		String hash = PasswordEncription.generateSecurePassword(password, key);
		
		Account acc = new Account(username, hash, key, LocalDateTime.now());

		return accountService.updatePasswordByManager(acc);
	}
	
	@PutMapping("/list/information/{id}/update/status")
	public Integer updateStatus(@PathVariable("id")int id, @RequestBody Account account) {
		String username = account.getUsername();
		int accStatus = account.getAccountStatus();
		
		Account acc = new Account(id, username, LocalDateTime.now(), accStatus);

		return accountService.updateAccountStatus(acc);
	}
	
	@DeleteMapping("/list/delete/{id}")
	public Integer deleteAccount(@PathVariable("id") int id) {
		return accountService.deleteAccount(id);
	}
}
