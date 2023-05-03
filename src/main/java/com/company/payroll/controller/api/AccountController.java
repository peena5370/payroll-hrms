package com.company.payroll.controller.api;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.company.payroll.util.PasswordEncryption;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;

@RestController
@RequestMapping("/api/account")
public class AccountController {
	private static final String VALUE_ONE = "{\"username\": \"string\", \"password\": \"string\", "
										  + "\"roles\": \"string\", \"register_date\": \"2023-04-28T11:33:18.906Z\", "
										  + "\"mid\": null, \"eid\": null}";
	private static final String VALUE_TWO = "{\"username\": \"string\", \"roles\": \"string\", \"mid\": null, \"eid\": null, \"aid\": 0, "
										  + "\"modified_date\": \"2023-04-28T11:38:12.262Z\", \"status\": 0}";
	private static final String VALUE_THREE = "{\"username\": \"string\", \"password\": \"strings\", \"aid\": 0, "
			  							  	+ "\"modified_date\": \"2023-04-28T11:38:12.262Z\"}";
	
	@Autowired
	private AccountService accountService;

	private String directory;
	
	public AccountController(@Value("${file.upload.directory}") String directory) {
		this.directory = directory;
	}
	
	@Operation(summary="Get account list")
	@GetMapping("/list")
	public ResponseEntity<List<Account>> listAccount() {
		return ResponseEntity.ok(accountService.getList());	
	}
	
	@Operation(summary="Get account info by id")
	@GetMapping("/list/information/{id}")
	public ResponseEntity<Account> getById(@Parameter(description="Account id") @PathVariable("id")int id) {
		return ResponseEntity.ok(accountService.getById(id));
	}
	
	@Operation(summary="Register new account",
			   responses= {@ApiResponse(responseCode="200",
					   					description="Value return 1 for register success.",
					   					content=@Content(examples= {@ExampleObject(value="1")})),
					   	   @ApiResponse(responseCode="403",
					   			   		description="Value return 0 for register fail.",
					   			   		content=@Content(examples= {@ExampleObject(value="0")}))})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			   	 content= {@Content(mediaType="application/json", 
	   			 schema= @Schema(implementation = Account.class),
	   			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE)})})
	@PostMapping("/register")
	public ResponseEntity<Integer> insert(@RequestBody Account account) throws NoSuchAlgorithmException {
		String plainPassword = account.getPassword();
		Byte accountStatus = 1;
		String defaultImgPath = directory + "/images/default/img-001.png";
		String secretkey = PasswordEncryption.convertSecretKeyToString(PasswordEncryption.generateSecretKey("HmacSHA256", 256));
		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", PasswordEncryption.generatePasswordEncoder(secretkey));
		String encodedPassword = passwordEncoder.encode(plainPassword);
		
		Account row = new Account(account.getUsername(), encodedPassword, secretkey, account.getRoles(), account.getDateCreated(), 
									accountStatus, defaultImgPath, account.getMId(), account.getEId());
		
		Integer status = accountService.insert(row);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@Operation(summary="Update account password",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for update success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for update fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(required=true,
  	 	 content= {@Content(mediaType="application/json", 
		 schema= @Schema(implementation = Account.class),
		 examples= {@ExampleObject(name="Example 1", value=VALUE_THREE)})})
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
	
	@Operation(summary="Update account.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for update success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for update fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(required=true,
  	 	 content= {@Content(mediaType="application/json", 
		 schema= @Schema(implementation = Account.class),
		 examples= {@ExampleObject(name="Example 1", value=VALUE_TWO)})})
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> update(@RequestBody Account account) {
		Integer status = accountService.update(account);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@Operation(summary="Delete account.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for delete success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for delete fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> delete(@Parameter(description="Account id") @PathVariable("id") int aid) {
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
}
