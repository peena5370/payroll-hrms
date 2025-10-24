package com.company.payroll.department.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "department_facility_unit")
public class DepartmentFacilityUnit {
    @Id
    @Column(name = "department_uid")
    private long departmentUId;

    @Column(name = "department_id", nullable = false)
    private String departmentId;

    @Column(name = "facility_id", nullable = false)
    private String facilityId;
}
