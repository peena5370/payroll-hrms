package com.company.payroll.controller.api

import com.company.payroll.model.Loan
import com.company.payroll.service.StaffApplicationService
import com.github.pagehelper.PageInfo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/loan")
class StaffLoanController(@Autowired private val staffApplicationService: StaffApplicationService) {
  @Operation(summary = "Get loan list")
  @GetMapping
  fun listLoan(@RequestParam(value = "page", required = true) page: Int, @RequestParam(value = "size", required = true) offset: Int): ResponseEntity<PageInfo<Loan>> {
    return ResponseEntity.ok(staffApplicationService.listLoan(page, offset))
  }

  @Operation(summary = "Get loan list by employee id")
  @GetMapping("/{id}/all")
  fun listLoanByEId(@Parameter(description = "employee id") @PathVariable("id") eid: Int): ResponseEntity<List<Loan>?> {
    return ResponseEntity.ok(staffApplicationService.findLoanByEId(eid))
  }

  @Operation(summary = "Get loan info by id")
  @GetMapping("/{id}")
  fun getById(@PathVariable("id") lid: Int): ResponseEntity<Loan?> {
    return ResponseEntity.ok(staffApplicationService.findLoanById(lid))
  }

  @Operation(summary = "Add loan info")
  @PostMapping
  fun insert(@RequestBody loan: Loan): ResponseEntity<Loan> {
    return ResponseEntity.ok(staffApplicationService.insertLoan(loan))
  }

  @Operation(summary = "Update loan info.")
  @PutMapping("/{id}")
  fun update(@RequestBody loan: Loan): ResponseEntity<Loan> {
    return ResponseEntity.ok(staffApplicationService.updateLoan(loan))
  }

  @Operation(summary = "Delete loan info.")
  @DeleteMapping("/{id}")
  fun delete(@PathVariable("id") lid: Int): ResponseEntity<Int> {
    val status = staffApplicationService.deleteLoan(lid)
    return if (status == 0) {
      ResponseEntity.status(HttpStatus.FORBIDDEN).body(status)
    } else ResponseEntity.ok(status)
  }
}