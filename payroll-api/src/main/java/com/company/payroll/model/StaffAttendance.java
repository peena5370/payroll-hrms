package com.company.payroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class StaffAttendance {
    private Integer atId;
    private LocalDate attendanceDate;
    private LocalDateTime checkInOne;
    private LocalDateTime checkOutOne;
    private LocalDateTime checkInTwo;
    private LocalDateTime checkOutTwo;
    private LocalDateTime checkInThree;
    private LocalDateTime checkOutThree;
    private Integer staffId;
    private LocalDate startDate;
    private LocalDate endDate;
}
