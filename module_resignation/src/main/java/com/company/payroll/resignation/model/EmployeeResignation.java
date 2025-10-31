package com.company.payroll.resignation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "employee_resignation")
public class EmployeeResignation {
    @Id
    @Column(name = "resignation_id")
    private long resignationId;

    @Column(name = "employee_id", nullable = false)
    private long employeeId;

    @Column(name = "resignation_date", nullable = false)
    private LocalDate resignationDate;

    @Column(name = "last_working_day")
    private LocalDate lastWorkingDay;

    @Column(name = "resignation_reason")
    private String resignationReason;

    @Column(name = "notice_period_day", nullable = false)
    private int noticePeriodDays;

    @Column(name = "exit_interview_conducted", nullable = false)
    private boolean exitInterviewConducted;

    @Column(name = "exit_interview_note")
    private String exitInterviewNote;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "approved_by_id")
    private Long approvedById;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
