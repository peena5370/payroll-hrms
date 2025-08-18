package com.company.payroll.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "company_facility")
public class CompanyFacility {
    @Id
    @Column(name = "facility_id")
    private long facilityId;

    @Column(name = "facility_name", unique = true, nullable = false)
    private String facilityName;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "state_province")
    private String stateProvince;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "contact_person_id")
    private Long contactPersonId;

    @Column(name = "facility_phone_number")
    private String facilityPhoneNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
