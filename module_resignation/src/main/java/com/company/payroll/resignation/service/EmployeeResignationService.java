package com.company.payroll.resignation.service;

import com.company.payroll.resignation.dto.EmployeeResignationDTO;
import com.company.payroll.resignation.dto.EmployeeResignationDetailDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeResignationService {

    int createResignationInfo(EmployeeResignationDTO employeeResignationDTO);

    List<EmployeeResignationDetailDTO> getAllResignationInfoByOffsetAndLimitOrEmployeeId(Long employeeId, int offset, int limit);

    Optional<EmployeeResignationDetailDTO> getResignationInfoById(long resignationId);

    int updateResignationInfoById(long resignationId, EmployeeResignationDTO employeeResignationDTO);

    int deleteResignationInfoById(long resignationId);
}
