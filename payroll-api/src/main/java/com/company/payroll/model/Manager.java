package com.company.payroll.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Manager {
    private Integer mId;

    private String fullname;

    @Schema(description="Three values available: female, male, other")
    private String gender;

    @JsonProperty("date_of_birth")
    private LocalDate dob;

    private Short age;
    
    @Schema(description="Three values available: divorced, married, single")
    private String martialstatus;

    private String education;

    private String address;

    private String state;

    private String country;

    private String phone;

    @JsonProperty("company_email")
    private String companyEmail;

    private LocalDate datehired;

    private LocalDate dateresign;

    private String attachment;

    private String imgUser;

    private Integer deptno;

    private Integer titleno;

    private Integer sId;

    private Integer bId;

    private Integer resignId;
    
    private List<Title> title;
    
    private List<Department> department;
    
    private Salary salary;
    
    private BankingInfo bankingInfo;
    
    
}