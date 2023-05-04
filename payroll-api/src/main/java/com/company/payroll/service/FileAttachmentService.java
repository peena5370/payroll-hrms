package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.FileAttachment;

public interface FileAttachmentService {

	List<FileAttachment> getListByEId(Integer eId);
	
	List<FileAttachment> getListByMId(Integer mId);
	
	FileAttachment getByPrimaryKey(int fId);
	
	Integer insert(FileAttachment row);
	
	Integer update(FileAttachment row);
	
	Integer delete(int fId);
	
}
