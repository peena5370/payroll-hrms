package com.company.payroll.controller.api

import com.company.payroll.model.Salary
import com.company.payroll.service.SalaryService
import com.github.pagehelper.PageInfo
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/salary")
class StaffSalaryController(@Autowired private val salaryService: SalaryService) {
  @Operation(summary = "Get salary list")
  @GetMapping
  fun listSalary(@RequestParam(value = "page", required = true) page: Int, @RequestParam(value = "size", required = true) offset: Int): ResponseEntity<PageInfo<Salary>> {
    return ResponseEntity.ok(salaryService.list(page, offset))
  }

  @Operation(summary = "Get salary info by id")
  @GetMapping("/{id}")
  fun getById(@PathVariable("id") sid: Int): ResponseEntity<Salary?> {
    return ResponseEntity.ok(salaryService.findById(sid))
  }

  @Operation(summary = "Update salary info.")
  @PutMapping("/{id}")
  fun update(@RequestBody salary: Salary): ResponseEntity<Salary> {
    return ResponseEntity.ok(salaryService.update(salary))
  }
}