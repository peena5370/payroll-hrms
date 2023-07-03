package com.company.payroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SystemAccount {
    private Integer aId;
    private String username;
    private String password;
    private String secretKey;
    private String roles;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    private LocalDateTime lastLogin;
    private Byte lastAttempt;
    private Byte accountStatus;
    private String imgPath;
    private Integer staffId;
}
