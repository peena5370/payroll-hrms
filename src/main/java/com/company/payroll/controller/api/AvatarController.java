package com.company.payroll.controller.api;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.payroll.model.ProfileImage;
import com.company.payroll.service.AvatarService;
import com.company.payroll.utils.FileUtils;

import jakarta.servlet.http.HttpServletRequest;

//@RestController
public class AvatarController {
	@Autowired
    private FileUtils fileUtils;
	
	@Autowired
	private AvatarService avatarService;
    
//    @PostMapping("/{id}/upload/avatar")
    public ProfileImage uploadAvatar(@RequestParam("file") MultipartFile file, @PathVariable("id")int sapid) {
    	ProfileImage image = null;
    	String imgpath = "/" + String.valueOf(sapid) + "/avatar";
    	
    	String contentType = file.getContentType();
    	if(contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/gif")) {
    		ProfileImage returnRow = avatarService.loadAvatar(sapid);
			if(returnRow == null) {
				image = fileUtils.upload(file, imgpath, sapid);	
	    		avatarService.saveAvatar(image);
			} else {
				image = fileUtils.upload(file, imgpath, sapid);	
	    		avatarService.updateAvatar(image);
			}
    	} else {
    		System.out.println("Wrong file content.");
    	}
    	
        return image;
    }

//    @PostMapping("/uploadMultipleFiles")
    public List<ProfileImage> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @PathVariable("id")int sapid) {
        return Arrays.asList(files).stream().map(file -> uploadAvatar(file, sapid)).collect(Collectors.toList());
    }

//    @GetMapping("/{id}/download/avatar")
    public ResponseEntity<Resource> downloadAvatar(@PathVariable int id, HttpServletRequest request) {
        // Load file as Resource
    	ProfileImage image = avatarService.loadAvatar(id);
        Resource resource = fileUtils.download(image.getRealName(), Paths.get(image.getFilePath()));

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            System.out.println("content type: " + contentType + "\tpath: " + resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
