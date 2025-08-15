package com.company.payroll.controller;

import com.company.payroll.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/facility")
public class FacilityController {

    @GetMapping
    public ResponseEntity<CommonResponse> getCompanyFacilityInfoByOffsetLimitWithOrderField
            (@RequestParam(value = "offset", required = false, defaultValue = "0") String offset,
             @RequestParam(value = "limit", required = false, defaultValue = "5") String limit,
             @RequestParam(value = "field", required = false, defaultValue = "facilityId-asc") String sortOrder) {
        // @TODO awaiting implementation
        return ResponseEntity.status(200).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getCompanyFacilityInfoById(@PathVariable("id") String facilityId) {
        // @TODO awaiting implementation
        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<CommonResponse> createCompanyFacilityInfo(@RequestBody Object companyFacilityDTO) {
        // @TODO awaiting implementation
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse> updateCompanyFacilityInfoById(@PathVariable("id") String facilityId, Object companyFacilityDTO) {
        // @TODO awaiting implementation
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteCompanyFacilityInfoById(@PathVariable("id") String facilityId) {
        // @TODO awaiting implementation
        return ResponseEntity.ok(null);
    }
}
