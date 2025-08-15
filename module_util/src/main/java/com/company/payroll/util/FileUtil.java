package com.company.payroll.util;

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

@Slf4j
@Component
public class FileUtil {
	private final Path storageLocation;
	
	public FileUtil(@Value("${file.upload.directory}") String directory) {
		this.storageLocation = Paths.get(directory).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.storageLocation);
		} catch(Exception e) {
			log.error("Failed to create file directory. Error message: {}", e.getMessage());
        	throw new RuntimeException();
		}
	}
	
	/**
	 * Image upload utility. Accepted image file format in (filename).(jpg/jpeg/png/gif)
	 * Modified at 30 Apr 2023
	 * <p> Change from upload(MultipartFile file, String path, int id) to imageUpload(MultipartFile file, String impPath)
	 * 
	 * @param image
	 * @param path
	 * @return String upload path
	 */
	public String uploadImage(MultipartFile image, String path) {
		String uploadPath = null;
		
        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        
        Matcher regex = Pattern.compile("(\\w*)(\\.)(jpg|jpeg|png|gif)", Pattern.CASE_INSENSITIVE).matcher(filename.replaceAll(" ", ""));
		if(!regex.matches()) {
			return uploadPath;
		} else {
	        String randomName = UUID.randomUUID() + filename.substring((filename.lastIndexOf(".")));
	        
	        try {
	        	Path storagePath = Path.of(String.valueOf(this.storageLocation), path);
	        	Files.createDirectories(storagePath);
	        	
	            Path dest = storagePath.resolve(randomName);
	            Files.copy(image.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);
	            
	            uploadPath = dest.toString();
	        } catch (IOException e) {
	        	log.warn("Could not store file to directory. Error message: {}", e);
	        	throw new RuntimeException();
	        }
		}

        return uploadPath;
	}
	
	/**
	 * File upload utility, accepted file format in: doc, docs, and pdf.
	 * @param file
	 * @param path
	 * @return String upload path
	 */
	public String uploadFile(MultipartFile file, String path) {
		String uploadPath = null;
		
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        
        Matcher regex = Pattern.compile("(\\w*\\p{P}.*)|([0-9])|(\\w*)(\\.)(doc|docx|pdf)", Pattern.CASE_INSENSITIVE).matcher(filename);
		if(!regex.matches()) {
			return uploadPath;
		} else {   
	        try {
	        	Path storagePath = Path.of(String.valueOf(this.storageLocation), path);
	        	Files.createDirectories(storagePath);
	        	
	            Path dest = storagePath.resolve(filename);
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
	 * <p> Change from download(String filename, Path path) to download(Path path)
	 * 
	 * @param path
	 * @return Resource
	 */
	public Resource downloadFile(Path path) {
		Resource resource = null;
		try {
	        Resource urlResource = new UrlResource(path.toUri());
	        if(urlResource.exists()) {
	        	resource = urlResource;
	        }
		} catch (MalformedURLException e) {
			log.info("File not found in directory. Error message: {}", e);
		}
		
		return resource;
	}
	
	/**
	 * File delete utility
	 * @param path
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean deleteFile(Path path) {
		boolean bool = false;
		try {
			bool = Files.deleteIfExists(path);
		} catch (IOException e) {
			log.info("Delete file fail. Error message: {}", e);
		}
		
		return bool;
	}
}