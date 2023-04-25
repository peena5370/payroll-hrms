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

import com.company.payroll.model.Resignation;
import com.company.payroll.service.ResignationService;

@RestController
@RequestMapping("/resign")
public class ResignationController {
	
	private ResignationService resignationService;
	
	public ResignationController(ResignationService resignationService) {
		this.resignationService = resignationService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Resignation>> listResignation() {
		return ResponseEntity.ok(resignationService.getList());
	}
	
	@GetMapping("/list/information/{id}")
	public ResponseEntity<Resignation> getById(@PathVariable("id")int id) {
		return ResponseEntity.ok(resignationService.getById(id));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Integer> insert(@RequestBody Resignation resignation) {
		Integer status = resignationService.insert(resignation);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> update(@RequestBody Resignation resignation) {
		Integer status = resignationService.update(resignation);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> delete(@PathVariable("id")int id) {
		Integer status = resignationService.delete(id);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
//	@PutMapping("/list/information/{id}/update/status")
//	public Integer updateResignStatus(@PathVariable("id")int id, @RequestBody Resignation resign) {
//		int resignStatus = resign.getResignStatus();
//		Integer mid = resign.getMId();
//		
//		Resignation rs = new Resignation(id, resignStatus, mid);
//		
//		return resignationService.updateResignStatus(rs);
//	}
}
