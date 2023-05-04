package com.company.payroll.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequiredArgsConstructor
public class Account {
    private Integer aId;

    @NotNull
    @Size(min = 5, max=20)
    private String username;
    
    @NotNull
    @JsonProperty("password")
    @Size(min = 8, max = 20)
    private String password;
    
    @Hidden
    @JsonProperty("key")
    private String secretkey;

    @Schema(description="Contain three roles: <br>"
    				  + "1. role_admin=for admin user<br>"
    				  + "2. role_manager=for manager user<br>"
    				  + "3. role_user=for normal user<br>")
    private String roles;
    
    @JsonProperty("register_date")
    private LocalDateTime dateCreated;

    @JsonProperty("modified_date")
    private LocalDateTime dateModified;
    
    @JsonProperty("last_login")
    private LocalDateTime lastLogin;

    @Hidden
    @JsonProperty("last_attempt")
    private Byte lastAttempt;
    
    @Schema(description="0=disable, 1=enable, 2=locked")
    @JsonProperty("status")
    private Byte accountStatus;
    
    @JsonProperty("profile_img_path")
    private String imgPath;
    
    private Integer mId;

    private Integer eId;
    
    public Account(String username, String password, String secretkey, String roles,
			LocalDateTime dateCreated, Byte accountStatus, String imgPath, Integer mId, Integer eId) {
		this.username = username;
		this.password = password;
		this.secretkey = secretkey;
		this.roles = roles;
		this.dateCreated = dateCreated;
		this.accountStatus = accountStatus;
		this.imgPath = imgPath;
		this.mId = mId;
		this.eId = eId;
	}

	public Account(Integer aId, String username, String password, String secretkey,
			LocalDateTime dateModified) {
		this.aId = aId;
		this.username = username;
		this.password = password;
		this.secretkey = secretkey;
		this.dateModified = dateModified;
	}
}