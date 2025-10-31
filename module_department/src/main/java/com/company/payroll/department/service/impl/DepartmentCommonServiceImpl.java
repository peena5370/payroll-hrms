package com.company.payroll.department.service.impl;

import com.company.payroll.common.DepartmentCommonService;
import com.company.payroll.department.model.DepartmentEmployee;
import com.company.payroll.department.model.DepartmentFacilityUnit;
import com.company.payroll.department.repository.DepartmentEmployeeRepository;
import com.company.payroll.department.repository.DepartmentFacilityUnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service("departmentCommonService")
public class DepartmentCommonServiceImpl implements DepartmentCommonService {
    public static final String CLASS_NAME = "[DepartmentCommonServiceImpl]";

    private final DepartmentFacilityUnitRepository departmentFacilityUnitRepository;
    private final DepartmentEmployeeRepository departmentEmployeeRepository;

    public DepartmentCommonServiceImpl(DepartmentFacilityUnitRepository departmentFacilityUnitRepository,
                                       DepartmentEmployeeRepository departmentEmployeeRepository) {
        this.departmentFacilityUnitRepository = departmentFacilityUnitRepository;
        this.departmentEmployeeRepository = departmentEmployeeRepository;
    }

    @Override
    public boolean isFacilityUnitInUsed(long facilityId) {
        final String functionName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("{} {} start. facilityId={}", new Object[]{CLASS_NAME, functionName, facilityId});

        List<DepartmentFacilityUnit> result = departmentFacilityUnitRepository.getAllByFacilityId(facilityId);

        boolean isInUsed = (result != null && !result.isEmpty());

        log.info("{} {} end. facilityId={}, result={}", new Object[]{CLASS_NAME, functionName, facilityId, isInUsed});
        return isInUsed;
    }

    @Override
    public boolean isDepartmentEmployeeExist(long employeeId) {
        final String functionName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("{} {} start. employeeId={}", new Object[]{CLASS_NAME, functionName, employeeId});

        List<DepartmentEmployee> result = departmentEmployeeRepository.getAllByDepartmentEmployeeId(employeeId);

        boolean isExist = (result != null && !result.isEmpty());

        log.info("{} {} end. employeeId={}, result={}", new Object[]{CLASS_NAME, functionName, employeeId, isExist});
        return isExist;
    }
}
