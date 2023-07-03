package com.company.payroll.controller.api;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

import com.company.payroll.model.StaffDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.payroll.service.StaffDetailsService;
import com.company.payroll.util.FileUtils;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/staff/leave")
public class StaffDetailsController {
	
	@Autowired
	private StaffDetailsService staffDetailsService;
	
	@Operation(summary="Get employee list")
	@GetMapping
	public ResponseEntity<PageInfo<StaffDetails>> listEmployee(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffDetailsService.listStaffDetails(page, offset));
	}
	
	@Operation(summary="Get employee info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<StaffDetails>> findById(@PathVariable("id") Integer staffId) {
		return ResponseEntity.ok(staffDetailsService.findByStaffId(staffId));
	}
	
	@Operation(summary="Load employee image")
	@PostMapping("/{staff_id}/image/loads")
	public ResponseEntity<Resource> loadStaffImage(@PathVariable("staff_id") Integer staffId, HttpServletRequest request) {
		Resource resource = staffDetailsService.loadStaffImage(staffId);
		String contentType = "";
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
        	log.info("Could not determine file type. Exception message: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
		
	}

	@Operation(summary="Insert staffDetails info")
	@PostMapping
	public ResponseEntity<StaffDetails> insert(@Parameter(description="image file") @RequestPart("img") MultipartFile image, @RequestPart("employee") StaffDetails staffDetails) {
		return ResponseEntity.ok(staffDetailsService.addStaffDetails(image, staffDetails));
	}
	
	@Operation(summary="Update staffDetails info.")
	@PutMapping("/{id}")
	public ResponseEntity<StaffDetails> update(@RequestBody StaffDetails staffDetails) {
		return ResponseEntity.ok(staffDetailsService.updateStaffDetails(staffDetails));
	}
	
	@Operation(summary="Delete employee info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") Integer staffId) {
		return ResponseEntity.ok(staffDetailsService.deleteStaffDetails(staffId));
	}
	
	@Operation(summary="Get active employee count.")
	@GetMapping("/count/{dept_no}/active")
	public ResponseEntity<Integer> count(@PathVariable("dept_no") Integer deptNo) {
		return ResponseEntity.ok(staffDetailsService.countActiveStaff(deptNo));
	}
}