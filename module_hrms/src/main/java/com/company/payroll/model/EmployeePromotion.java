package com.company.payroll.model;

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
@Table(name = "employee_promotion")
public class EmployeePromotion {
    @Id
    @Column(name = "promotion_id")
    private long promotionId;

    @Column(name = "employee_id", nullable = false)
    private long employeeId;

    @Column(name = "old_job_title", nullable = false)
    private String oldJobTitle;

    @Column(name = "new_job_title", nullable = false)
    private String newJobTitle;

    @Column(name = "old_department_id", nullable = false)
    private long oldDepartmentId;

    @Column(name = "new_department_id", nullable = false)
    private long newDepartmentId;

    @Column(name = "promotion_date")
    private LocalDate promotionDate;

    @Column(name = "salary_increment_amount")
    private Double salaryIncrementAmount;

    @Column(name = "promotion_reason")
    private String promotionReason;

    @Column(name = "approved_by_id")
    private Long approvedById;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
