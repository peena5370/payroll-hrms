package com.company.payroll.controller.api;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

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

import com.company.payroll.model.Manager;
import com.company.payroll.service.StaffDetailsService;
import com.company.payroll.util.FileUtils;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Deprecated
@Slf4j
@RestController
@RequestMapping("/api/manager")
public class ManagerController {
	
	@Autowired
	private StaffDetailsService staffDetailsService;
	
	@Autowired
	private FileUtils fileUtils;
	
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
	
	@Operation(summary="Load manager image")
	@PostMapping("/{id}/image")
	public ResponseEntity<Resource> loadImage(@PathVariable("id") int mid, HttpServletRequest request) {
		Optional<Manager> manager = staffDetailsService.findManagerById(mid);
		Resource resource = fileUtils.download(Paths.get(manager.get().getImgUser()));
		
		String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
        	log.info("Could not determine file type. Exception message: {}", e);
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
		
	}
	
	@Operation(summary="Insert manager info")
	@PostMapping
	public ResponseEntity<Manager> insert(@Parameter(description="image file") @RequestPart("img") MultipartFile image, @RequestPart("manager") Manager manager) {
		String filepath = "/files/managers/list";
		String contentType = image.getContentType();
		
		if(contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/gif")) {
			manager.setImgUser(fileUtils.imageUpload(image, filepath));
		} else {
			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);
		}
		
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

	@Operation(summary="Get active manager count.")
	@GetMapping("/count/{deptno}/active")
	public ResponseEntity<Integer> count(@PathVariable("deptno") int deptno) {
		return ResponseEntity.ok(staffDetailsService.countActiveManager(deptno));
	}
}
