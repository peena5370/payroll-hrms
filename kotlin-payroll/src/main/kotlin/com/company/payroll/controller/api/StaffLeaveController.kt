package com.company.payroll.controller.api

import com.company.payroll.model.Leave
import com.company.payroll.service.StaffApplicationService
import com.github.pagehelper.PageInfo
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/leave")
class StaffLeaveController(@Autowired private val staffApplicationService: StaffApplicationService) {
  @Operation(summary = "Get leave list")
  @GetMapping
  fun listLeave(@RequestParam(value = "page", required = true) page: Int, @RequestParam(value = "size", required = true) offset: Int): ResponseEntity<PageInfo<Leave>> {
    return ResponseEntity.ok(staffApplicationService.listLeave(page, offset))
  }

  @Operation(summary = "Get leave list by employee id")
  @GetMapping("/{id}/all")
  fun listByEId(@Parameter(description = "employee id") @PathVariable("id") eid: Int): ResponseEntity<List<Leave>?> {
    return ResponseEntity.ok(staffApplicationService.findLeaveByEId(eid))
  }

  @Operation(summary = "Get leave info by id")
  @GetMapping("/{id}")
  fun getById(@PathVariable("id") lid: Int): ResponseEntity<Leave?> {
    return ResponseEntity.ok(staffApplicationService.findLeaveById(lid))
  }

  @Operation(summary = "Add leave info")
  @PostMapping
  fun insert(@RequestBody leave: Leave): ResponseEntity<Leave> {
    return ResponseEntity.ok(staffApplicationService.insertLeave(leave))
  }

  @Operation(summary = "Update leave info.")
  @PutMapping("/{id}")
  fun update(@RequestBody leave: Leave): ResponseEntity<Leave> {
    return ResponseEntity.ok(staffApplicationService.updateLeave(leave))
  }

  @Operation(summary = "Delete leave info.")
  @DeleteMapping("/{id}")
  fun delete(@PathVariable("id") lid: Int): ResponseEntity<Int> {
    val status = staffApplicationService.deleteLeave(lid)
    return if (status == 0) {
      ResponseEntity.status(HttpStatus.FORBIDDEN).body(status)
    } else ResponseEntity.ok(status)
  }
}