package com.company.payroll.controller.api

import com.company.payroll.model.Department
import com.company.payroll.service.CompanyInfoService
import com.github.pagehelper.PageInfo
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/department")
class DepartmentController(@Autowired private val companyInfoService: CompanyInfoService) {
  @Operation(summary = "Get department list")
  @GetMapping
  fun listDepartment(@RequestParam(value = "page", required = true) page: Int, @RequestParam(value = "size", required = true) offset: Int): ResponseEntity<PageInfo<Department>> {
    return ResponseEntity.ok(companyInfoService.listDepartment(page, offset))
  }

  @Operation(summary = "Get department info by id")
  @GetMapping("/{id}")
  fun getById(@PathVariable("id") deptNo: Int): ResponseEntity<Department> {
    return ResponseEntity.ok(companyInfoService.findDepartmentById(deptNo))
  }

  @Operation(summary = "Insert department info")
  @PostMapping
  fun insert(@RequestBody department: Department): ResponseEntity<Department> {
    return ResponseEntity.ok(companyInfoService.insertDepartment(department))
  }

  @Operation(summary = "Update department info.")
  @PutMapping("/{id}")
  fun update(@RequestBody department: Department): ResponseEntity<Department> {
    return ResponseEntity.ok(companyInfoService.updateDepartment(department))
  }

  @Operation(summary = "Delete department info.")
  @DeleteMapping("/{id}")
  fun delete(@PathVariable("id") deptNo: Int): ResponseEntity<Int> {
    return ResponseEntity.ok(companyInfoService.deleteDepartment(deptNo))
  }
}