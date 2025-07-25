package com.company.payroll.service;

import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.company.payroll.model.HmsFileAttachment;
import com.github.pagehelper.PageInfo;

public interface HmsFileService {
	
	PageInfo<HmsFileAttachment> getAllFileByStaffId(Long staffId, int page, int offset);

	Optional<HmsFileAttachment> getFileById(Long id);
	
	Integer insertFiles(MultipartFile[] files, Long staffId);
	
	Optional<Resource> downloadFileById(long id);
	
	Integer deleteFileById(Long id);
}
