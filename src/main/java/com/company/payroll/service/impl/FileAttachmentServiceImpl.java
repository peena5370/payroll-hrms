package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.payroll.mapper.FileAttachmentMapper;
import com.company.payroll.model.FileAttachment;
import com.company.payroll.service.FileAttachmentService;

@Service
public class FileAttachmentServiceImpl implements FileAttachmentService {
	
	private FileAttachmentMapper fileAttachmentMapper;
	
	public FileAttachmentServiceImpl(FileAttachmentMapper fileAttachmentMapper) {
		this.fileAttachmentMapper = fileAttachmentMapper;
	}

	@Override
	public List<FileAttachment> getListByEId(Integer eId) {
		return fileAttachmentMapper.selectListByEId(eId);
	}

	@Override
	public List<FileAttachment> getListByMId(Integer mId) {
		return fileAttachmentMapper.selectListByMId(mId);
	}
	
	@Override
	public FileAttachment getByPrimaryKey(int fId) {
		return fileAttachmentMapper.selectByPrimaryKey(fId);
	}

	@Override
	public Integer insert(FileAttachment row) {
		return fileAttachmentMapper.insertSelective(row);
	}

	@Override
	public Integer update(FileAttachment row) {
		return fileAttachmentMapper.updateByPrimaryKeySelective(row);
	}

	@Override
	public Integer delete(int fId) {
		return fileAttachmentMapper.deleteByPrimaryKey(fId);
	}

}
