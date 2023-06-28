package com.company.payroll.controller.api

import com.company.payroll.model.StaffAttendance
import com.company.payroll.service.StaffAttendanceService
import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/attendance")
class StaffAttendanceController(@Autowired private val staffAttendanceService: StaffAttendanceService) {

  @GetMapping
  fun getStaffAttendance(@RequestParam("page") page: Int, @RequestParam("size") size: Int, @RequestBody staffAttendance: StaffAttendance): ResponseEntity<PageInfo<StaffAttendance>> {
    return ResponseEntity.ok(staffAttendance.startDate?.let {
                                  staffAttendance.endDate?.let {
                                    it1 -> staffAttendanceService.getStaffAttendance(staffAttendance.staffId, it, it1, page, size)
                                  }
                              })
  }

  @PostMapping("/check_in/1")
  fun staffCheckInOne(@RequestBody staffAttendance: StaffAttendance): ResponseEntity<Int> {
    return ResponseEntity.ok(staffAttendanceService.staffCheckIn(staffAttendance))
  }

  @PutMapping("/check_out/1")
  fun staffCheckOutOne(@RequestBody staffAttendance: StaffAttendance): ResponseEntity<Int> {
    return ResponseEntity.ok(staffAttendanceService.staffUpdate(staffAttendance))
  }

  @PutMapping("/check_in/2")
  fun staffCheckInTwo(@RequestBody staffAttendance: StaffAttendance): ResponseEntity<Int> {
    return ResponseEntity.ok(staffAttendanceService.staffUpdate(staffAttendance))
  }

  @PutMapping("/check_out/2")
  fun staffCheckOutTwo(@RequestBody staffAttendance: StaffAttendance): ResponseEntity<Int> {
    return ResponseEntity.ok(staffAttendanceService.staffUpdate(staffAttendance))
  }

  @PutMapping("/check_in/3")
  fun staffCheckInThree(@RequestBody staffAttendance: StaffAttendance): ResponseEntity<Int> {
    return ResponseEntity.ok(staffAttendanceService.staffUpdate(staffAttendance))
  }

  @PutMapping("/check_out/3")
  fun staffCheckOutThree(@RequestBody staffAttendance: StaffAttendance): ResponseEntity<Int> {
    return ResponseEntity.ok(staffAttendanceService.staffUpdate(staffAttendance))
  }
}