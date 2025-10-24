package com.company.payroll.department.service.impl;

import com.company.payroll.department.dto.DepartmentDTO;
import com.company.payroll.department.dto.DepartmentInfoDTO;
import com.company.payroll.department.model.Department;
import com.company.payroll.department.model.DepartmentEmployee;
import com.company.payroll.department.model.DepartmentFacilityUnit;
import com.company.payroll.department.repository.DepartmentEmployeeRepository;
import com.company.payroll.department.repository.DepartmentFacilityUnitRepository;
import com.company.payroll.department.repository.DepartmentRepository;
import com.company.payroll.department.service.DepartmentService;
import com.company.payroll.util.util.SnowFlakeIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    public static final String CLASS_NAME = "[DepartmentServiceImpl]";
    private final SnowFlakeIdGenerator snowFlakeIdGenerator;
    private final DepartmentRepository departmentRepository;
    private final DepartmentEmployeeRepository departmentEmployeeRepository;
    private final DepartmentFacilityUnitRepository departmentFacilityUnitRepository;

    public DepartmentServiceImpl(SnowFlakeIdGenerator snowFlakeIdGenerator,
                                 DepartmentRepository departmentRepository,
                                 DepartmentEmployeeRepository departmentEmployeeRepository,
                                 DepartmentFacilityUnitRepository departmentFacilityUnitRepository) {
        this.snowFlakeIdGenerator = snowFlakeIdGenerator;
        this.departmentRepository = departmentRepository;
        this.departmentEmployeeRepository = departmentEmployeeRepository;
        this.departmentFacilityUnitRepository = departmentFacilityUnitRepository;
    }

    @Override
    public List<DepartmentInfoDTO> getAllDepartmentInfoByOffsetLimit(int offset, int limit) {
        final String functionName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("{} {} start.", new Object[]{CLASS_NAME, functionName});

        List<DepartmentInfoDTO> departmentInfoDTOList = new ArrayList<>();

        Sort sort = Sort.by("departmentId").ascending();
        PageRequest pageRequest = PageRequest.of(offset, limit, sort);

        List<Department> departments = departmentRepository.findAll(pageRequest).getContent();

        if (!departments.isEmpty()) {
            for (Department department : departments) {
                DepartmentInfoDTO departmentInfoDTO = new DepartmentInfoDTO(
                        department.getDepartmentId(),
                        department.getDepartmentName(),
                        department.getCostCenterCode(),
                        department.getDescription(),
                        department.getParentDepartmentId(),
                        department.getLocation(),
                        department.getPhoneExtensionCode(),
                        department.getDepartmentEmail(),
                        department.getCreatedAt()
                );

                departmentInfoDTOList.add(departmentInfoDTO);
            }
        }

        log.info("{} {} end. Size={}", new Object[]{CLASS_NAME, functionName, departmentInfoDTOList.size()});
        return departmentInfoDTOList;
    }

    @Override
    public Optional<DepartmentInfoDTO> getDepartmentInfoByDepartmentId(long departmentId) {
        final String functionName = Thread.currentThread().getStackTrace()[1].getMethodName();

        Object[] params = new Object[]{CLASS_NAME, functionName, departmentId};

        log.info("{} {} start. departmentId={}", params);

        Optional<Department> department = departmentRepository.findById(departmentId);

        if (department.isPresent()) {
            Department result = department.get();

            DepartmentInfoDTO departmentInfoDTO = new DepartmentInfoDTO(
                    result.getDepartmentId(),
                    result.getDepartmentName(),
                    result.getCostCenterCode(),
                    result.getDescription(),
                    result.getParentDepartmentId(),
                    result.getLocation(),
                    result.getPhoneExtensionCode(),
                    result.getDepartmentEmail(),
                    result.getCreatedAt()
            );

            log.info("{} {} success for departmentId={}.", params);
            return Optional.of(departmentInfoDTO);
        }

        log.info("{} {} fail. Department info with departmentId={} not exist.", params);
        return Optional.empty();
    }

    @Override
    public int createDepartmentInfo(DepartmentDTO departmentDTO) {
        final String functionName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("{} {} start.", new Object[]{CLASS_NAME, functionName});

        int status = 0;
        try {
            Optional<Long> existingDepartmentId = departmentRepository.findIdByDepartmentCostCenterCode(departmentDTO.costCenterCode());

            if (existingDepartmentId.isPresent()) {
                status = -2;
            } else {
                Department department = new Department();
                department.setDepartmentId(snowFlakeIdGenerator.nextId());
                department.setDepartmentName(departmentDTO.departmentName());
                department.setCostCenterCode(departmentDTO.costCenterCode());
                department.setDescription(departmentDTO.description());
                department.setParentDepartmentId(departmentDTO.parentDepartmentId());
                department.setLocation(departmentDTO.location());
                department.setPhoneExtensionCode(departmentDTO.phoneExtensionCode());
                department.setDepartmentEmail(departmentDTO.departmentEmail());
                department.setCreatedAt(LocalDateTime.now());
                department.setUpdatedAt(null);

                departmentRepository.saveAndFlush(department);

                log.info("{} {} create success.", new Object[]{CLASS_NAME, functionName});
                status = 1;
            }
        } catch (Exception e) {
            log.error("{} {} encountered exception. Message: {}", new Object[]{CLASS_NAME, functionName, e.getMessage()});
            status = -1;
        }

        log.info("{} {} end. Status={}", new Object[]{CLASS_NAME, functionName, status});
        return status;
    }

    @Override
    public int updateDepartmentInfoById(long departmentId, DepartmentDTO departmentDTO) {
        final String functionName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("{} {} start. departmentId={}", new Object[]{CLASS_NAME, functionName, departmentId});

        int status = 0;

        try {
            Optional<Department> department = departmentRepository.findById(departmentId);

            if (department.isEmpty()) {
                log.info("{} {} not found for departmentId={}", new Object[]{CLASS_NAME, functionName, departmentId});
                status = -2;
            } else {
                // check for duplicate department cost center value which clash with other data
                Optional<Long> existingDepartmentId = departmentRepository.findIdByDepartmentCostCenterCode(departmentDTO.costCenterCode());

                if ((existingDepartmentId.isPresent()) && (departmentId != existingDepartmentId.get())) {
                    return -3;
                } else {
                    Department updateDepartment = department.get();
                    updateDepartment.setDepartmentName(departmentDTO.departmentName());
                    updateDepartment.setCostCenterCode(departmentDTO.costCenterCode());
                    updateDepartment.setDescription(departmentDTO.description());
                    updateDepartment.setParentDepartmentId(departmentDTO.parentDepartmentId());
                    updateDepartment.setLocation(departmentDTO.location());
                    updateDepartment.setPhoneExtensionCode(departmentDTO.phoneExtensionCode());
                    updateDepartment.setDepartmentEmail(departmentDTO.departmentEmail());
                    updateDepartment.setUpdatedAt(LocalDateTime.now());

                    departmentRepository.saveAndFlush(updateDepartment);

                    log.info("{} {} update success for departmentId={}", new Object[]{CLASS_NAME, functionName, departmentId});
                    status = 1;
                }
            }
        } catch (Exception e) {
            log.error("{} {} encountered exception. Message: {}", new Object[]{CLASS_NAME, functionName, e.getMessage()});
            status = -1;
        }

        log.info("{} {} end. departmentId={}, status={}", new Object[]{CLASS_NAME, functionName, departmentId, status});
        return status;
    }

    @Override
    public int deleteDepartmentInfoById(long departmentId) {
        final String functionName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("{} {} start. departmentId={}", new Object[]{CLASS_NAME, functionName, departmentId});

        int status = 0;

        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            Optional<List<DepartmentEmployee>> departmentEmployees = departmentEmployeeRepository.getAllByDepartmentId(departmentId);
            Optional<List<DepartmentFacilityUnit>> departmentFacilityUnits = departmentFacilityUnitRepository.getAllByDepartmentId(departmentId);

            if(departmentEmployees.isPresent() || departmentFacilityUnits.isPresent()) {
                log.info("{} {} for departmentId={} is in used, not allow to delete.", new Object[]{CLASS_NAME, functionName, departmentId});
                status = -2;
            } else {
                departmentRepository.delete(department.get());

                log.info("{} {} delete success for departmentId={}", new Object[]{CLASS_NAME, functionName, departmentId});
                status = 1;
            }
        } else {
            log.info("{} {} for departmentId={} not found.", new Object[]{CLASS_NAME, functionName, departmentId});
            status = -1;
        }

        log.info("{} {} end. departmentId={}, Status={}", new Object[]{CLASS_NAME, functionName, departmentId, status});
        return status;
    }
}
