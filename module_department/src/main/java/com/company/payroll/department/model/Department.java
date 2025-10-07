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
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "department_id")
    private long departmentId;

    @Column(name = "department_name", unique = true, nullable = false)
    private String departmentName;

    @Column(name = "description")
    private String description;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "parent_department_id")
    private Long parentDepartmentId;

    @Column(name = "location")
    private String location;

    @Column(name = "phone_extension_code")
    private String phoneExtensionCode;

    @Column(name = "department_email")
    private String departmentEmail;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
