package com.company.payroll.controller.api;

import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.payroll.model.Resignation;
import com.company.payroll.service.StaffMiscellaneousService;
import com.company.payroll.util.FileUtils;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/resign")
public class ResignationController {
	
	@Autowired
	private StaffMiscellaneousService staffMiscellaneousService;
	
	@Autowired
    private FileUtils fileUtils;

	@Operation(summary="Get resignation list")
	@GetMapping
	public ResponseEntity<PageInfo<Resignation>> listResignation(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffMiscellaneousService.listResignation(page, offset));
	}
	
	@Operation(summary="Get resign info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Resignation>> getById(@PathVariable("id") int id) {
		return ResponseEntity.ok(staffMiscellaneousService.findResignationById(id));
	}

	@Operation(summary="Insert resign info")
	@PostMapping
	public ResponseEntity<String> insert(@RequestPart("file") MultipartFile file, @RequestPart("resignation") Resignation resignation) {
		String filepath = "";
		
		if(resignation.getEId()==null) {
			filepath = "/resign_files/" + resignation.getMId();
		} else {
			filepath = "/resign_files/" + resignation.getEId();
		}
		
		if(file.getContentType().equals("application/msword") || file.getContentType().equals("application/pdf") || 
					file.getContentType().equals("application/wps-office.doc") || file.getContentType().equals("application/wps-office.docx")) {
			resignation.setFileName(file.getOriginalFilename());
			resignation.setFileSize(file.getSize());
			resignation.setAttachment(fileUtils.fileUpload(file, filepath));
			
			staffMiscellaneousService.insertResignation(resignation);
		} else {
			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("unsupported media type");
		}
		
		return ResponseEntity.ok("success");
	}
	
	@Operation(summary="Update resign info.")
	@PutMapping("/{id}")
	public ResponseEntity<Resignation> update(@RequestBody Resignation resignation) {	
		return ResponseEntity.ok(staffMiscellaneousService.updateResignation(resignation));
	}
	
	@Operation(summary="Delete resign info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
		Optional<Resignation> resign = staffMiscellaneousService.findResignationById(id);
		fileUtils.delete(Paths.get(resign.get().getAttachment()));
		
		return ResponseEntity.ok(staffMiscellaneousService.deleteResignation(id));
	}
}
