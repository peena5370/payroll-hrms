package com.company.payroll.repository;

import com.company.payroll.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d.departmentId FROM Department d WHERE d.departmentName = :departmentName")
    Optional<Long> findIdByDepartmentName(@Param("departmentName") String departmentName);
}
