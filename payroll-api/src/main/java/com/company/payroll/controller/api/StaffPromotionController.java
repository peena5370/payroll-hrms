package com.company.payroll.controller.api;

import java.util.Optional;

import com.company.payroll.model.StaffPromotion;
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

@RestController
@RequestMapping("/api/staff/promotion")
public class StaffPromotionController {
	
	@Autowired
	private StaffMiscellaneousService staffMiscellaneousService;

	@Operation(summary="Get promotion list")
	@GetMapping
	public ResponseEntity<PageInfo<StaffPromotion>> listPromotion(@RequestParam(value="page", required=true) int page,
																  @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffMiscellaneousService.listPromotion(page, offset));
	}
	
	@Operation(summary="Get promotion list by eid")
	@GetMapping("/{id}/all")
	public ResponseEntity<PageInfo<StaffPromotion>> listByEId(@RequestParam(value="page", required=true) int page,
															  @RequestParam(value="size", required=true) int offset,
															  @PathVariable("id") Integer staffId) {
		return ResponseEntity.ok(staffMiscellaneousService.listPromotionByStaffId(page, offset, staffId));
	}
	
	@Operation(summary="Get promotion info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<StaffPromotion>> getById(@PathVariable("id") Integer pId) {
		return ResponseEntity.ok(staffMiscellaneousService.findPromotionById(pId));
	}
	
	@Operation(summary="Insert staffPromotion info")
	@PostMapping
	public ResponseEntity<StaffPromotion> insert(@RequestBody StaffPromotion staffPromotion) {
		return ResponseEntity.ok(staffMiscellaneousService.insertPromotion(staffPromotion));
	}
	
	@Operation(summary="Update staffPromotion info.")
	@PutMapping("/{id}")
	public ResponseEntity<StaffPromotion> update(@RequestBody StaffPromotion staffPromotion) {
		return ResponseEntity.ok(staffMiscellaneousService.updatePromotion(staffPromotion));
	}
	
	@Operation(summary="Delete promotion info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int pid) {
		return ResponseEntity.ok(staffMiscellaneousService.deletePromotion(pid));
	}
}
