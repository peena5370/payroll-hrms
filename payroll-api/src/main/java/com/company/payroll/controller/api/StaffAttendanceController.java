package com.company.payroll.controller.api;

import com.company.payroll.model.StaffAttendance;
import com.company.payroll.service.StaffAttendanceService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
public class StaffAttendanceController {
    @Autowired
    private StaffAttendanceService staffAttendanceService;

    @GetMapping
    public ResponseEntity<PageInfo<StaffAttendance>> getStaffAttendance(@RequestParam("page") int page, @RequestParam("size") int size, @RequestBody StaffAttendance staffAttendance) {
        return ResponseEntity.ok(staffAttendanceService.getStaffAttendance(staffAttendance.getStaffId(), staffAttendance.getStartDate(), staffAttendance.getEndDate(), page, size));
    }

    @PostMapping("/check_in/1")
    public ResponseEntity<Integer> staffCheckInOne(@RequestBody StaffAttendance staffAttendance) {
        return ResponseEntity.ok(staffAttendanceService.staffCheckIn(staffAttendance));
    }

    @PutMapping("/check_out/1")
    public ResponseEntity<Integer> staffCheckOutOne(@RequestBody StaffAttendance staffAttendance) {
        return ResponseEntity.ok(staffAttendanceService.attendanceUpdate(staffAttendance));
    }

    @PutMapping("/check_in/2")
    public ResponseEntity<Integer> staffCheckInTwo(@RequestBody StaffAttendance staffAttendance) {
        return ResponseEntity.ok(staffAttendanceService.attendanceUpdate(staffAttendance));
    }

    @PutMapping("/check_out/2")
    public ResponseEntity<Integer> staffCheckOutTwo(@RequestBody StaffAttendance staffAttendance) {
        return ResponseEntity.ok(staffAttendanceService.attendanceUpdate(staffAttendance));
    }

    @PutMapping("/check_in/3")
    public ResponseEntity<Integer> staffCheckInThree(@RequestBody StaffAttendance staffAttendance) {
        return ResponseEntity.ok(staffAttendanceService.attendanceUpdate(staffAttendance));
    }

    @PutMapping("/check_out/3")
    public ResponseEntity<Integer> staffCheckOutThree(@RequestBody StaffAttendance staffAttendance) {
        return ResponseEntity.ok(staffAttendanceService.attendanceUpdate(staffAttendance));
    }
}
