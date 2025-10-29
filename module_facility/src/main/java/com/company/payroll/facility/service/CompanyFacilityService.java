package com.company.payroll.facility.service;

import com.company.payroll.facility.dto.CompanyFacilityDTO;
import com.company.payroll.facility.dto.CompanyFacilityDetailDTO;

import java.util.List;
import java.util.Optional;

public interface CompanyFacilityService {

    int createCompanyFacilityDetail(CompanyFacilityDTO companyFacilityDTO);

    List<CompanyFacilityDetailDTO> getAllCompanyFacilityDetailByOffsetAndLimit(int offset, int limit);

    Optional<CompanyFacilityDetailDTO> getCompanyFacilityDetailById(long facilityId);

    int updateCompanyFacilityDetailById(long facilityId, CompanyFacilityDTO companyFacilityDTO);

    int deleteCompanyFacilityDetailById(long facilityId);
}
