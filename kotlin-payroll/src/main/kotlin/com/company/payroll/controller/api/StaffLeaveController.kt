package com.company.payroll.controller.api

import com.company.payroll.model.StaffLeave
import com.company.payroll.service.StaffApplicationService
import com.github.pagehelper.PageInfo
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/application/leave")
class StaffLeaveController(@Autowired private val staffApplicationService: StaffApplicationService) {
  @Operation(summary = "Get leave list")
  @GetMapping
  fun listLeave(@RequestParam(value = "page", required = true) page: Int, @RequestParam(value = "size", required = true) offset: Int): ResponseEntity<PageInfo<StaffLeave>> {
    return ResponseEntity.ok(staffApplicationService.listLeave(page, offset))
  }

  @Operation(summary = "Get leave list by employee id")
  @GetMapping("/{id}/all")
  fun listByEId(@Parameter(description = "employee id") @PathVariable("id") staffId: Int): ResponseEntity<List<StaffLeave>> {
    return ResponseEntity.ok(staffApplicationService.findLeaveByStaffId(staffId))
  }

  @Operation(summary = "Get leave info by id")
  @GetMapping("/{id}")
  fun getById(@PathVariable("id") lid: Int): ResponseEntity<StaffLeave> {
    return ResponseEntity.ok(staffApplicationService.findLeaveById(lid))
  }

  @Operation(summary = "Add leave info")
  @PostMapping
  fun insert(@RequestBody staffLeave: StaffLeave): ResponseEntity<StaffLeave> {
    return ResponseEntity.ok(staffApplicationService.insertLeave(staffLeave))
  }

  @Operation(summary = "Update leave info.")
  @PutMapping("/{id}")
  fun update(@RequestBody staffLeave: StaffLeave): ResponseEntity<StaffLeave> {
    return ResponseEntity.ok(staffApplicationService.updateLeave(staffLeave))
  }

  @Operation(summary = "Delete leave info.")
  @DeleteMapping("/{id}")
  fun delete(@PathVariable("id") lid: Int): ResponseEntity<Int> {
    return ResponseEntity.ok(staffApplicationService.deleteLeave(lid))
  }
}