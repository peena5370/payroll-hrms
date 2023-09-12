package com.company.payroll.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SysAccount {
    private Long id;

    private String username;

    private String password;

    private String secretKey;

    private String role;

    private LocalDateTime dateCreated;

    private LocalDateTime dateModified;

    private LocalDateTime lastLogin;

    private Byte status;
}