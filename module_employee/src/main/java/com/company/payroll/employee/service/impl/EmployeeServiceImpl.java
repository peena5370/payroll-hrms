package com.company.payroll.employee.service.impl;

import com.company.payroll.employee.dto.EmployeeDTO;
import com.company.payroll.employee.dto.EmployeeInfoDTO;
import com.company.payroll.employee.model.Employee;
import com.company.payroll.employee.model.EmployeeBankDetail;
import com.company.payroll.employee.model.EmployeeEmergencyContact;
import com.company.payroll.employee.repository.EmployeeBankDetailRepository;
import com.company.payroll.employee.repository.EmployeeEmergencyContactRepository;
import com.company.payroll.employee.repository.EmployeeRepository;
import com.company.payroll.employee.service.EmployeeService;
import com.company.payroll.util.util.SnowFlakeIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String CLASS_NAME = "[EmployeeServiceImpl]";

    private final TransactionTemplate transactionTemplate;
    private final SnowFlakeIdGenerator snowFlakeIdGenerator;
    private final EmployeeRepository employeeRepository;
    private final EmployeeBankDetailRepository employeeBankDetailRepository;
    private final EmployeeEmergencyContactRepository  employeeEmergencyContactRepository;

    public EmployeeServiceImpl(TransactionTemplate transactionTemplate,
                               SnowFlakeIdGenerator snowFlakeIdGenerator,
                               EmployeeRepository employeeRepository,
                               EmployeeBankDetailRepository employeeBankDetailRepository,
                               EmployeeEmergencyContactRepository employeeEmergencyContactRepository) {
        this.transactionTemplate = transactionTemplate;
        this.snowFlakeIdGenerator = snowFlakeIdGenerator;
        this.employeeRepository = employeeRepository;
        this.employeeBankDetailRepository = employeeBankDetailRepository;
        this.employeeEmergencyContactRepository = employeeEmergencyContactRepository;
    }

    @Override
    public int createEmployeeInfo(EmployeeDTO employeeDTO) {
        final String functionName = Thread.currentThread().getStackTrace()[2].getMethodName();
        log.info("{} {} start.", new Object[]{CLASS_NAME, functionName});

        Integer status = transactionTemplate.execute(transactionStatus -> {
            try {
                Optional<Long> existingEmployeeId = employeeRepository.findIdByIcNumber(employeeDTO.icNumber());

                if(existingEmployeeId.isPresent()) {
                    log.info("{} {} employee info exist.", new Object[]{CLASS_NAME, functionName});
                    return -2;
                }

                Employee newEmployee = new Employee(
                        snowFlakeIdGenerator.nextId(),
                        employeeDTO.firstName(),
                        employeeDTO.lastName(),
                        employeeDTO.dateOfBirth(),
                        employeeDTO.icNumber(),
                        employeeDTO.gender(),
                        employeeDTO.email(),
                        employeeDTO.phoneNumber(),
                        employeeDTO.addressLine1(),
                        employeeDTO.addressLine2(),
                        employeeDTO.city(),
                        employeeDTO.state(),
                        employeeDTO.postalCode(),
                        employeeDTO.country(),
                        employeeDTO.hireDate(),
                        employeeDTO.employmentStatus(),
                        employeeDTO.jobTitle(),
                        employeeDTO.managerId(),
                        LocalDateTime.now(),
                        null
                );

                Employee createdEmployee = employeeRepository.save(newEmployee);
                long employeeId = createdEmployee.getEmployeeId();

                if((employeeDTO.bankDetails() != null) && (!employeeDTO.bankDetails().isEmpty())) {
                    List<EmployeeBankDetail> newBankDetails = employeeDTO.bankDetails().stream()
                            .map(employeeBankDetailDTO -> new EmployeeBankDetail(
                                    snowFlakeIdGenerator.nextId(),
                                    employeeId,
                                    employeeBankDetailDTO.bankName(),
                                    employeeBankDetailDTO.accountNumber(),
                                    employeeBankDetailDTO.bicCode(),
                                    employeeBankDetailDTO.accountType()
                            )).toList();

                    employeeBankDetailRepository.saveAll(newBankDetails);
                }

                if((employeeDTO.emergencyContacts() != null) && (!employeeDTO.emergencyContacts().isEmpty())) {
                    List<EmployeeEmergencyContact> newEmergencyContacts = employeeDTO.emergencyContacts().stream()
                            .map(employeeEmergencyContactDTO -> new EmployeeEmergencyContact(
                                    snowFlakeIdGenerator.nextId(),
                                    employeeId,
                                    employeeEmergencyContactDTO.contactPersonName(),
                                    employeeEmergencyContactDTO.relationship(),
                                    employeeEmergencyContactDTO.phoneNumber(),
                                    employeeEmergencyContactDTO.email()
                            )).toList();

                    employeeEmergencyContactRepository.saveAll(newEmergencyContacts);
                }

                return 1;
            } catch(Exception e){
                log.error("{} {} exception occurred for transaction. Message={}", new Object[]{CLASS_NAME, functionName, e.getMessage()});

                transactionStatus.setRollbackOnly();
                return -1;
            }
        });

        int finalStatus = Optional.ofNullable(status).orElse(0);

        log.info("{} {} end. Status={}", new Object[]{CLASS_NAME, functionName, finalStatus});
        return finalStatus;
    }

    @Override
    public List<EmployeeInfoDTO> getAllEmployeesByOffsetAndLimitAndOrder(int offset, int limit) {
        return null;
    }

    @Override
    public Optional<EmployeeInfoDTO> getEmployeeInfoById(long employeeId) {
        final String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

        Object[] objParam = new Object[]{CLASS_NAME, methodName, employeeId};
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

//            result = Optional.of(new EmployeeInfoDTO(
//                    employee.getEmployeeId(),
//                    employee.getFirstName(),
//                    employee.getLastName(),
//                    employee.getDateOfBirth(),
//                    employee.getIcNumber(),
//                    employee.getGender(),
//                    employee.getEmail(),
//                    employee.getPhoneNumber(),
//                    employee.getAddressLine1(),
//                    employee.getAddressLine2(),
//                    employee.getCity(),
//                    employee.getStateProvince(),
//                    employee.getPostalCode(),
//                    employee.getCountry(),
//                    employee.getHireDate(),
//                    employee.getEmploymentStatus(),
//                    employee.getJobTitle(),
//                    employee.getManagerId(),
//                    managerName
//            ));
        }

        log.info("{0} [{1}] end.", objParam);
        return result;
    }

    @Override
    public int updateEmployeeInfoById(long employeeId, EmployeeDTO employeeDTO) {
        return 0;
    }

    @Override
    public int deleteEmployeeInfoById(long employeeId) {
        return 0;
    }
}
