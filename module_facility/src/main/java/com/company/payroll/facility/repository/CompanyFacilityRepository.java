package com.company.payroll.facility.repository;

import com.company.payroll.facility.model.CompanyFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyFacilityRepository extends JpaRepository<CompanyFacility, Long> {

    @Query("SELECT cf.facilityId FROM CompanyFacility cf WHERE cf.facilityName = :facilityName")
    Optional<Long> findIdByFacilityName(@Param("facilityName") String facilityName);
}
