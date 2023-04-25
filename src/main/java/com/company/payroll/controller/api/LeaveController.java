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

import com.company.payroll.model.Leave;
import com.company.payroll.service.LeaveService;

@RestController
@RequestMapping("/leave")
public class LeaveController {
	
	private LeaveService leaveService;
	
	public LeaveController(LeaveService leaveService) {
		this.leaveService = leaveService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Leave>> listLeave() {
		return ResponseEntity.ok(leaveService.getList());
	}
	
	@GetMapping("/{id}/list")
	public ResponseEntity<List<Leave>> listByEId(@PathVariable("id")int eid) {
		return ResponseEntity.ok(leaveService.getListByEId(eid));
	}
	
	@GetMapping("/list/information/{id}")
	public ResponseEntity<Leave> getById(@PathVariable("id")int lid) {
		return ResponseEntity.ok(leaveService.getById(lid));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Integer> insert(@RequestBody Leave leave) {
		Integer status = leaveService.insert(leave);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> update(@RequestBody Leave leave) {
		Integer status = leaveService.update(leave);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> delete(@PathVariable("id")int lid) {
		Integer status = leaveService.delete(lid);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
}
