package com.company.payroll.controller;

import com.company.payroll.dto.DepartmentDTO;
import com.company.payroll.dto.DepartmentInfoDTO;
import com.company.payroll.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/department")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<Object> getDepartmentInfoByOffsetLimitWithOrderField
            (@RequestParam(value = "offset", required = false, defaultValue = "0") String offset,
             @RequestParam(value = "limit", required = false, defaultValue = "5") String limit,
             @RequestParam(value = "field", required = false, defaultValue = "departmentId-asc") String field) {

        return ResponseEntity.ok("TODO");
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentInfoDTO> getDepartmentInfoById(@PathVariable("id") String departmentId) {

        System.out.println("departmentId: " + departmentId);

        Optional<DepartmentInfoDTO> departmentInfo = departmentService.getDepartmentInfoByDepartmentId(Long.parseLong(departmentId));


//        if (departmentInfo.isPresent()) {
//            System.out.println("got data");
//        } else {
//            return ResponseEntity.internalServerError().build();
//        }

        return ResponseEntity.of(departmentInfo);
    }

    @PostMapping
    public ResponseEntity<Object> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        int result = departmentService.createDepartmentInfo(departmentDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDepartmentInfoById(@PathVariable("id") String id, @RequestBody Object obj) {
        return ResponseEntity.ok("TODO");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDepartmentInfoById(@PathVariable("id") String id) {
        return ResponseEntity.ok("TODO");
    }
}
