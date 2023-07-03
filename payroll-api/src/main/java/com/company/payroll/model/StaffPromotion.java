package com.company.payroll.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class StaffPromotion {
    private Integer pId;
    private BigDecimal currentSalary;
    private BigDecimal promoteSalary;
    private LocalDate promoteDate;
    private String comment;
    private Title title;
    private StaffDetails staffDetails;
    private Integer titleNo;
    private Integer staffId;

//    private String comment;
//    private Integer eId;
//    private Integer mId;
//    private List<Title> title;
}