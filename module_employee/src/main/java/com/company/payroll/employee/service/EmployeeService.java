package com.company.payroll.employee.service;

import com.company.payroll.employee.dto.EmployeeDTO;
import com.company.payroll.employee.dto.EmployeeInfoDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    int createEmployeeInfo(EmployeeDTO employeeDTO);

    List<EmployeeInfoDTO> getAllEmployeesByOffsetAndLimitAndOrder(int offset, int limit);

    Optional<EmployeeInfoDTO> getEmployeeInfoById(long employeeId);

    int updateEmployeeInfoById(long employeeId, EmployeeDTO employeeDTO);

    int deleteEmployeeInfoById(long employeeId);
}
