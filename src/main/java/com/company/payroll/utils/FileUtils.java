package com.company.payroll.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.company.payroll.exception.FileNotFoundException;
import com.company.payroll.exception.FileStorageException;
import com.company.payroll.model.FileStorageProperties;
import com.company.payroll.model.ProfileImage;

public class FileUtils {
	private final Path fileStorageLocation;
	
	public FileUtils(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		
//		initialize by creating new directories with the file storage location
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch(Exception e) {
			throw new FileStorageException("Cannot create directory", e);
		}
	}
	
	public ProfileImage upload(MultipartFile file, String path, int id) {
		// Normalize file name
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String newName = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));
        
        Path storePath = Path.of(String.valueOf(this.fileStorageLocation), path);

        try {
            // Check if the file's name contains invalid characters
            if(filename.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence: " + filename);
            }
            
            Files.createDirectories(storePath);
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = storePath.resolve(newName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
            return new ProfileImage(filename, newName, targetLocation.toString(), file.getSize(), file.getContentType(), LocalDate.now(), id);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + filename + ". Please try again!", ex);
        }
	}
	
	public Resource download(String fileName, Path path) {
		try {
	        Resource resource = new UrlResource(path.toUri());
	        if(resource.exists()) {
	            return resource;
	        } else {
	            throw new FileNotFoundException("File not found " + fileName);
	        }
		} catch (MalformedURLException ex) {
			throw new FileNotFoundException("File not found " + fileName, ex);
		}
	}
}
