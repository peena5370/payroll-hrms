package com.company.payroll.facility.dto;

public record CompanyFacilityDTO(
       String facilityName,
       String addressLine1,
       String addressLine2,
       String city,
       String stateProvince,
       String postalCode,
       String country,
       Long contactPersonId,
       String facilityPhoneNumber
) {
}
