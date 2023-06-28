package com.company.payroll.controller.api

import com.company.payroll.model.StaffLoan
import com.company.payroll.service.StaffApplicationService
import com.github.pagehelper.PageInfo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/application/loan")
class StaffLoanController(@Autowired private val staffApplicationService: StaffApplicationService) {
  @Operation(summary = "Get loan list")
  @GetMapping
  fun list(@RequestParam(value = "page", required = true) page: Int, @RequestParam(value = "size", required = true) offset: Int): ResponseEntity<PageInfo<StaffLoan>> {
    return ResponseEntity.ok(staffApplicationService.listLoan(page, offset))
  }

  @Operation(summary = "Get loan list by employee id")
  @GetMapping("/{id}/all")
  fun listByStaffId(@Parameter(description = "employee id") @PathVariable("id") staffId: Int): ResponseEntity<List<StaffLoan>> {
    return ResponseEntity.ok(staffApplicationService.findLoanByStaffId(staffId))
  }

  @Operation(summary = "Get loan info by id")
  @GetMapping("/{id}")
  fun findById(@PathVariable("id") loanId: Int): ResponseEntity<StaffLoan> {
    return ResponseEntity.ok(staffApplicationService.findLoanById(loanId))
  }

  @Operation(summary = "Add loan info")
  @PostMapping
  fun insert(@RequestBody staffLoan: StaffLoan): ResponseEntity<StaffLoan> {
    return ResponseEntity.ok(staffApplicationService.insertLoan(staffLoan))
  }

  @Operation(summary = "Update loan info.")
  @PutMapping("/{id}")
  fun update(@RequestBody staffLoan: StaffLoan): ResponseEntity<StaffLoan> {
    return ResponseEntity.ok(staffApplicationService.updateLoan(staffLoan))
  }

  @Operation(summary = "Delete loan info.")
  @DeleteMapping("/{id}")
  fun delete(@PathVariable("id") loanId: Int): ResponseEntity<Int> {
    return ResponseEntity.ok(staffApplicationService.deleteLoan(loanId))
  }
}