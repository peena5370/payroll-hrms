package com.company.payroll.controller.api;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.payroll.model.Account;
import com.company.payroll.model.FileAttachment;
import com.company.payroll.service.AccountService;
import com.company.payroll.service.FileAttachmentService;
import com.company.payroll.utils.FileUtils;
import com.company.payroll.utils.JwtTokenUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/files")
public class FileController {
    private FileUtils fileUtils;
    
    private JwtTokenUtils jwtTokenUtils;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private FileAttachmentService fileAttachmentService;
	
	public FileController(FileUtils fileUtils, JwtTokenUtils jwtTokenUtils) {
		this.fileUtils = fileUtils;
		this.jwtTokenUtils = jwtTokenUtils;
	}
    
    @PostMapping("/upload/image")
    public ResponseEntity<Integer> uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
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

    @GetMapping("/download/image")
    public ResponseEntity<Resource> downloadImage(HttpServletRequest request) {
    	String header = request.getHeader("Authorization");
    	String token = header.substring(7);
    	Claims claims = jwtTokenUtils.getClaims(token);
    	Account account = accountService.getByUsername(claims.get("username").toString());
    	
        Resource resource = fileUtils.download(Paths.get(account.getImgPath()));

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
        	log.info("Could not determine file type. Error message: {}", e);
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);
        }
        
//      return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//      .body(resource);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
    }

	@PostMapping(value = "/upload/documents/employee/{id}")
	public ResponseEntity<Integer> uploadEmployeeFiles(@RequestPart("files") MultipartFile[] files, @PathVariable("id") int id) {
		String filepath = "/files/employees/" + String.valueOf(id);

		FileAttachment obj = new FileAttachment();
      
		List<MultipartFile> fileList = Arrays.asList(files).stream().toList();
	  
		for(MultipartFile file : fileList) {
			String path = fileUtils.fileUpload(file, filepath);
		  
			obj.setFileName(file.getOriginalFilename());
			obj.setFileSize(file.getSize());
			obj.setAttachmentPath(path);
			obj.setEId(id);
		  
			fileAttachmentService.insert(obj);
		}
      
		return ResponseEntity.ok(1);
	}
  
	@PostMapping("/upload/documents/manager/{id}")
	public ResponseEntity<Integer> uploadManagerFiles(@RequestParam("files") MultipartFile[] files, @PathVariable("id") int id) {
		String filepath = "/files/manager/" + String.valueOf(id);
	  
		FileAttachment obj = new FileAttachment();
      
     	List<MultipartFile> fileList = Arrays.asList(files).stream().toList();
	  
     	for(MultipartFile file : fileList) {
			String path = fileUtils.fileUpload(file, filepath);
		  
			obj.setFileName(file.getOriginalFilename());
			obj.setFileSize(file.getSize());
			obj.setAttachmentPath(path);
			obj.setMId(id);
		  
			fileAttachmentService.insert(obj);
     	}
      
     	return ResponseEntity.ok(1);
	}
	
	@DeleteMapping("/documents/delete/{id}")
	public ResponseEntity<Integer> deleteFile(@PathVariable("id") int id) {
		FileAttachment obj = fileAttachmentService.getByPrimaryKey(id);
		Integer status = fileAttachmentService.delete(obj.getFId());
		
		if(status ==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(0);
		} else {
			boolean bool = fileUtils.delete(Paths.get(obj.getAttachmentPath()));
			
			if(!bool) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
			}
		}

		return ResponseEntity.ok(1);
	}
}
