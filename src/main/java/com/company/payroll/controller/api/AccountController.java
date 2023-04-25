package com.company.payroll.controller.api;

import java.security.NoSuchAlgorithmException;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.company.payroll.utils.PasswordEncryption;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	private AccountService accountService;
	
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Account>> listAccount(){
		return ResponseEntity.ok(accountService.getList());	
	}
	
	@GetMapping("/list/information/{id}")
	public Account getById(@PathVariable("id")int id) {
		return accountService.getById(id);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Integer> insert(@RequestBody Account account) throws NoSuchAlgorithmException {
		String plainPassword = account.getPassword();
		Byte accountStatus = 1;
		String secretkey = PasswordEncryption.convertSecretKeyToString(PasswordEncryption.generateSecretKey("HmacSHA256", 256));
		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", PasswordEncryption.generatePasswordEncoder(secretkey));
		String encodedPassword = passwordEncoder.encode(plainPassword);
		
		Account row = new Account(account.getUsername(), encodedPassword, secretkey, account.getRoles(), account.getDateCreated(), 
									accountStatus, account.getMId(), account.getEId());
		
		Integer status = accountService.insert(row);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@PutMapping("/list/information/{id}/password/update")
	public ResponseEntity<Integer> listUpdatePassword(@RequestBody Account account) throws NoSuchAlgorithmException {
		String plainPassword = account.getPassword();
		String secretkey = PasswordEncryption.convertSecretKeyToString(PasswordEncryption.generateSecretKey("HmacSHA256", 256));
		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", PasswordEncryption.generatePasswordEncoder(secretkey));
		String encodedPassword = passwordEncoder.encode(plainPassword);
		
		Account row = new Account(account.getAId(), account.getUsername(), encodedPassword, secretkey, account.getDateModified());

		Integer status = accountService.updateListPassword(row);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> update(@RequestBody Account account) {
		Integer status = accountService.update(account);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> delete(@PathVariable("id") int aid) {
		Integer status = accountService.delete(aid);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
//	
//	@GetMapping("/list/count/all")
//	public Integer getAllAccountCount() {
//		return accountService.countAccount();
//	}

//	@GetMapping("/list/count/active")
//	public Integer getActiveAccountCount() {
//		return accountService.countAccountByStatus();
//	}

//	@PutMapping("/profile/update/password")
//	public Integer profileUpdatePassword(@RequestBody Account account) {	
//		String username = account.getUsername();
//		String password = account.getPassword();
//		String key = PasswordEncription.getSaltvalue(30);
//		String hash = PasswordEncription.generateSecurePassword(password, key);
//		
//		Account acc = new Account(username, hash, key, LocalDateTime.now());
//
//		return accountService.updatePasswordByManager(acc);
//	}
}
