package com.company.payroll.service.impl;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.company.payroll.util.FileUtils;
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.FileAttachmentMapper;
import com.company.payroll.model.FileAttachment;
import com.company.payroll.service.FileAttachmentService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileAttachmentServiceImpl implements FileAttachmentService {

	@Autowired
	private FileUtils fileUtils;

	@Autowired
	private FileAttachmentMapper fileAttachmentMapper;

	@Override
	public List<FileAttachment> listFilesByStaffId(Integer staffId) {
		return fileAttachmentMapper.selectListByStaffId(staffId);
	}

	@Override
	public List<FileAttachment> getListByEId(Integer eId) {
		return null;
	}

	@Override
	public List<FileAttachment> getListByMId(Integer mId) {
		return null;
	}

	@Override
	public FileAttachment getByPrimaryKey(int fId) {
		return null;
	}

	@Override
	public FileAttachment findById(Integer fId) {
		return fileAttachmentMapper.selectByPrimaryKey(fId);
	}

	@Override
	public Integer insert(FileAttachment row) {
		return null;
	}

	@Override
	public Integer uploadFiles(MultipartFile[] files, Integer staffId) {
		String filePath = "/attachments/" + staffId.toString();
		int row = 0;
		List<MultipartFile> list = Arrays.stream(files).toList();
		for(MultipartFile file : list) {
			if(Objects.equals(file.getContentType(), "application/msword") || Objects.equals(file.getContentType(), "application/pdf")
					|| Objects.equals(file.getContentType(), "application/wps-office.doc")
					|| Objects.equals(file.getContentType(), "application/wps-office.docx")) {
				FileAttachment fileDetails = new FileAttachment(null, file.getOriginalFilename(), file.getSize(), fileUtils.fileUpload(file, filePath), staffId);
				row += fileAttachmentMapper.insertSelective(fileDetails);
			} else {
				throw new InvalidFileNameException(file.getOriginalFilename(), "Invalid file format");
			}
		}
		return row;
	}

	@Override
	public Integer update(FileAttachment row) {
		return null;
	}

	@Override
	public Resource downloadFile(Integer fId) {
		FileAttachment fileDetails = fileAttachmentMapper.selectByPrimaryKey(fId);

		return fileUtils.download(Paths.get(fileDetails.getFilePath()));
	}

	@Override
	public Integer delete(int fId) {
		int row = 0;
		FileAttachment fileDetails = fileAttachmentMapper.selectByPrimaryKey(fId);
		boolean bool = fileUtils.delete(Paths.get(fileDetails.getFilePath()));
		if(bool) {
			row += fileAttachmentMapper.deleteByPrimaryKey(fId);
		} else {
			return row;
		}
		return row;
	}

}
