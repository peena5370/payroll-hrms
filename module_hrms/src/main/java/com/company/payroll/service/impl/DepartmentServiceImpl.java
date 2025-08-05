package com.company.payroll.service.impl;

import com.company.payroll.dto.DepartmentDTO;
import com.company.payroll.dto.DepartmentInfoDTO;
import com.company.payroll.model.Department;
import com.company.payroll.model.Employee;
import com.company.payroll.repository.DepartmentRepository;
import com.company.payroll.repository.EmployeeRepository;
import com.company.payroll.service.DepartmentService;
import com.company.payroll.util.SnowFlakeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private SnowFlakeIdGenerator snowFlakeIdGenerator;

    private DepartmentRepository departmentRepository;

    private EmployeeRepository employeeRepository;

    @Autowired
    public DepartmentServiceImpl(SnowFlakeIdGenerator snowFlakeIdGenerator,
                                 DepartmentRepository departmentRepository,
                                 EmployeeRepository employeeRepository) {
        this.snowFlakeIdGenerator = snowFlakeIdGenerator;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<DepartmentInfoDTO> getDepartmentInfoByDepartmentId(Long departmentId) {
        System.out.println("service departmentId: " + departmentId);
        Optional<Department> department = departmentRepository.findById(departmentId);

        if (department.isPresent()) {
            System.out.println("is department is present");
            Long parentDepartmentId = department.get().getParentDepartmentId();
            String parentDepartmentName = null;
            if(parentDepartmentId != null) {
                Optional<Department> parentDepartment = departmentRepository.findById(parentDepartmentId);

                System.out.println("parentDepartment is null?" + parentDepartment.isPresent());
                if(parentDepartment.isPresent()) {
                    parentDepartmentName = parentDepartment.get().getDepartmentName();
                }
            }

            Optional<Employee> managerInfo = employeeRepository.findById(department.get().getManagerId());

            if (managerInfo.isPresent()) {
                System.out.println("come here for manager info");
            }
            return Optional.of(new DepartmentInfoDTO(department.get().getDepartmentName(), department.get().getDescription(),
                    null, parentDepartmentName,
                    department.get().getLocation(), department.get().getPhoneExtensionCode(), department.get().getDepartmentEmail()));

//            return Optional.of(new DepartmentInfoDTO(department.get().getDepartmentName(), department.get().getDescription(),
//                    managerInfo.get().getFirstName() + managerInfo.get().getLastName(), parentDepartmentName,
//                    department.get().getLocation(), department.get().getPhoneExtensionCode(), department.get().getDepartmentEmail()));

        }

        return Optional.empty();
    }

    @Override
    public int createDepartmentInfo(DepartmentDTO departmentDTO) {
        try {

            Optional<Long> existingDepartmentId = departmentRepository.findIdByDepartmentName(departmentDTO.departmentName());

            if(existingDepartmentId.isPresent()) {
                return -3;
            } else {
                Department department = new Department();
                department.setDepartmentId(snowFlakeIdGenerator.nextId());
                department.setDepartmentName(departmentDTO.departmentName());
                department.setDescription(departmentDTO.description());
                department.setManagerId(departmentDTO.managerId());
                department.setParentDepartmentId(departmentDTO.parentDepartmentId());
                department.setLocation(departmentDTO.location());
                department.setPhoneExtensionCode(departmentDTO.phoneExtensionCode());
                department.setDepartmentEmail(departmentDTO.departmentEmail());
                department.setCreatedAt(LocalDateTime.now());
                department.setUpdatedAt(null);

                Department savedDepartment = departmentRepository.saveAndFlush(department);

                if(savedDepartment.getDepartmentId() != 0L) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } catch (DataIntegrityViolationException e) {
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }

    @Override
    public int updateDepartmentInfoById(long departmentId, DepartmentDTO departmentDTO) {
        return 0;
    }

    @Override
    public int deleteDepartmentInfoById(long departmentId) {
        return 0;
    }
}
