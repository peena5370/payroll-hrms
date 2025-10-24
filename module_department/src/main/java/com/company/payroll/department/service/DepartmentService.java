package com.company.payroll.department.service;

import com.company.payroll.department.dto.DepartmentDTO;
import com.company.payroll.department.dto.DepartmentInfoDTO;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<DepartmentInfoDTO> getAllDepartmentInfoByOffsetLimit(int offset, int limit);

    Optional<DepartmentInfoDTO> getDepartmentInfoByDepartmentId(long departmentId);

    int createDepartmentInfo(DepartmentDTO departmentDTO);

    int updateDepartmentInfoById(long departmentId, DepartmentDTO departmentDTO);

    int deleteDepartmentInfoById(long departmentId);
}
