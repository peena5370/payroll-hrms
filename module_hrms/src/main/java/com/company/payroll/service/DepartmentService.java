package com.company.payroll.service;

import com.company.payroll.dto.DepartmentDTO;
import com.company.payroll.dto.DepartmentInfoDTO;
import com.company.payroll.dto.DepartmentInfosDTO;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<DepartmentInfosDTO> getAllDepartmentInfoByOffsetLimitAndSortOrder(int offset, int limit, String sortOrder);

    Optional<DepartmentInfoDTO> getDepartmentInfoByDepartmentId(long departmentId);

    int createDepartmentInfo(DepartmentDTO departmentDTO);

    int updateDepartmentInfoById(long departmentId, DepartmentDTO departmentDTO);

    int deleteDepartmentInfoById(long departmentId);
}
