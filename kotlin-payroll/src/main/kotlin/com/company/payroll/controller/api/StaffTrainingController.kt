package com.company.payroll.controller.api

import com.company.payroll.model.StaffTraining
import com.company.payroll.service.StaffMiscellaneousService
import com.github.pagehelper.PageInfo
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Parameter

@RestController
@RequestMapping("/api/training")
class StaffTrainingController(@Autowired private val staffMiscellaneousService: StaffMiscellaneousService) {
  @Operation(summary = "Get training list")
  @GetMapping
  fun listTraining(@RequestParam(value = "page", required = true) page: Int, @RequestParam(value = "size", required = true) offset: Int): ResponseEntity<PageInfo<StaffTraining>> {
    return ResponseEntity.ok(staffMiscellaneousService.listTraining(page, offset))
  }

  @Operation(summary = "Get training info by id")
  @GetMapping("/{id}")
  fun getById(@PathVariable("id") tId: Int): ResponseEntity<StaffTraining?> {
    return ResponseEntity.ok(staffMiscellaneousService.findTrainingById(tId))
  }

  @Operation(summary = "Get training list by employee id")
  @GetMapping("/employee/{id}/all")
  fun getListByStaffId(@Parameter(description = "employee id") @PathVariable("id") eId: Int): ResponseEntity<List<StaffTraining>?> {
    return ResponseEntity.ok(staffMiscellaneousService.findTrainingByStaffId(eId))
  }

  @Operation(summary = "Insert training info")
  @PostMapping
  fun insert(@RequestBody staffTraining: StaffTraining): ResponseEntity<StaffTraining> {
    return ResponseEntity.ok(staffMiscellaneousService.insertTraining(staffTraining))
  }

  @Operation(summary = "Update training info.")
  @PutMapping("/{id}")
  fun update(@RequestBody staffTraining: StaffTraining): ResponseEntity<StaffTraining> {
    return ResponseEntity.ok(staffMiscellaneousService.updateTraining(staffTraining))
  }

  @Operation(summary = "Delete training info.")
  @DeleteMapping("/{id}")
  fun delete(@PathVariable("id") tId: Int): ResponseEntity<Int> {
    return ResponseEntity.ok(staffMiscellaneousService.deleteTraining(tId))
  }
}