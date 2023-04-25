package com.company.payroll.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Department {
    private Integer deptno;

    private String deptname;

    private String location;

    private String state;

    private String country;
}