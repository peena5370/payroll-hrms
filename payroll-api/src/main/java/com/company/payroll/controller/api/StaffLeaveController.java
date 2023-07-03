package com.company.payroll.controller.api;

import java.util.List;
import java.util.Optional;

import com.company.payroll.model.StaffLeave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.service.StaffApplicationService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/application/leave")
public class StaffLeaveController {
	
	@Autowired
	private StaffApplicationService staffApplicationService;

	@Operation(summary="Get leave list")
	@GetMapping
	public ResponseEntity<PageInfo<StaffLeave>> list(@RequestParam(value="page", required=true) int page,
													 @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffApplicationService.listLeave(page, offset));
	}
	
	@Operation(summary="Get leave list by employee id")
	@GetMapping("/{id}/all")
	public ResponseEntity<Optional<List<StaffLeave>>> listByStaffId(@Parameter(description="employee id") @PathVariable("id") Integer staffId) {
		return ResponseEntity.ok(staffApplicationService.findLeaveByStaffId(staffId));
	}
	
	@Operation(summary="Get leave info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<StaffLeave>> findById(@PathVariable("id") Integer lId) {
		return ResponseEntity.ok(staffApplicationService.findLeaveById(lId));
	}
	
	@Operation(summary="Add staffLeave info")
	@PostMapping
	public ResponseEntity<StaffLeave> insert(@RequestBody StaffLeave staffLeave) {
		return ResponseEntity.ok(staffApplicationService.insertLeave(staffLeave));
	}
	
	@Operation(summary="Update staffLeave info.")
	@PutMapping("/{id}")
	public ResponseEntity<StaffLeave> update(@RequestBody StaffLeave staffLeave) {
		return ResponseEntity.ok(staffApplicationService.updateLeave(staffLeave));
	}
	
	@Operation(summary="Delete leave info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") Integer lId) {
		return ResponseEntity.ok(staffApplicationService.deleteLeave(lId));
	}
}
