package com.company.payroll.service;

import com.company.payroll.dto.DepartmentDTO;
import com.company.payroll.dto.DepartmentInfoDTO;

import java.util.Optional;

public interface DepartmentService {

    public Optional<DepartmentInfoDTO> getDepartmentInfoByDepartmentId(Long departmentId);

    public int createDepartmentInfo(DepartmentDTO departmentDTO);

    public int updateDepartmentInfoById(long departmentId, DepartmentDTO departmentDTO);

    public int deleteDepartmentInfoById(long departmentId);
}
