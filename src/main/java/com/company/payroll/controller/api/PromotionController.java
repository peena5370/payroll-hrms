package com.company.payroll.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.Promotion;
import com.company.payroll.service.PromotionService;

@RestController
@RequestMapping("/promotion")
public class PromotionController {
	
	private PromotionService promotionService;
	
	public PromotionController(PromotionService promotionService) {
		this.promotionService = promotionService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Promotion>> listPromotion() {
		return ResponseEntity.ok(promotionService.getList());
	}
	
	@GetMapping("/list/information/{id}")
	public ResponseEntity<Promotion> getPromotionById(@PathVariable("id")int pid) {
		return ResponseEntity.ok(promotionService.getById(pid));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Integer> insertPromotion(@RequestBody Promotion promotion) {
		Integer status = promotionService.insert(promotion);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> updatePromotion(@RequestBody Promotion promotion) {
		Integer status = promotionService.update(promotion);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> delete(@PathVariable("id")int pid) {
		Integer status = promotionService.delete(pid);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
}
