package com.company.payroll.controller.api;

import com.company.payroll.model.StaffLeaveDetails;
import com.company.payroll.service.StaffLeaveDetailsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff/leave")
public class StaffLeaveDetailsController {

    @Autowired
    private StaffLeaveDetailsService staffLeaveDetailsService;

    @GetMapping
    public ResponseEntity<PageInfo<StaffLeaveDetails>> list(@RequestParam("page") int page, @RequestParam("size") int offset) {
        return ResponseEntity.ok(staffLeaveDetailsService.list(page, offset));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffLeaveDetails> findById(@PathVariable("id") Integer ldId) {
        return ResponseEntity.ok(staffLeaveDetailsService.findById(ldId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffLeaveDetails> update(@RequestBody StaffLeaveDetails staffLeaveDetails) {
        return ResponseEntity.ok(staffLeaveDetailsService.update(staffLeaveDetails));
    }
}
