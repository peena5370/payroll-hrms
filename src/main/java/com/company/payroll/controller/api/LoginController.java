package com.company.payroll.controller.api;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.Account;
import com.company.payroll.model.ResponseObject;
import com.company.payroll.service.AccountService;
import com.company.payroll.utils.JwtTokenUtils;
import com.company.payroll.utils.PasswordEncryption;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class LoginController {
	private static final String VALUE_ONE = "{\"username\": \"string\", \"password\": \"string\"}";
	private static final String VALUE_TWO = "{\"username\": \"testaccount\", \"password\": \"Abcde@12345\"}";
	private static final String VALUE_THREE = "{\"code\": 200, \"msg\": \"Login Success.\"}";
	private static final String VALUE_FOUR = "{\"code\": 401, \"msg\": \"Account has reach max attempt login.\"}";
	private static final String VALUE_FIVE = "{\"code\": 401, \"msg\": \"Wrong user password.\"}";
	private static final String VALUE_SIX = "{\"code\": 500, \"msg\": \"The account does not exist.\"}";
	
	private AccountService accountService;
	
	private JwtTokenUtils jwtTokenUtils;
	
	public LoginController(AccountService accountService, JwtTokenUtils jwtTokenUtils) {
		this.accountService = accountService;
		this.jwtTokenUtils = jwtTokenUtils;
	}

	@Operation(summary = "System login API",
			   description="API will consist of the response status from back-end server.\n"
			   			 + "This API will also generate an authorization token for the client which saved at the response header('Authorization').",
			   parameters= {
					   @Parameter(name= "username", required= true, 
							   	  description= "Username"),
					   @Parameter(name= "password", required= true, 
					   			  description= "Password")},
			   responses= {@ApiResponse(responseCode= "200",
			   							 description= "Login success.",
			   							 content= {@Content(mediaType="application/json",
			   									 	schema= @Schema(implementation = ResponseObject.class),
			   									 	examples= {@ExampleObject(value=VALUE_THREE)})}),
			   				@ApiResponse(responseCode= "401",
			   							 description= "Unauthorized request.",
			   							 content= {@Content(mediaType="application/json",
   									 	 			schema= @Schema(implementation = ResponseObject.class),
   									 	 			examples= {@ExampleObject(name="Example 1", value=VALUE_FOUR),
   									   					 	   @ExampleObject(name="Example 2", value=VALUE_FIVE)})}),
			   				@ApiResponse(responseCode= "500",
  							 			 description= "Account not available in server.",
  							 			 content= {@Content(mediaType="application/json",
  							 			 			schema= @Schema(implementation = ResponseObject.class),
  							 			 			examples= {@ExampleObject(value=VALUE_SIX)})})
			   				})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			   required= true,
			   content= {@Content(mediaType="application/json", 
			   			 schema= @Schema(implementation = Account.class),
			   			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE),
			   					 	@ExampleObject(name="Example 2", value=VALUE_TWO)}) })
	@PostMapping("/login")
	public ResponseEntity<ResponseObject> backendLoginValidate(HttpServletRequest request, @RequestBody Account account) {
		ResponseObject resp = new ResponseObject();
		String token = null;
		Map<String, Object> claims = new HashMap<>();
		
		try {
			Account obj = accountService.getByUsername(account.getUsername());
			PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", PasswordEncryption.generatePasswordEncoder(obj.getSecretkey()));
			Boolean match = passwordEncoder.matches(account.getPassword(), obj.getPassword());
			
			if(match && obj.getLastAttempt()<5) {
				switch(obj.getAccountStatus()) {
					case 0:
						log.info("Inactive account has sucess login.");
						break;
					case 1:
						Account obj2 = new Account();
						obj2.setAId(obj.getAId());
						obj2.setLastAttempt((byte) 1);
						obj2.setLastLogin(LocalDateTime.now());
						
						accountService.update(obj2);
						
						switch(obj.getRoles()) {
							case "role_admin":
								claims.put("username", account.getUsername());
								claims.put("roles", obj.getRoles());
								claims.put("id", obj.getMId());
								claims.put("client_address", request.getRemoteAddr());
								
								token = jwtTokenUtils.generateToken(claims);
								break;
							case "role_manager":
								claims.put("username", account.getUsername());
								claims.put("roles", obj.getRoles());
								claims.put("id", obj.getMId());
								claims.put("client_address", request.getRemoteAddr());
								
								token = jwtTokenUtils.generateToken(claims);
								break;
							default:
								claims.put("username", account.getUsername());
								claims.put("roles", obj.getRoles());
								claims.put("id", obj.getEId());
								claims.put("client_address", request.getRemoteAddr());
								
								token = jwtTokenUtils.generateToken(claims);
								break;
						}
						
						resp.setCode(200);
						resp.setMsg("Login success.");
						break;
					case 2:
						log.info("Locked account has success login.");
						break;
					default:
						break;
				}
			} else if(obj.getLastAttempt()>=6) {
				Account obj3 = new Account();
				obj3.setAId(obj.getAId());
				obj3.setAccountStatus((byte) 2);
				
				accountService.update(obj3);
				
				resp.setCode(401);
				resp.setMsg("Account has reach max attempt login.");
				
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
			} else {
				Account obj4 = new Account();
				obj4.setAId(obj.getAId());
				obj4.setLastAttempt((byte) (obj.getLastAttempt() + 1));
				
				accountService.update(obj4);
				
				resp.setCode(401);
				resp.setMsg("Wrong user password.");
				
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
			}
			
		} catch(NullPointerException | NoSuchAlgorithmException e) {
			log.error("Error message: {}", e.getMessage());
			
			resp.setCode(500);
			resp.setMsg("The account does not exist.");
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		}
		
		return ResponseEntity.ok().header("Authorization", "Bearer " + token).body(resp);
	}
	
	@Operation(summary= "System logout API",
			   description= "TODO")
	@PostMapping("/logout")
	public ResponseEntity<ResponseObject> logout() {
		ResponseObject resp = new ResponseObject();
		resp.setCode(200);
		resp.setMsg("Success logout.");
		/**
		 * TODO to be add up validate user logout.
		 * Items needed:
		 * 1. remote address
		 * 2. token
		 * 
		 * May need to invalidate session, clear token or maybe blacklist token with Redis cache
		 */
		
		return ResponseEntity.ok(resp);
	}
}
