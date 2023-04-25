package com.company.payroll.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


/**
 * Date created 6 Nov 2022
 * 
 * @author leeshengxian
 * @version 1.1
 * 
 * Major changes made at 22 Apr 2023:
 * Add newly created attributes for Account model. Added below attributes:
 * 1. lastLogin
 * 2. lastAttempt
 * 3. imgPath
 * 4. mId
 * 5. eId
 * 6. roles
 * 
 * Rename key attribute to secretkey
 * Rename dateChanged to dateModified
 * 
 * Change accountStatus data type from int to Byte
 * 
 */
@Getter
@Setter
@RequiredArgsConstructor
public class Account {
    private Integer aId;

    private String username;
    
    @JsonProperty("password")
    private String password;
    
    @JsonProperty("key")
    private String secretkey;

    private String roles;
    
    @JsonProperty("register_date")
    private LocalDateTime dateCreated;

    @JsonProperty("modified_date")
    private LocalDateTime dateModified;
    
    @JsonProperty("last_login")
    private LocalDateTime lastLogin;

    @JsonProperty("last_attempt")
    private Byte lastAttempt;
    
    @JsonProperty("status")
    private Byte accountStatus;
    
    @JsonProperty("profile_img_path")
    private String imgPath;
    
    private Integer mId;

    private Integer eId;

	public Account(String username, String password, String secretkey, String roles,
			LocalDateTime dateCreated, Byte accountStatus, Integer mId, Integer eId) {
		this.username = username;
		this.password = password;
		this.secretkey = secretkey;
		this.roles = roles;
		this.dateCreated = dateCreated;
		this.accountStatus = accountStatus;
		this.mId = mId;
		this.eId = eId;
	}
	public Account(int aId, String username, String password, String secretkey, LocalDateTime dateModified) {
		this.aId = aId;
		this.username = username;
		this.password = password;
		this.secretkey = secretkey;
		this.dateModified = dateModified;
	}
	
	public Account(int aid, String username, String roles, Byte lastAttempt, Byte accountStatus) {
		this.aId = aid;
		this.username = username;
		this.roles = roles;
		this.lastAttempt = lastAttempt;
		this.accountStatus = accountStatus;
	}

//	public Account(String username, String password, String secretkey, LocalDateTime dateModified) {
//		this.username = username;
//		this.password = password;
//		this.secretkey = secretkey;
//		this.dateModified = dateModified;
//	}
//	
//	public Account(int aId, String username, LocalDateTime dateModified, Byte accountStatus) {
//		this.aId = aId;
//		this.username = username;
//		this.dateModified = dateModified;
//		this.accountStatus = accountStatus;
//	}
}