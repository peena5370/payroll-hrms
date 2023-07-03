package com.company.payroll.controller.api;

import java.util.List;
import java.util.Optional;

import com.company.payroll.model.StaffTraining;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.company.payroll.service.StaffMiscellaneousService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/staff/training")
public class StaffTrainingController {
	
	@Autowired
	private StaffMiscellaneousService staffMiscellaneousService;
	
	@Operation(summary="Get training list")
	@GetMapping
	public ResponseEntity<PageInfo<StaffTraining>> list(@RequestParam(value="page", required=true) int page,
														@RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffMiscellaneousService.listTraining(page, offset));
	}
	
	@Operation(summary="Get training info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<StaffTraining>> findById(@PathVariable("id") Integer tId) {
		return ResponseEntity.ok(staffMiscellaneousService.findTrainingById(tId));
	}
	
	@Operation(summary="Get training list by employee id")
	@GetMapping("/{staff_id}/all")
	public ResponseEntity<List<StaffTraining>> listByStaffId(@Parameter(description="employee id") @PathVariable("staff_id") Integer staffId) {
		return ResponseEntity.ok(staffMiscellaneousService.listTrainingByStaffId(staffId));
	}
	
	@Operation(summary="Insert staffTraining info")
	@PostMapping
	public ResponseEntity<StaffTraining> insert(@RequestBody StaffTraining staffTraining) {
		return ResponseEntity.ok(staffMiscellaneousService.insertTraining(staffTraining));
	}
	
	@Operation(summary="Update staffTraining info.")
	@PutMapping("/{id}")
	public ResponseEntity<StaffTraining> update(@RequestBody StaffTraining staffTraining) {
		return ResponseEntity.ok(staffMiscellaneousService.updateTraining(staffTraining));
	}

	@Operation(summary="Delete training info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int tId) {		
		return ResponseEntity.ok(staffMiscellaneousService.deleteTraining(tId));
	}	
}
