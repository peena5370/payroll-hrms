package com.company.payroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SysDepartment {
    private Long id;

    private String divisionName;

    private String location;

    private Long managerId;
}