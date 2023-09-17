package com.company.payroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class HmsStaffLearningDetail {
    private Long id;

    private String title;

    private String description;
}