package com.company.payroll.department.repository;

import com.company.payroll.department.model.DepartmentEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartmentEmployeeRepository extends JpaRepository<DepartmentEmployee, Long> {

    @Query("SELECT de FROM DepartmentEmployee de WHERE de.departmentId = :departmentId")
    Optional<List<DepartmentEmployee>> getAllByDepartmentId(@Param("departmentId") Long departmentId);

    @Query("SELECT de FROM DepartmentEmployee de WHERE de.departmentId IN :departmentIds AND de.isManager = :isManager")
    Optional<List<DepartmentEmployee>> getAllByDepartmentIdsAndIsManager(@Param("departmentIds") List<Long> departmentIds, @Param("isManager") boolean isManager);
}
