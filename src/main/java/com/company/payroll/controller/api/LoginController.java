package com.company.payroll.controller.api;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.Account;
import com.company.payroll.model.ResponseObject;
import com.company.payroll.service.AccountService;
import com.company.payroll.utils.JwtTokenUtils;
import com.company.payroll.utils.PasswordEncryption;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class LoginController {
//	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	private AccountService accountService;
	private JwtTokenUtils jwtTokenUtils;
	
	public LoginController(AccountService accountService, JwtTokenUtils jwtTokenUtils) {
		this.accountService = accountService;
		this.jwtTokenUtils = jwtTokenUtils;
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseObject> backendLoginValidate(HttpServletRequest request, @RequestBody Account account) {
		ResponseObject resp = new ResponseObject();
		String jwtToken = "";
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
								claims.put("img_path", obj.getImgPath());
								claims.put("client_address", request.getRemoteAddr());
								
								jwtToken = jwtTokenUtils.createJwt(claims);
								break;
							case "role_manager":
								claims.put("username", account.getUsername());
								claims.put("roles", obj.getRoles());
								claims.put("id", obj.getMId());
								claims.put("img_path", obj.getImgPath());
								claims.put("client_address", request.getRemoteAddr());
								
								jwtToken = jwtTokenUtils.createJwt(claims);
								break;
							default:
								claims.put("username", account.getUsername());
								claims.put("roles", obj.getRoles());
								claims.put("id", obj.getEId());
								claims.put("img_path", obj.getImgPath());
								claims.put("client_address", request.getRemoteAddr());
								
								jwtToken = jwtTokenUtils.createJwt(claims);
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
		
		return ResponseEntity.ok().header("Authorization", "Bearer " + jwtToken).body(resp);
	}
	
	@GetMapping("/logout")
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
