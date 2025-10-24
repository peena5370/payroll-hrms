package com.company.payroll.department.controller;

import com.company.payroll.department.dto.DepartmentDTO;
import com.company.payroll.department.dto.DepartmentInfoDTO;
import com.company.payroll.department.service.DepartmentService;
import com.company.payroll.util.response.CommonResponse;
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

    private static final String CLASS_NAME = "[DepartmentController]";
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<CommonResponse> getDepartmentInfoByOffsetLimit
            (@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
             @RequestParam(value = "limit", required = false, defaultValue = "5") Integer limit) {
        final String functionName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("{} {} start. Request param: offset={}, limit={}", new Object[]{CLASS_NAME, functionName, offset, limit});

        List<DepartmentInfoDTO> departmentList = departmentService.getAllDepartmentInfoByOffsetLimit(offset, limit);

        CommonResponse response = departmentList.isEmpty() ?
                new CommonResponse(BAD_REQUEST.value(), "Error when retrieving the department info.", null) :
                new CommonResponse(OK.value(), "Success retrieve department info", departmentList);

        log.info("{} {} end. Response={}", new Object[]{CLASS_NAME, functionName, response.statusCode()});
        return ResponseEntity.status(response.statusCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getDepartmentInfoById(@PathVariable("id") Long departmentId) {
        final String functionName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("{} {} start. departmentId={}.", new Object[]{CLASS_NAME, functionName, departmentId});

        Optional<DepartmentInfoDTO> departmentInfo = departmentService.getDepartmentInfoByDepartmentId(departmentId);

        CommonResponse response = departmentInfo.map(departmentInfoDTO ->
                new CommonResponse(OK.value(), "Department info retrieve success.", departmentInfoDTO))
                .orElseGet(() -> new CommonResponse(NOT_FOUND.value(), "Department info not found.", null));

        log.info("{} {} end. Status={}", new Object[]{CLASS_NAME, functionName, response.statusCode()});
        return ResponseEntity.status(response.statusCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<CommonResponse> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        final String functionName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("{} {} start.", new Object[]{CLASS_NAME, functionName});

        int result = departmentService.createDepartmentInfo(departmentDTO);

        Map<Integer, CommonResponse> responses = Map.ofEntries(
                Map.entry(1, new CommonResponse(OK.value(), "Department info inserted success", null)),
                Map.entry(0, new CommonResponse(BAD_REQUEST.value(), "Department info inserted failed", null)),
                Map.entry(-1, new CommonResponse(INTERNAL_SERVER_ERROR.value(), "API exception encountered. Please check backend log for status", null)),
                Map.entry(-2, new CommonResponse(BAD_REQUEST.value(), "Department info exist", null))
        );

        CommonResponse response = responses.getOrDefault(result, new CommonResponse(INTERNAL_SERVER_ERROR.value(),
                CommonResponse.COMMON_ERROR_MESSAGE, null));

        log.info("{} {} end. Response={}", new Object[]{CLASS_NAME, functionName, response.statusCode()});
        return ResponseEntity.status(response.statusCode()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDepartmentInfoById(@PathVariable("id") Long departmentId, @RequestBody DepartmentDTO departmentDTO) {
        final String functionName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("{} {} start. departmentId={}", new Object[]{CLASS_NAME, functionName, departmentId});
        int updatedResult = departmentService.updateDepartmentInfoById(departmentId, departmentDTO);

        Map<Integer, CommonResponse> responses = Map.ofEntries(
                Map.entry(1, new CommonResponse(OK.value(), "Department info updated success", null)),
                Map.entry(0, new CommonResponse(BAD_REQUEST.value(), "Department info updated failed", null)),
                Map.entry(-1, new CommonResponse(INTERNAL_SERVER_ERROR.value(), "API exception encountered. Please check backend log for status", null)),
                Map.entry(-2, new CommonResponse(NOT_FOUND.value(), "Department info not found", null)),
                Map.entry(-3, new CommonResponse(BAD_REQUEST.value(), "Department cost center code duplicated", null))
        );

        CommonResponse response = responses.getOrDefault(updatedResult, new CommonResponse(INTERNAL_SERVER_ERROR.value(),
                CommonResponse.COMMON_ERROR_MESSAGE, null));

        log.info("{} {} end. departmentId={}, Status={}", new Object[]{CLASS_NAME, functionName, departmentId, response.statusCode()});
        return ResponseEntity.status(response.statusCode()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteDepartmentInfoById(@PathVariable("id") String departmentId) {
        final String functionName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("{} {} start. departmentId={}", new Object[]{CLASS_NAME, functionName, departmentId});

        int deletedResult = departmentService.deleteDepartmentInfoById(Long.parseLong(departmentId));

        Map<Integer, CommonResponse> responses = Map.ofEntries(
                Map.entry(1, new CommonResponse(OK.value(), "Department info delete success.", null)),
                Map.entry(0, new CommonResponse(BAD_REQUEST.value(), "Department info delete failed.", null)),
                Map.entry(-1, new CommonResponse(NOT_FOUND.value(), "Department info not found", null)),
                Map.entry(-2, new CommonResponse(BAD_REQUEST.value(), "Department info is in used, not allow to delete", null))
        );

        CommonResponse response = responses.getOrDefault(deletedResult, new CommonResponse(INTERNAL_SERVER_ERROR.value(),
                CommonResponse.COMMON_ERROR_MESSAGE, null));

        log.info("{} {} end. departmentId={}, Status={}", new Object[]{CLASS_NAME, functionName, departmentId, response.statusCode()});
        return ResponseEntity.status(response.statusCode()).body(response);
    }
}
