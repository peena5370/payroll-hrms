package com.company.payroll.controller.api

import com.company.payroll.model.StaffLeaveDetails
import com.company.payroll.service.StaffLeaveDetailsService
import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/staff/leave")
class StaffLeaveDetailsController(@Autowired private val staffLeaveDetailsService: StaffLeaveDetailsService) {
  @GetMapping
  fun list(@RequestParam("page") page: Int, @RequestParam("size") size: Int): ResponseEntity<PageInfo<StaffLeaveDetails>> {
    return ResponseEntity.ok(staffLeaveDetailsService.list(page, size))
  }

  @GetMapping("{id}")
  fun findById(@PathVariable("id") ldId: Int): ResponseEntity<StaffLeaveDetails> {
    return ResponseEntity.ok(staffLeaveDetailsService.findById(ldId))
  }

  @PutMapping("/{id}")
  fun update(@RequestBody staffLeaveDetails: StaffLeaveDetails): ResponseEntity<StaffLeaveDetails> {
    return ResponseEntity.ok(staffLeaveDetailsService.update(staffLeaveDetails))
  }

}