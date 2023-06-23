package com.company.payroll.controller.api

import com.company.payroll.model.StaffPayroll
import com.company.payroll.service.StaffPayrollService
import com.github.pagehelper.PageInfo
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Parameter

@RestController
@RequestMapping("/api/payroll")
class StaffPayrollController(@Autowired private val staffPayrollService: StaffPayrollService) {
  @Operation(summary = "Get employee payroll list")
  @GetMapping
  fun listPayrollEmployee(@RequestParam(value = "page", required = true) page: Int, @RequestParam(value = "size", required = true) offset: Int): ResponseEntity<PageInfo<StaffPayroll>> {
    return ResponseEntity.ok(staffPayrollService.listStaffPayroll(page, offset))
  }

  @Operation(summary = "Get payroll list by employee id")
  @GetMapping("/{id}/all")
  fun listPayrollEmployeeByEId(@Parameter(description = "employee id") @PathVariable("id") eid: Int): ResponseEntity<List<StaffPayroll>?> {
    return ResponseEntity.ok(staffPayrollService.findStaffPayrollByStaffId(eid))
  }

  @Operation(summary = "Get employee payroll list by id")
  @GetMapping("/{id}")
  fun getById(@PathVariable("id") id: Int): ResponseEntity<StaffPayroll?> {
    return ResponseEntity.ok(staffPayrollService.findStaffPayrollById(id))
  }

  @Operation(summary = "Insert employee payroll info")
  @PostMapping
  fun insert(@RequestBody staffPayroll: StaffPayroll): ResponseEntity<StaffPayroll> {
    return ResponseEntity.ok(staffPayrollService.insertStaffPayroll(staffPayroll))
  }

  @Operation(summary = "Update employee payroll info.")
  @PutMapping("/{id}")
  fun update(@RequestBody staffPayroll: StaffPayroll): ResponseEntity<StaffPayroll> {
    return ResponseEntity.ok(staffPayrollService.updateStaffPayroll(staffPayroll))
  }

  @Operation(summary = "Delete employee payroll info.")
  @DeleteMapping("/{id}")
  fun delete(@PathVariable("id") prId: Int): ResponseEntity<Int> {
    return ResponseEntity.ok(staffPayrollService.deleteStaffPayroll(prId))
  }
}