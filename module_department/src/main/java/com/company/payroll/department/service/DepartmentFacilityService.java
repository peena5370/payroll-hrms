package com.company.payroll.department.service;

import com.company.payroll.department.dto.DepartmentDetailDTO;
import com.company.payroll.department.dto.DepartmentFacilityDTO;

import java.util.List;

public interface DepartmentFacilityService {
    int createDepartmentFacilityUnit(DepartmentFacilityDTO departmentFacilityDTO);

    boolean deleteDepartmentFacilityUnitDetailByDepartmentFUId(long departmentFUId);

    List<DepartmentDetailDTO> getAllDepartmentDetailsByFacilityId(long facilityId);
}
