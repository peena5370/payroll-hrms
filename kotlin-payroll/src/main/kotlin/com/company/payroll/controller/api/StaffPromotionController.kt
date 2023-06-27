package com.company.payroll.controller.api

import com.company.payroll.model.StaffPromotion
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
  fun listPromotion(@RequestParam(value = "page", required = true) page: Int, @RequestParam(value = "size", required = true) offset: Int): ResponseEntity<PageInfo<StaffPromotion>> {
    return ResponseEntity.ok(staffMiscellaneousService.listPromotion(page, offset))
  }

  @Operation(summary = "Get promotion list by eid")
  @GetMapping("/{id}/all")
  fun listByEId(@RequestParam(value = "page", required = true) page: Int, @RequestParam(value = "size", required = true) offset: Int, @PathVariable("id") eid: Int): ResponseEntity<PageInfo<StaffPromotion>> {
    return ResponseEntity.ok(staffMiscellaneousService.listPromotionByStaffId(page, offset, eid))
  }

  @Operation(summary = "Get promotion info by id")
  @GetMapping("/{id}")
  fun getById(@PathVariable("id") pid: Int): ResponseEntity<StaffPromotion?> {
    return ResponseEntity.ok(staffMiscellaneousService.findPromotionById(pid))
  }

  @Operation(summary = "Insert promotion info")
  @PostMapping
  fun insert(@RequestBody staffPromotion: StaffPromotion): ResponseEntity<StaffPromotion> {
    return ResponseEntity.ok(staffMiscellaneousService.insertPromotion(staffPromotion))
  }

  @Operation(summary = "Update promotion info.")
  @PutMapping("/{id}")
  fun update(@RequestBody staffPromotion: StaffPromotion): ResponseEntity<StaffPromotion> {
    return ResponseEntity.ok(staffMiscellaneousService.updatePromotion(staffPromotion))
  }

  @Operation(summary = "Delete promotion info.")
  @DeleteMapping("/{id}")
  fun delete(@PathVariable("id") pid: Int): ResponseEntity<Int> {
    return ResponseEntity.ok(staffMiscellaneousService.deletePromotion(pid))
  }
}