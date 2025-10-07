package com.company.payroll.employee.service;

import com.company.payroll.employee.dto.EmployeeDTO;
import com.company.payroll.employee.dto.EmployeeInfoDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Optional<List<EmployeeInfoDTO>> getAllEmployeesByOffsetAndLimitAndOrder(int offset, int limit, String sortOrder);

    Optional<EmployeeInfoDTO> getEmployeeInfoById(long employeeId);

    Optional<List<Object>> getAllManagerInfosByIds(List<Long> employeeIds);

    int createEmployeeInfo(EmployeeDTO employeeInfo);

    int updateEmployeeInfoById(long employeeId, EmployeeDTO employeeInfo);

    int deleteEmployeeInfoById(long employeeId);
}
