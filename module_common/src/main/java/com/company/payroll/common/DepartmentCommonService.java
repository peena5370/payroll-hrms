package com.company.payroll.common;

public interface DepartmentCommonService {
    boolean isFacilityUnitInUsed(long facilityId);

    boolean isDepartmentEmployeeExist(long employeeId);
}
