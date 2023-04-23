package com.company.payroll.controller.api;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	private static final PasswordEncryption passwordEncryption = new PasswordEncryption();
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/list")
	public ResponseEntity<List<Account>> listAccount(){
		List<Account> list = accountService.getList();
		return ResponseEntity.status(HttpStatus.OK).body(list);	
	}
	
	@GetMapping("/list/information/{id}")
	public Account getById(@PathVariable("id")int id) {
		return accountService.getById(id);
	}
//	
//	@GetMapping("/list/count/all")
//	public Integer getAllAccountCount() {
//		return accountService.countAccount();
//	}
//	
//	@GetMapping("/list/count/active")
//	public Integer getActiveAccountCount() {
//		return accountService.countAccountByStatus();
//	}
//	
	@PostMapping("/register")
	public Integer registerAccount(@RequestBody Account account) throws NoSuchAlgorithmException {
		String plainPassword = account.getPassword();
		Byte accountStatus = 1;
		String secretkey = PasswordEncryption.convertSecretKeyToString(PasswordEncryption.generateSecretKey("HmacSHA256", 256));
		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", PasswordEncryption.generatePasswordEncoder(secretkey));
		String encodedPassword = passwordEncoder.encode(plainPassword);
		
		Account acc = new Account(account.getUsername(), encodedPassword, secretkey, account.getRoles(), account.getDateCreated(), 
									accountStatus, account.getMId(), account.getEId());
		
		return accountService.insert(acc);
	}
//	
//	@PutMapping("/list/information/{id}/update/password")
//	public Integer updateListPassword(@PathVariable("id")int id, @RequestBody Account account) {
//		String username = account.getUsername();
//		String password = account.getPassword();
//		String key = PasswordEncription.getSaltvalue(30);
//		String hash = PasswordEncription.generateSecurePassword(password, key);
//		
//		Account acc = new Account(id, username, hash, key, LocalDateTime.now());
//
//		return accountService.updateAccountByAdmin(acc);
//	}
//	
//	@PutMapping("/profile/update/password")
//	public Integer updateAccountPassword(@RequestBody Account account) {	
//		String username = account.getUsername();
//		String password = account.getPassword();
//		String key = PasswordEncription.getSaltvalue(30);
//		String hash = PasswordEncription.generateSecurePassword(password, key);
//		
//		Account acc = new Account(username, hash, key, LocalDateTime.now());
//
//		return accountService.updatePasswordByManager(acc);
//	}
//	
//	@PutMapping("/list/information/{id}/update/status")
//	public Integer updateStatus(@PathVariable("id")int id, @RequestBody Account account) {
//		String username = account.getUsername();
//		int accStatus = account.getAccountStatus();
//		
//		Account acc = new Account(id, username, LocalDateTime.now(), accStatus);
//
//		return accountService.updateAccountStatus(acc);
//	}
//	
//	@DeleteMapping("/list/delete/{id}")
//	public Integer deleteAccount(@PathVariable("id") int id) {
//		return accountService.deleteAccount(id);
//	}
}
