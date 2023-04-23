package com.company.payroll.model;

import java.time.LocalDateTime;

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

    private String password;

    private String secretkey;

    private String roles;

    private LocalDateTime dateCreated;

    private LocalDateTime dateModified;

    private LocalDateTime lastLogin;

    private Byte lastAttempt;

    private Byte accountStatus;

    private String imgPath;

    private Integer mId;

    private Integer eId;
    
    public Account(String username, String password, String secretkey, LocalDateTime datecreated, Byte accountStatus) {
		this.username = username;
		this.password = password;
		this.secretkey = secretkey;
		this.dateCreated = datecreated;
		this.accountStatus = accountStatus;
	}
	
	public Account(int aId, String username, String password, String secretkey, LocalDateTime dateModified) {
		this.aId = aId;
		this.username = username;
		this.password = password;
		this.secretkey = secretkey;
		this.dateModified = dateModified;
	}

	public Account(String username, String password, String secretkey, LocalDateTime dateModified) {
		this.username = username;
		this.password = password;
		this.secretkey = secretkey;
		this.dateModified = dateModified;
	}
	
	public Account(int aId, String username, LocalDateTime dateModified, Byte accountStatus) {
		this.aId = aId;
		this.username = username;
		this.dateModified = dateModified;
		this.accountStatus = accountStatus;
	}
}