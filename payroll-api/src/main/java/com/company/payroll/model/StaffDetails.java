package com.company.payroll.model;

import java.time.LocalDate;
//import java.util.List;

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
public class StaffDetails {
//    private Integer eId;
    private Integer staffId;
//    private String fullname;
    private String staffName;
    @Schema(description="Three values available: female, male, other")
    private String gender;
    
    @JsonProperty("date_of_birth")
//    private LocalDate dob;
    private LocalDate dateOfBirth;
    private Short age;
    @Schema(description="Three values available: divorced, married, single")
    private String martialStatus;
    private String education;
    private String address;
    private Integer postalCode;
    private String state;
    private String country;
    private String phone;
    @JsonProperty("company_email")
    private String email;
    private LocalDate dateHired;
    private LocalDate dateResign;
    private String imgPath;
    private Department department;
    private Title title;
    private StaffBankingInfo staffBankingInfo;
    private StaffSalary staffSalary;
    private StaffLeaveDetails staffLeaveDetails;
    private StaffDetails manager;
    private Integer deptNo;
    private Integer titleNo;
    private Integer managerId;
    private Integer ldId;
    private Integer sId;
    private Integer bId;

//    private Integer resignId;
//
//    private List<Title> title;
//
//    private List<Department> department;
//
//    private Manager managerOld;
//
//    private StaffSalary staffSalaryOld;
//
//    private StaffBankingInfo bankingInfo;
}