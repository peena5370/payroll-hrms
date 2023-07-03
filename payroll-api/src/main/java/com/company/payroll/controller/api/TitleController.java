package com.company.payroll.controller.api;

import java.util.Optional;

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

import com.company.payroll.model.Title;
import com.company.payroll.service.CompanyInfoService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/system/title")
public class TitleController {
	
	@Autowired
	private CompanyInfoService companyInfoService;

	@Operation(summary="Get title list")
	@GetMapping
	public ResponseEntity<PageInfo<Title>> list(@RequestParam(value="page", required=true) int page,
												@RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(companyInfoService.listTitle(page, offset));
	}

	@Operation(summary="Get title info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Title>> findById(@PathVariable("id") Integer titleNo) {
		return ResponseEntity.ok(companyInfoService.findTitleById(titleNo));
	}
	
	@Operation(summary="Insert title info")
	@PostMapping
	public ResponseEntity<Title> insert(@RequestBody Title title) {
		return ResponseEntity.status(HttpStatus.CREATED).body(companyInfoService.insertTitle(title));
	}
	
	@Operation(summary="Update title info.")
	@PutMapping("/{id}")
	public ResponseEntity<Title> update(@RequestBody Title title) {
		return ResponseEntity.ok(companyInfoService.updateTitle(title));
	}
	
	@Operation(summary="Delete title info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") Integer titleNo) {
		return ResponseEntity.ok(companyInfoService.deleteTitle(titleNo));
	}
}