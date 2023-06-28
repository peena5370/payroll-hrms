package com.company.payroll.controller.api

import com.company.payroll.model.StaffSalary
import com.company.payroll.service.StaffSalaryService
import com.github.pagehelper.PageInfo
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/staff/salary")
class StaffSalaryController(@Autowired private val staffSalaryService: StaffSalaryService) {
  @Operation(summary = "Get salary list")
  @GetMapping
  fun listSalary(@RequestParam(value = "page", required = true) page: Int, @RequestParam(value = "size", required = true) offset: Int): ResponseEntity<PageInfo<StaffSalary>> {
    return ResponseEntity.ok(staffSalaryService.list(page, offset))
  }

  @Operation(summary = "Get salary info by id")
  @GetMapping("/{id}")
  fun getById(@PathVariable("id") sid: Int): ResponseEntity<StaffSalary?> {
    return ResponseEntity.ok(staffSalaryService.findById(sid))
  }

  @Operation(summary = "Update salary info.")
  @PutMapping("/{id}")
  fun update(@RequestBody staffSalary: StaffSalary): ResponseEntity<StaffSalary> {
    return ResponseEntity.ok(staffSalaryService.update(staffSalary))
  }
}