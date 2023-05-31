package com.company.payroll.controller.api;

import java.util.List;
import java.util.Optional;

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

import com.company.payroll.model.Training;
import com.company.payroll.service.StaffMiscellaneousService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/training")
public class TrainingController {
	
	@Autowired
	private StaffMiscellaneousService staffMiscellaneousService;
	
	@Operation(summary="Get training list")
	@GetMapping
	public ResponseEntity<PageInfo<Training>> listTraining(@RequestParam(value="page", required=true) int page,  @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffMiscellaneousService.listTraining(page, offset));
	}
	
	@Operation(summary="Get training info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Training>> getById(@PathVariable("id") int tId) {
		return ResponseEntity.ok(staffMiscellaneousService.findTrainingById(tId));
	}
	
	@Operation(summary="Get training list by employee id")
	@GetMapping("/{id}/all")
	public ResponseEntity<Optional<List<Training>>> getListByEId(@Parameter(description="employee id") @PathVariable("id") int eId) {
		return ResponseEntity.ok(staffMiscellaneousService.findTrainingByEId(eId));
	}
	
	@Operation(summary="Insert training info")
	@PostMapping
	public ResponseEntity<Training> insert(@RequestBody Training training) {
		return ResponseEntity.ok(staffMiscellaneousService.insertTraining(training));
	}
	
	@Operation(summary="Update training info.")
	@PutMapping("/{id}")
	public ResponseEntity<Training> update(@RequestBody Training training) {
		return ResponseEntity.ok(staffMiscellaneousService.updateTraining(training));
	}

	@Operation(summary="Delete training info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int tId) {		
		return ResponseEntity.ok(staffMiscellaneousService.deleteTraining(tId));
	}	
}
