package com.company.payroll.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileUtils {
	private final Path storageLocation;
	
	public FileUtils(@Value("${file.upload.directory}") String directory) {
		this.storageLocation = Paths.get(directory).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.storageLocation);
		} catch(Exception e) {
			log.warn("Failed to create file directory. Error message: {}", e);
		}
	}
	
	/**
	 * 
	 * Modified at 30 Apr 2023
	 * <p> Change from upload(MultipartFile file, String path, int id) to imageUpload(MultipartFile file, String impPath)
	 * 
	 * @param file
	 * @param imgPath
	 * @return
	 */
	public String imageUpload(MultipartFile file, String imgPath) {
		String uploadPath = "";
		
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        
        Matcher regex = Pattern.compile("(\\w*)(\\.)(jpg|jpeg|png|gif)").matcher(filename);
		if(!regex.matches()) {
			uploadPath = "";
		} else {
	        String randomName = UUID.randomUUID() + filename.substring((filename.lastIndexOf(".")));
	        
	        try {
	        	Path storagePath = Path.of(String.valueOf(this.storageLocation), imgPath);
	        	Files.createDirectories(storagePath);
	        	
	            Path dest = storagePath.resolve(randomName);
	            Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);
	            
	            uploadPath = dest.toString();
	        } catch (IOException e) {
	        	log.warn("Could not store file to directory. Error message: {}", e);
	        }
		}

        return uploadPath;
	}
	
	/**
	 * 
	 * Modified at 30 Apr 2022
	 * <p> Change from download(String filename, Path path) to imageDownload(Path imgPath)
	 * 
	 * @param imgPath
	 * @return Resource
	 */
	public Resource imageDownload(Path imgPath) {
		Resource resource = null;
		try {
	        Resource urlResource = new UrlResource(imgPath.toUri());
	        if(urlResource.exists()) {
	        	resource = urlResource;
	        }
		} catch (MalformedURLException e) {
			log.info("File not found in directory. Error message: {}", e);
		}
		
		return resource;
	}
}
