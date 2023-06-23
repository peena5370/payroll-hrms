package com.company.payroll.controller.api

import com.company.payroll.model.BankingInfo
import com.company.payroll.service.BankingInfoService
import com.github.pagehelper.PageInfo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/banking")
class StaffBankingController(@Autowired private val bankingInfoService: BankingInfoService) {
  @Operation(summary = "Get banking list")
  @GetMapping
  fun listBankingInfo(@RequestParam(value = "page", required = true) page: Int, @RequestParam(value = "size", required = true) offset: Int): ResponseEntity<PageInfo<BankingInfo>> {
    return ResponseEntity.ok(bankingInfoService.list(page, offset))
  }

  @Operation(summary = "Get banking info by id")
  @GetMapping("/{id}")
  fun getById(@Parameter(description = "Banking id") @PathVariable("id") bid: Int): ResponseEntity<BankingInfo?> {
    return ResponseEntity.ok(bankingInfoService.findById(bid))
  }

  @Operation(summary = "Update banking info.")
  @PutMapping("/{id}")
  fun update(@RequestBody bankingInfo: BankingInfo): ResponseEntity<BankingInfo> {
    return ResponseEntity.ok(bankingInfoService.update(bankingInfo))
  }
}