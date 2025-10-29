package com.company.payroll.facility.dto;

import java.time.LocalDateTime;

public record CompanyFacilityDetailDTO(
        Long facilityId,
        LocalDateTime createdAt,
        CompanyFacilityDTO detail
) {
}
