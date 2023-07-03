package com.company.payroll.controller.api;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.payroll.model.Account;
import com.company.payroll.model.FileAttachment;
import com.company.payroll.service.SystemAccountService;
import com.company.payroll.service.FileAttachmentService;
import com.company.payroll.util.FileUtils;
import com.company.payroll.util.JwtTokenUtils;

import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/staff/files")
public class FileAttachmentController {
	
	@Autowired
	private FileAttachmentService fileAttachmentService;

	@Operation(summary="Get employee document list")
	@GetMapping("/{id}/all")
	public ResponseEntity<List<FileAttachment>> listByStaffId(@Parameter(description="StaffDetails id") @PathVariable("id") Integer staffId) {
		return ResponseEntity.ok(fileAttachmentService.listFilesByStaffId(staffId));
	}

    @Operation(summary="Upload multiple employee documents")
	@PostMapping("/{id}/upload/multiple")
	public ResponseEntity<Integer> uploadMultipleFiles(@Parameter(description="Files") @RequestParam("files") MultipartFile[] files,
													  @Parameter(description="StaffDetails id") @PathVariable("id") Integer staffId) {
		return ResponseEntity.ok(fileAttachmentService.uploadFiles(files, staffId));
	}
	
    @Operation(summary="Download documents based on file id")
	@PostMapping("/{id}")
	public ResponseEntity<Resource> downloadFile(@Parameter(description="File id") @PathVariable("id") Integer fId, HttpServletRequest request) {
		Resource resource = fileAttachmentService.downloadFile(fId);
		String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            log.info("Could not determine file type. Exception message: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);
        }
        
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
    @Operation(summary="Download documents based on file id")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteFile(@Parameter(description="File id") @PathVariable("id") Integer fId) {
		Integer status = fileAttachmentService.delete(fId);
		
		if(status.equals(0)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Path delete failed.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File delete failed.");
		}
	}
}
