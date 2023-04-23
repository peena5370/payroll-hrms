package com.company.payroll.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Employee {
    private Integer eId;

    private String fullname;

    private String gender;

    private LocalDate dob;

    private Short age;

    private String martialstatus;

    private String education;

    private String address;

    private String state;

    private String country;

    private String phone;

    private String companyEmail;

    private LocalDate datehired;

    private LocalDate dateresign;

    private String attachment;

    private String imgUser;

    private Integer deptno;

    private Integer titleno;

    private Integer mId;

    private Integer sId;

    private Integer bId;

    private Integer resignId;
}