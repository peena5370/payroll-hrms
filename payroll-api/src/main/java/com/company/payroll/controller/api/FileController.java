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
import com.company.payroll.service.AccountService;
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
@RequestMapping("/api/files")
public class FileController {
	
	@Autowired
    private FileUtils fileUtils;
    
	@Autowired
    private JwtTokenUtils jwtTokenUtils;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private FileAttachmentService fileAttachmentService;
    
	@Operation(summary="Upload profile image")
    @PostMapping("/profile/image/upload")
    public ResponseEntity<Integer> uploadImage(@Parameter(description="Employee id") @RequestParam("file") MultipartFile file, HttpServletRequest request) {
    	Integer status = 0;
    	String header = request.getHeader("Authorization");
    	String token = header.substring(7);
    	Claims claims = jwtTokenUtils.getClaims(token);
    	
    	LocalDate date = LocalDate.now();
    	String imgpath = "/images/" + claims.getSubject()+ "/" + date.getYear() 
    				   + "/" + date.getMonthValue() + "/" + date.getDayOfMonth();
    	String contentType = file.getContentType();
    	
    	if(contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/gif")) {
    		String uploadPath = fileUtils.imageUpload(file, imgpath);
    		if(!uploadPath.equals("")) {
    			Account account = new Account();
    			account.setUsername(claims.getSubject());
    			account.setImgPath(uploadPath);
    			account.setDateModified(LocalDateTime.now());
    			
    			status = accountService.updateImagePath(account);
    			if(status==0) {
    				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
    			}
    		}
    	} else {
    		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(status);
    	}
    	
    	return ResponseEntity.ok(status);
    }

    @Operation(summary="Load profile image")
    @GetMapping("/profile/image/loads")
    public ResponseEntity<Resource> loadImage(HttpServletRequest request) {
    	String header = request.getHeader("Authorization");
    	String token = header.substring(7);
    	Claims claims = jwtTokenUtils.getClaims(token);
    	Account account = accountService.getByUsername(claims.get("username").toString());
    	
        Resource resource = fileUtils.download(Paths.get(account.getImgPath()));

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
        	log.info("Could not determine file type. Exception message: {}", e);
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
    }
    
    @Operation(summary="Get employee document list")
	@GetMapping("/documents/employee/{id}/list")
	public ResponseEntity<List<FileAttachment>> getEmployeeList(@Parameter(description="Employee id") @PathVariable("id") int id) {
		return ResponseEntity.ok(fileAttachmentService.getListByEId(id));
	}
	
    @Operation(summary="Get manager document list")
	@GetMapping("/documents/manager/{id}/list")
	public ResponseEntity<List<FileAttachment>> getManagerList(@Parameter(description="Manager id") @PathVariable("id") int id) {
		return ResponseEntity.ok(fileAttachmentService.getListByMId(id));
	}

    @Operation(summary="Upload multiple employee documents")
	@PostMapping("/documents/upload/employee/{id}")
	public ResponseEntity<String> uploadEmployeeFiles(@Parameter(description="Files") @RequestParam("files") MultipartFile[] files, 
													  @Parameter(description="Employee id") @PathVariable("id") int id) {
		String filepath = "/files/employees/" + String.valueOf(id);

		FileAttachment obj = new FileAttachment();
      
		List<MultipartFile> fileList = Arrays.asList(files).stream().toList();
	  
		for(MultipartFile file : fileList) {
			if(file.getContentType().equals("application/msword") || file.getContentType().equals("application/pdf")) {
				String path = fileUtils.fileUpload(file, filepath);
			  
				obj.setFileName(file.getOriginalFilename());
				obj.setFileSize(file.getSize());
				obj.setAttachmentPath(path);
				obj.setEId(id);
			  
				fileAttachmentService.insert(obj);
			} else {
				return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Media type not supported.");
			}
		}
      
		return ResponseEntity.ok("Success");
	}
  
    @Operation(summary="Upload multiple manager documents")
	@PostMapping("/documents/upload/manager/{id}")
	public ResponseEntity<String> uploadManagerFiles(@Parameter(description="Files") @RequestParam("files") MultipartFile[] files, 
													 @Parameter(description="Manager id") @PathVariable("id") int id) {
		String filepath = "/files/manager/" + String.valueOf(id);
	  
		FileAttachment obj = new FileAttachment();
      
     	List<MultipartFile> fileList = Arrays.asList(files).stream().toList();
	  
     	for(MultipartFile file : fileList) {
     		if(file.getContentType().equals("application/msword") || file.getContentType().equals("application/pdf")) {
				String path = fileUtils.fileUpload(file, filepath);
			  
				obj.setFileName(file.getOriginalFilename());
				obj.setFileSize(file.getSize());
				obj.setAttachmentPath(path);
				obj.setMId(id);
			  
				fileAttachmentService.insert(obj);
     		} else {
				return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Media type not supported.");
			}
     	}
      
     	return ResponseEntity.ok("Success");
	}
	
    @Operation(summary="Download documents based on file id")
	@GetMapping("/documents/download/{id}")
	public ResponseEntity<Resource> downloadFile(@Parameter(description="File id") @PathVariable("id") int id, HttpServletRequest request) {
		FileAttachment obj = fileAttachmentService.getByPrimaryKey(id);
		
		Resource resource = fileUtils.download(Paths.get(obj.getAttachmentPath()));
		
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
	
    @Operation(summary="Download documents based on file id")
	@DeleteMapping("/documents/delete/{id}")
	public ResponseEntity<String> deleteFile(@Parameter(description="File id") @PathVariable("id") int id) {
		FileAttachment obj = fileAttachmentService.getByPrimaryKey(id);
		Integer status = fileAttachmentService.delete(obj.getFId());
		
		if(status ==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Path delete failed.");
		} else {
			boolean bool = fileUtils.delete(Paths.get(obj.getAttachmentPath()));
			
			if(!bool) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File delete failed.");
			}
		}

		return ResponseEntity.ok("Success");
	}
}
