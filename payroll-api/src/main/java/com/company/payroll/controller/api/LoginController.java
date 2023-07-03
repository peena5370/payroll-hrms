package com.company.payroll.controller.api;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.company.payroll.model.SystemAccount;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.ResponseObject;
import com.company.payroll.service.SystemAccountService;
import com.company.payroll.util.JwtTokenUtils;
import com.company.payroll.util.PasswordEncryption;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class LoginController {
	private static final String VALUE_ONE = "{\"username\": \"string\", \"password\": \"string\"}";
	private static final String VALUE_TWO = "{\"username\": \"testaccount\", \"password\": \"Abcde@12345\"}";
	private static final String VALUE_THREE = "{\"code\": 200, \"msg\": \"Login Success.\"}";
	private static final String VALUE_FOUR = "{\"code\": 401, \"msg\": \"Account has reach max attempt login.\"}";
	private static final String VALUE_FIVE = "{\"code\": 401, \"msg\": \"Wrong user password.\"}";
	private static final String VALUE_SIX = "{\"code\": 500, \"msg\": \"The account does not exist.\"}";
	
	@Autowired
	private SystemAccountService systemAccountService;
	
	@Autowired
	private JwtTokenUtils jwtTokenUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;

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
			   			 schema= @Schema(implementation = SystemAccount.class),
			   			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE),
			   					 	@ExampleObject(name="Example 2", value=VALUE_TWO)}) })
	@PostMapping("/login")
	public ResponseEntity<ResponseObject> backendLoginValidate(HttpServletRequest request, @RequestBody Map<String, String> map) {
		ResponseObject resp = new ResponseObject();
		String token = "";
		Map<String, Object> claims = new HashMap<>();

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(map.get("username"), map.get("password"));
		try {
			Optional<SystemAccount> accountDetails = systemAccountService.findByUsername(map.get("username"));
			if(accountDetails.isPresent()) {
				SystemAccount account = accountDetails.get();
				PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", PasswordEncryption.generatePasswordEncoder(account.getSecretKey()));
				boolean match = passwordEncoder.matches(map.get("password"), accountDetails.get().getPassword());

				if(match && account.getLastAttempt()<5) {
					switch (account.getAccountStatus()) {
						case 0 -> {
							log.info("Inactive account has success login.");
							resp.setCode(401);
							resp.setMsg("Inactive account has success login.");
						}
						case 1 -> {
							SystemAccount obj2 = new SystemAccount();
							obj2.setAId(account.getAId());
							obj2.setLastLogin(LocalDateTime.now());
							obj2.setLastAttempt((byte) 1);
							systemAccountService.modifyStatusRoles(obj2);

							authenticationManager.authenticate(authToken);
							claims.put("id", account.getStaffId());
							claims.put("roles", account.getRoles());
							claims.put("client_address", request.getRemoteAddr());

							token = jwtTokenUtils.generateToken(account.getUsername(), claims);

							resp.setCode(200);
							resp.setMsg("Login success.");
						}
						case 2 -> {
							log.info("Locked account has success login.");
							resp.setCode(401);
							resp.setMsg("Locked account has success login.");
						}
						default -> {
						}
					}
				} else if(account.getLastAttempt()>=6) {
					SystemAccount obj3 = new SystemAccount();
					obj3.setAId(account.getAId());
					obj3.setAccountStatus((byte) 2);

					systemAccountService.modifyStatusRoles(obj3);

					resp.setCode(401);
					resp.setMsg("Account has reach max attempt login.");

					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
				} else {
					SystemAccount obj4 = new SystemAccount();
					obj4.setAId(account.getAId());
					obj4.setLastAttempt((byte) (account.getLastAttempt() + 1));

					systemAccountService.setLastAttempt(obj4);

					resp.setCode(401);
					resp.setMsg("Wrong user password.");

					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
				}
			}
		} catch(NullPointerException | NoSuchAlgorithmException e) {
			log.error("Error message: {}", e.getMessage());
			
			resp.setCode(500);
			resp.setMsg("The account does not exist.");
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		}
		
		return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).body(resp);
	}
	
	@Operation(summary= "System logout API",
			   description= "TODO")
	@PostMapping("/logout")
	public ResponseEntity<ResponseObject> logout(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		ResponseObject resp = new ResponseObject();
		resp.setCode(200);
		resp.setMsg("Success logout.");
		/**
		 * TODO to be add up validate user logout.
		 * Items needed:
		 * 1. remote address
		 * 2. token
		 * 3. redis cache token
		 *
		 * Blacklist token with Redis container
		 * 1. replace Bearer header to Blacklist header
		 * 2. save to Redis
		 */

		return ResponseEntity.ok(resp);
	}

	@Operation(summary = "Load profile image")
	@PostMapping("/image/download")
	public ResponseEntity<Resource> downloadUserImage(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		Claims claims = jwtTokenUtils.getClaims(header.substring(7));
		Resource resource = systemAccountService.downloadAccountImage(claims.getSubject());
		String contentType = "";
		if(resource!=null) {
			try {
				contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			} catch (IOException e) {
				log.error("Could not determine file type. Exception message: {}", e.getMessage());
				return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);
			}
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
	}
}
