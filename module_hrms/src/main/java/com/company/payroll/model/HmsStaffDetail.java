package com.company.payroll.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class HmsStaffDetail {
    private Long id;

    private String fullname;

    private String gender;

    private LocalDate dateOfBirth;

    private Integer age;

    private String maritalStatus;

    private String education;

    private String homeAddress;

    private String phone;

    private String email;

    private LocalDate hiredDate;

    private LocalDate resignDate;

    private String profileImagePath;

    private Long deptId;

    private Long titleId;

    private Long managerId;
}