package com.company.payroll.department.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "department_employee")
public class DepartmentEmployee {
    @Id
    @Column(name = "department_eid")
    private long departmentEid;

    @Column(name = "department_id", nullable = false)
    private long departmentId;

    @Column(name = "department_uid", nullable = false)
    private long departmentUnitId;

    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    @Column(name = "is_primary")
    private boolean isPrimary;

    @Column(name = "is_manager")
    private boolean isManager;

    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt;

    @Column(name = "leaved_at")
    private LocalDateTime leavedAt;
}
