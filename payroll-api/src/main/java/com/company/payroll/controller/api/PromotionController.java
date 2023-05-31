package com.company.payroll.controller.api;

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

import com.company.payroll.model.Promotion;
import com.company.payroll.service.StaffMiscellaneousService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/promotion")
public class PromotionController {
	
	@Autowired
	private StaffMiscellaneousService staffMiscellaneousService;

	@Operation(summary="Get promotion list")
	@GetMapping
	public ResponseEntity<PageInfo<Promotion>> listPromotion(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffMiscellaneousService.listPromotion(page, offset));
	}
	
	@Operation(summary="Get promotion info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Promotion>> getById(@PathVariable("id") int pid) {
		return ResponseEntity.ok(staffMiscellaneousService.findPromotionById(pid));
	}
	
	@Operation(summary="Insert promotion info")
	@PostMapping
	public ResponseEntity<Promotion> insert(@RequestBody Promotion promotion) {
		return ResponseEntity.ok(staffMiscellaneousService.insertPromotion(promotion));
	}
	
	@Operation(summary="Update promotion info.")
	@PutMapping("/{id}")
	public ResponseEntity<Promotion> update(@RequestBody Promotion promotion) {
		return ResponseEntity.ok(staffMiscellaneousService.updatePromotion(promotion));
	}
	
	@Operation(summary="Delete promotion info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int pid) {
		return ResponseEntity.ok(staffMiscellaneousService.deletePromotion(pid));
	}
}
