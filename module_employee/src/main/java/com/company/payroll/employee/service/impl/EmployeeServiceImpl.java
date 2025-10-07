package com.company.payroll.employee.service.impl;

import com.company.payroll.employee.dto.EmployeeDTO;
import com.company.payroll.employee.dto.EmployeeInfoDTO;
import com.company.payroll.employee.model.Employee;
import com.company.payroll.employee.repository.EmployeeRepository;
import com.company.payroll.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String CLASSNAME = "[EmployeeServiceImpl]";

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<List<EmployeeInfoDTO>> getAllEmployeesByOffsetAndLimitAndOrder(int offset, int limit, String sortOrder) {
        return Optional.empty();
    }

    @Override
    public Optional<EmployeeInfoDTO> getEmployeeInfoById(long employeeId) {
        final String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

        Object[] objParam = new Object[]{
                CLASSNAME,
                methodName,
                employeeId
        };
        log.info("{0} [{1}] start. EmployeeId={2}", objParam);

        Optional<EmployeeInfoDTO> result = Optional.empty();

        Optional<Employee> employeeQuery = this.employeeRepository.findById(employeeId);

        if (employeeQuery.isPresent()) {
            Employee employee = employeeQuery.get();

            String managerName = null;
            if (employee.getManagerId() != null) {
                Optional<Employee> managerQuery = this.employeeRepository.findById(employeeId);

                if (managerQuery.isPresent()) {
                    Employee manager = managerQuery.get();
                    managerName = manager.getFirstName() + " " + manager.getLastName();
                }
            }

            result = Optional.of(new EmployeeInfoDTO(
                    employee.getEmployeeId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDateOfBirth(),
                    employee.getIcNumber(),
                    employee.getGender(),
                    employee.getEmail(),
                    employee.getPhoneNumber(),
                    employee.getAddressLine1(),
                    employee.getAddressLine2(),
                    employee.getCity(),
                    employee.getStateProvince(),
                    employee.getPostalCode(),
                    employee.getCountry(),
                    employee.getHireDate(),
                    employee.getEmploymentStatus(),
                    employee.getJobTitle(),
                    employee.getManagerId(),
                    managerName
            ));
        }

        log.info("{0} [{1}] end.", objParam);
        return result;
    }

    @Override
    public Optional<List<Object>> getAllManagerInfosByIds(List<Long> employeeIds) {
        return Optional.empty();
    }

    @Override
    public int createEmployeeInfo(EmployeeDTO employeeInfo) {
        final String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

        Object[] objParam = new Object[]{
                CLASSNAME,
                methodName
        };
        log.info("{0} [{1}] start.", objParam);

        int createStatus = 0;
        /**
         * 1. first_name
         * 2. last_name
         * 3. date_of_birth
         * 4. gender
         * 5. email
         * 6. phone_number
         * 7. address_line_1
         * 8. address_line_2
         * 9. city
         * 10. state_province
         * 11. postal_code
         * 12. country
         * 13. hire_date
         * 14. employment_status
         * 15. job_title
         * 16. manager_id
         */

        log.info("{0} [{1}] end.", objParam);
        return createStatus;
    }

    @Override
    public int updateEmployeeInfoById(long employeeId, EmployeeDTO employeeInfo) {
        return 0;
    }

    @Override
    public int deleteEmployeeInfoById(long employeeId) {
        return 0;
    }
}
