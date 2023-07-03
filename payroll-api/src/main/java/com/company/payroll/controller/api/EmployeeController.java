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
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private StaffDetailsService staffDetailsService;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Operation(summary="Get employee list")
	@GetMapping
	public ResponseEntity<PageInfo<StaffDetails>> listEmployee(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffDetailsService.listEmployee(page, offset));
	}
	
	@Operation(summary="Get employee info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<StaffDetails>> getById(@PathVariable("id") int eid) {
		return ResponseEntity.ok(staffDetailsService.findEmployeeById(eid));
	}
	
	@Operation(summary="Load employee image")
	@PostMapping("/{id}/image")
	public ResponseEntity<Resource> loadImage(@PathVariable("id") int eid, HttpServletRequest request) {
		Optional<StaffDetails> employee = staffDetailsService.findEmployeeById(eid);
		Resource resource = fileUtils.download(Paths.get(employee.get().getImgUser()));
		
		String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
        	log.info("Could not determine file type. Exception message: {}", e);
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
		
	}

	@Operation(summary="Insert staffDetails info")
	@PostMapping
	public ResponseEntity<StaffDetails> insert(@Parameter(description="image file") @RequestPart("img") MultipartFile image, @RequestPart("staffDetails") StaffDetails staffDetails) {
		String filepath = "/files/employees/list";
		String contentType = image.getContentType();
		
		if(contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/gif")) {
			staffDetails.setImgUser(fileUtils.imageUpload(image, filepath));
		} else {
			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);
		}
		
		return ResponseEntity.ok(staffDetailsService.registerEmployee(staffDetails));
	}
	
	@Operation(summary="Update staffDetails info.")
	@PutMapping("/{id}")
	public ResponseEntity<StaffDetails> update(@RequestBody StaffDetails staffDetails) {
		return ResponseEntity.ok(staffDetailsService.updateEmployee(staffDetails));
	}
	
	@Operation(summary="Delete employee info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int eid) {
		return ResponseEntity.ok(staffDetailsService.deleteEmployee(eid));
	}
	
	@Operation(summary="Get active employee count.")
	@GetMapping("/count/{deptno}/active")
	public ResponseEntity<Integer> count(@PathVariable("deptno") int deptno) {
		return ResponseEntity.ok(staffDetailsService.countActiveEmployee(deptno));
	}
}