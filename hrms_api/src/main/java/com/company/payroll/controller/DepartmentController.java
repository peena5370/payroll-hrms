package com.company.payroll.controller;

import com.company.payroll.dto.DepartmentDTO;
import com.company.payroll.dto.DepartmentInfoDTO;
import com.company.payroll.dto.DepartmentInfosDTO;
import com.company.payroll.response.CommonResponse;
import com.company.payroll.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequestMapping("api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<CommonResponse> getDepartmentInfoByOffsetLimitWithOrderField
            (@RequestParam(value = "offset", required = false, defaultValue = "0") String offset,
             @RequestParam(value = "limit", required = false, defaultValue = "5") String limit,
             @RequestParam(value = "field", required = false, defaultValue = "departmentId-asc") String sortOrder) {
        log.info("\"getDepartmentInfoByOffsetLimitWithOrderField\" started. Queried param: offset: {0}, limit: {1}, field: {2}",
                new Object[]{offset, limit, sortOrder});

        List<DepartmentInfosDTO> departmentList = departmentService.getAllDepartmentInfoByOffsetLimitAndSortOrder(Integer.parseInt(offset),
                Integer.parseInt(limit), sortOrder);

        CommonResponse response = null;
        if(departmentList.isEmpty()) {
            response = new CommonResponse(BAD_REQUEST.value(), "Error when retrieving the department information list", null);
        } else {
            response = new CommonResponse(OK.value(), "Success retrieve department information list", departmentList);
        }

        return ResponseEntity.status(response.statusCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getDepartmentInfoById(@PathVariable("id") String departmentId) {
        log.info("\"getDepartmentInfoById\" started. departmentId: {}", departmentId);

        Optional<DepartmentInfoDTO> departmentInfo = departmentService.getDepartmentInfoByDepartmentId(Long.parseLong(departmentId));

        CommonResponse response = null;

        if (departmentInfo.isPresent()) {
            response = new CommonResponse(OK.value(), "success", departmentInfo);
        } else {
            response = new CommonResponse(NOT_FOUND.value(), "info not found", null);
        }

        log.info("\"getDepartmentInfoById\" ended. Response: {}", response.statusCode());
        return ResponseEntity.status(response.statusCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<CommonResponse> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        log.info("\"createDepartment\" started.");
        int result = departmentService.createDepartmentInfo(departmentDTO);

        Map<Integer, CommonResponse> responses = Map.ofEntries(
                Map.entry(1, new CommonResponse(OK.value(), "Department info inserted success", null)),
                Map.entry(0, new CommonResponse(BAD_REQUEST.value(), "Department info inserted failed", null)),
                Map.entry(-3, new CommonResponse(BAD_REQUEST.value(), "Department info exist", null)),
                Map.entry(-2, new CommonResponse(INTERNAL_SERVER_ERROR.value(), "API exception encountered. Please check backend log for status", null)),
                Map.entry(-1, new CommonResponse(INTERNAL_SERVER_ERROR.value(), "API exception encountered. Please check backend log for status", null))
        );

        CommonResponse response = responses.getOrDefault(result, new CommonResponse(INTERNAL_SERVER_ERROR.value(),
                "API returned uncommon status. Please check backend log for status", null));

        log.info("\"createDepartment\" ended. Response: {}", response.statusCode());
        return ResponseEntity.status(response.statusCode()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDepartmentInfoById(@PathVariable("id") String departmentId, @RequestBody DepartmentDTO departmentDTO) {
        log.info("\"updateDepartmentInfoById\" started. departmentId: {}", departmentId);
        int updatedResult = departmentService.updateDepartmentInfoById(Long.parseLong(departmentId), departmentDTO);

        Map<Integer, CommonResponse> responses = Map.ofEntries(
                Map.entry(1, new CommonResponse(OK.value(), "Department info updated success", null)),
                Map.entry(0, new CommonResponse(BAD_REQUEST.value(), "Department info updated failed", null)),
                Map.entry(-4, new CommonResponse(BAD_REQUEST.value(), "Department name duplicated", null)),
                Map.entry(-3, new CommonResponse(NOT_FOUND.value(), "Department info not found", null)),
                Map.entry(-2, new CommonResponse(INTERNAL_SERVER_ERROR.value(), "API exception encountered. Please check backend log for status", null)),
                Map.entry(-1, new CommonResponse(INTERNAL_SERVER_ERROR.value(), "API exception encountered. Please check backend log for status", null))
        );

        CommonResponse response = responses.getOrDefault(updatedResult, new CommonResponse(INTERNAL_SERVER_ERROR.value(),
                "API returned uncommon status. Please check backend log for status", null));

        log.info("\"updateDepartmentInfoById\" ended. Response: {}", response.statusCode());
        return ResponseEntity.status(response.statusCode()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteDepartmentInfoById(@PathVariable("id") String departmentId) {
        log.info("\"deleteDepartmentInfoById\" started. departmentId: {}", departmentId);
        CommonResponse response = null;

        int deletedResult = departmentService.deleteDepartmentInfoById(Long.parseLong(departmentId));

        if(deletedResult == 1) {
            response = new CommonResponse(OK.value(), "Department info delete success", null);
        } else {
            response = new CommonResponse(BAD_REQUEST.value(), "Failed to delete department info", null);
        }

        log.info("\"deleteDepartmentInfoById\" ended. Response: {}", response.statusCode());
        return ResponseEntity.status(response.statusCode()).body(response);
    }
}
