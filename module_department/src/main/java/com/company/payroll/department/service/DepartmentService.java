package com.company.payroll.department.service;

import com.company.payroll.department.dto.DepartmentDTO;
import com.company.payroll.department.dto.DepartmentInfoDTO;
import com.company.payroll.department.dto.DepartmentInfosDTO;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<DepartmentInfosDTO> getAllDepartmentInfoByOffsetLimitAndSortOrder(int offset, int limit, String sortOrder);

    Optional<DepartmentInfoDTO> getDepartmentInfoByDepartmentId(long departmentId);

    int createDepartmentInfo(DepartmentDTO departmentDTO);

    int updateDepartmentInfoById(long departmentId, DepartmentDTO departmentDTO);

    int deleteDepartmentInfoById(long departmentId);
}
