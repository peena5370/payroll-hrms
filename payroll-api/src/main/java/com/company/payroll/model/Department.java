package com.company.payroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Department {
//    private Integer deptno;
//
//    private String deptname;
//
//    private String location;
//
//    private String state;
//
//    private String country;

    private Integer deptNo;
    private String deptName;
    private String deptAddress;
    private Integer postalCode;
    private String state;
    private String country;
}