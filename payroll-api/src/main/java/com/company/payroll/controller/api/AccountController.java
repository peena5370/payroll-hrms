package com.company.payroll.controller.api;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.Account;
import com.company.payroll.service.SystemAccountService;
import com.github.pagehelper.PageInfo;

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
										  + "\"roles\": \"string\", \"mid\": null, \"eid\": null}";
	private static final String VALUE_TWO = "{\"username\": \"string\", \"roles\": \"string\", \"mid\": null, \"eid\": null, \"aid\": 0, "
										  + "\"modified_date\": \"2023-04-28T11:38:12.262Z\", \"status\": 0}";
	private static final String VALUE_THREE = "{\"username\": \"string\", \"password\": \"strings\", \"aid\": 0, "
			  							  	+ "\"modified_date\": \"2023-04-28T11:38:12.262Z\"}";
	
	@Autowired
	private SystemAccountService accountService;
	
	@Operation(summary="Get account list")
	@GetMapping
	public ResponseEntity<PageInfo<Account>> listAccount(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(accountService.list(page, offset));	
	}
	
	@Operation(summary="Get account info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Account>> getById(@Parameter(description="Account id") @PathVariable("id")int id) {
		return ResponseEntity.ok(accountService.findById(id));
	}
	
	@Operation(summary="Register new account")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			   	 content= {@Content(mediaType="application/json", 
	   			 schema= @Schema(implementation = Account.class),
	   			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE)})})
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Account account) throws NoSuchAlgorithmException {
		Account row = accountService.insert(account);
			if(row==null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("register failed");
			}
		
		return ResponseEntity.ok("register success");
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
	@PutMapping("/{id}/password")
	public ResponseEntity<String> listUpdatePassword(@RequestBody Account account) throws NoSuchAlgorithmException {
		Account row = accountService.updateListPassword(account);
		if(row==null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("password update failed");
		}
		
		return ResponseEntity.ok("update success");
	}
	
	@Operation(summary="Update account.")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(required=true,
  	 	 content= {@Content(mediaType="application/json", 
		 schema= @Schema(implementation = Account.class),
		 examples= {@ExampleObject(name="Example 1", value=VALUE_TWO)})})
	@PutMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody Account account) {
		Account row = accountService.update(account);
		if(row==null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("update failed");
		}
		
		return ResponseEntity.ok("update success");
	}
	
	@Operation(summary="Delete account.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for delete success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for delete fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@Parameter(description="Account id") @PathVariable("id") int aid) {	
		return ResponseEntity.ok(accountService.delete(aid));
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
