package com.company.payroll.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.company.payroll.mapper.HmsFileAttachmentMapper;
import com.company.payroll.model.HmsFileAttachment;
import com.company.payroll.service.HmsFileService;
import com.github.pagehelper.PageInfo;

@Service
public class HmsFileServiceImpl implements HmsFileService {
	
	@Autowired
	private HmsFileAttachmentMapper hmsFileAttachmentMapper;

	@Override
	public PageInfo<HmsFileAttachment> getAllFileByStaffId(Long staffId, int page, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<HmsFileAttachment> getFileById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer insertFiles(MultipartFile[] files, Long staffId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Resource> downloadFileById(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Integer deleteFileById(Long id) {
		return hmsFileAttachmentMapper.deleteByPrimaryKey(id);
	}

}
