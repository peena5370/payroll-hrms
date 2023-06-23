package com.company.payroll.controller.api

import com.company.payroll.model.Promotion
import com.company.payroll.service.StaffMiscellaneousService
import com.github.pagehelper.PageInfo
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/promotion")
class StaffPromotionController(@Autowired private val staffMiscellaneousService: StaffMiscellaneousService) {
  @Operation(summary = "Get promotion list")
  @GetMapping
  fun listPromotion(@RequestParam(value = "page", required = true) page: Int, @RequestParam(value = "size", required = true) offset: Int): ResponseEntity<PageInfo<Promotion>> {
    return ResponseEntity.ok(staffMiscellaneousService.listPromotion(page, offset))
  }

  @Operation(summary = "Get promotion list by eid")
  @GetMapping("/{id}/all")
  fun listByEId(@RequestParam(value = "page", required = true) page: Int, @RequestParam(value = "size", required = true) offset: Int, @PathVariable("id") eid: Int): ResponseEntity<PageInfo<Promotion>> {
    return ResponseEntity.ok(staffMiscellaneousService.listPromotionByStaffId(page, offset, eid))
  }

  @Operation(summary = "Get promotion info by id")
  @GetMapping("/{id}")
  fun getById(@PathVariable("id") pid: Int): ResponseEntity<Promotion?> {
    return ResponseEntity.ok(staffMiscellaneousService.findPromotionById(pid))
  }

  @Operation(summary = "Insert promotion info")
  @PostMapping
  fun insert(@RequestBody promotion: Promotion): ResponseEntity<Promotion> {
    return ResponseEntity.ok(staffMiscellaneousService.insertPromotion(promotion))
  }

  @Operation(summary = "Update promotion info.")
  @PutMapping("/{id}")
  fun update(@RequestBody promotion: Promotion): ResponseEntity<Promotion> {
    return ResponseEntity.ok(staffMiscellaneousService.updatePromotion(promotion))
  }

  @Operation(summary = "Delete promotion info.")
  @DeleteMapping("/{id}")
  fun delete(@PathVariable("id") pid: Int): ResponseEntity<Int> {
    return ResponseEntity.ok(staffMiscellaneousService.deletePromotion(pid))
  }
}