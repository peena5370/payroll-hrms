package com.company.payroll.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AtmsStaffAttendance {
    private Long id;

    private LocalDate dateWorking;

    private LocalDateTime inTime1;

    private LocalDateTime outTime1;

    private LocalDateTime inTime2;

    private LocalDateTime outTime2;

    private LocalDateTime inTime3;

    private LocalDateTime outTime3;

    private Long staffId;
}