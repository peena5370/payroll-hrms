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

import com.company.payroll.model.Manager;
import com.company.payroll.service.StaffDetailsService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {
	
	@Autowired
	private StaffDetailsService staffDetailsService;
	
	@Operation(summary="Get manager list")
	@GetMapping
	public ResponseEntity<PageInfo<Manager>> listManager(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffDetailsService.listManager(page, offset));
	}
	
	@Operation(summary="Get manager info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Manager>> getById(@PathVariable("id") int mid) {
		return ResponseEntity.ok(staffDetailsService.findManagerById(mid));
	}
	
	@Operation(summary="Insert manager info")
	@PostMapping
	public ResponseEntity<Manager> insert(@RequestBody Manager manager) {
		return ResponseEntity.ok(staffDetailsService.registerManager(manager));
	}
	
	@Operation(summary="Update manager info.")
	@PutMapping("/{id}")
	public ResponseEntity<Manager> update(@RequestBody Manager manager) {
		return ResponseEntity.ok(staffDetailsService.updateManager(manager));
	}

	@Operation(summary="Delete manager info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
		return ResponseEntity.ok(staffDetailsService.deleteManager(id));
	}

//	@GetMapping("/list/count/all")
//	public Integer countManager() {
//		return managerService.countManager();
//	}

//	@GetMapping("/list/count/active")
//	public Integer countAvailableManager() {
//		return managerService.countAvailableManager();
//	}
}
