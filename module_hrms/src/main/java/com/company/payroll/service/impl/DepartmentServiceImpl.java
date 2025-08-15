package com.company.payroll.service.impl;

import com.company.payroll.dto.DepartmentDTO;
import com.company.payroll.dto.DepartmentInfoDTO;
import com.company.payroll.dto.DepartmentInfosDTO;
import com.company.payroll.model.Department;
import com.company.payroll.model.Employee;
import com.company.payroll.repository.DepartmentRepository;
import com.company.payroll.repository.EmployeeRepository;
import com.company.payroll.service.DepartmentService;
import com.company.payroll.util.SnowFlakeIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.company.payroll.util.PageHelper.buildSortFromSortField;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final SnowFlakeIdGenerator snowFlakeIdGenerator;

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public DepartmentServiceImpl(SnowFlakeIdGenerator snowFlakeIdGenerator,
                                 DepartmentRepository departmentRepository,
                                 EmployeeRepository employeeRepository) {
        this.snowFlakeIdGenerator = snowFlakeIdGenerator;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<DepartmentInfosDTO> getAllDepartmentInfoByOffsetLimitAndSortOrder(int offset, int limit, String sortOrder) {

        List<DepartmentInfosDTO> departmentInfos = new ArrayList<>();

        Map<String, String> departmentColumnMap = Map.of(
                "departmentId", "departmentId",
                "departmentName", "departmentName",
                "description", "description",
                "managerId", "managerId",
                "parentDepartmentId", "parentDepartmentId",
                "location", "location",
                "phoneExtensionCode", "phoneExtensionCode",
                "departmentEmail", "departmentEmail");

        Sort sort = buildSortFromSortField(departmentColumnMap, sortOrder);

        PageRequest pageRequest = PageRequest.of(offset, limit, sort);

        List<Department> departments = departmentRepository.findAll(pageRequest).getContent();

        if(!departments.isEmpty()) {
            for (Department department : departments) {
                DepartmentInfosDTO departmentInfo = new DepartmentInfosDTO(
                        department.getDepartmentId(),
                        department.getDepartmentName(),
                        department.getDescription(),
                        department.getManagerId(),
                        department.getParentDepartmentId(),
                        department.getLocation(),
                        department.getPhoneExtensionCode(),
                        department.getDepartmentEmail());

                departmentInfos.add(departmentInfo);
            }
        }

        return departmentInfos;
    }

    @Override
    public Optional<DepartmentInfoDTO> getDepartmentInfoByDepartmentId(long departmentId) {
        log.info("\"getDepartmentInfoByDepartmentId\" departmentId: {}", departmentId);
        Optional<Department> department = departmentRepository.findById(departmentId);

        if (department.isPresent()) {
            Long parentDepartmentId = department.get().getParentDepartmentId();
            String parentDepartmentName = null;
            if (parentDepartmentId != null) {
                Optional<Department> parentDepartment = departmentRepository.findById(parentDepartmentId);

                if (parentDepartment.isPresent()) {
                    parentDepartmentName = parentDepartment.get().getDepartmentName();
                }
            }

            String managerName = null;
            Optional<Employee> managerInfo = employeeRepository.findById(department.get().getManagerId());

            if (managerInfo.isPresent()) {
                managerName = managerInfo.get().getFirstName() + " " + managerInfo.get().getLastName();
            }

            log.info("\"getDepartmentInfoByDepartmentId\" query result success. {}", departmentId);
            return Optional.of(new DepartmentInfoDTO(department.get().getDepartmentName(), department.get().getDescription(),
                    managerName, parentDepartmentName,
                    department.get().getLocation(), department.get().getPhoneExtensionCode(), department.get().getDepartmentEmail()));
        }

        return Optional.empty();
    }

    @Override
    public int createDepartmentInfo(DepartmentDTO departmentDTO) {
        try {
            Optional<Long> existingDepartmentId = departmentRepository.findIdByDepartmentName(departmentDTO.departmentName());

            if (existingDepartmentId.isPresent()) {
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

                if (savedDepartment.getDepartmentId() != 0L) {
                    return 1;
                } else {
                    return 0;
                }
            }
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }

    @Override
    public int updateDepartmentInfoById(long departmentId, DepartmentDTO departmentDTO) {
        try {
            Optional<Department> department = departmentRepository.findById(departmentId);

            if (department.isEmpty()) {
                return -3;
            } else {
                // check for duplicate department name which clash with other data
                Optional<Long> existingDepartmentId = departmentRepository.findIdByDepartmentName(departmentDTO.departmentName());

                if ((existingDepartmentId.isPresent()) && (departmentId != existingDepartmentId.get())) {
                    return -4;
                } else {
                    department.get().setDepartmentName(departmentDTO.departmentName());
                    department.get().setDescription(departmentDTO.description());
                    department.get().setManagerId(departmentDTO.managerId());
                    department.get().setParentDepartmentId(departmentDTO.parentDepartmentId());
                    department.get().setLocation(departmentDTO.location());
                    department.get().setPhoneExtensionCode(departmentDTO.phoneExtensionCode());
                    department.get().setDepartmentEmail(departmentDTO.departmentEmail());
                    department.get().setUpdatedAt(LocalDateTime.now());

                    departmentRepository.saveAndFlush(department.get());

                    return 1;
                }
            }
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }

    @Override
    public int deleteDepartmentInfoById(long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);

        if(department.isPresent()) {
            departmentRepository.delete(department.get());

            return 1;
        } else {
            return 0;
        }
    }
}
