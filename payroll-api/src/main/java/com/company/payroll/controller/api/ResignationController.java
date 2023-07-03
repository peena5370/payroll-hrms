package com.company.payroll.controller.api;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

import com.company.payroll.model.StaffResignation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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

import com.company.payroll.service.StaffMiscellaneousService;
import com.company.payroll.util.FileUtils;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/resign")
public class ResignationController {
	
	@Autowired
	private StaffMiscellaneousService staffMiscellaneousService;
	
	@Autowired
    private FileUtils fileUtils;

	@Operation(summary="Get resignation list")
	@GetMapping
	public ResponseEntity<PageInfo<StaffResignation>> listResignation(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffMiscellaneousService.listResignation(page, offset));
	}
	
	@Operation(summary="Get resign info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<StaffResignation>> getById(@PathVariable("id") int id) {
		return ResponseEntity.ok(staffMiscellaneousService.findResignationById(id));
	}

	@Operation(summary="Insert resign info")
	@PostMapping
	public ResponseEntity<String> insert(@RequestPart("file") MultipartFile file, @RequestPart("staffResignation") StaffResignation staffResignation) {
		String filepath = "";
		
		if(staffResignation.getEId()==null) {
			filepath = "/resign_files/" + staffResignation.getMId();
		} else {
			filepath = "/resign_files/" + staffResignation.getEId();
		}
		
		if(file.getContentType().equals("application/msword") || file.getContentType().equals("application/pdf") || 
					file.getContentType().equals("application/wps-office.doc") || file.getContentType().equals("application/wps-office.docx")) {
			staffResignation.setFileName(file.getOriginalFilename());
			staffResignation.setFileSize(file.getSize());
			staffResignation.setAttachment(fileUtils.fileUpload(file, filepath));
			
			staffMiscellaneousService.insertResignation(staffResignation);
		} else {
			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("unsupported media type");
		}
		
		return ResponseEntity.ok("success");
	}
	
	@Operation(summary="Download attachment")
	@PostMapping("/{id}/attachment/download")
	public ResponseEntity<Resource> downloadAttachment(@PathVariable("id") int id, HttpServletRequest request) {
		Optional<StaffResignation> resign = staffMiscellaneousService.findResignationById(id);
		
		Resource resource = fileUtils.download(Paths.get(resign.get().getAttachment()));
		
		String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            log.info("Could not determine file type. Exception message: {}", e);
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);
        }
        
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@Operation(summary="Update resign info.")
	@PutMapping("/{id}")
	public ResponseEntity<StaffResignation> update(@RequestBody StaffResignation staffResignation) {
		return ResponseEntity.ok(staffMiscellaneousService.updateResignation(staffResignation));
	}
	
	@Operation(summary="Delete resign info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
		Optional<StaffResignation> resign = staffMiscellaneousService.findResignationById(id);
		fileUtils.delete(Paths.get(resign.get().getAttachment()));
		
		return ResponseEntity.ok(staffMiscellaneousService.deleteResignation(id));
	}
}
