package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.FileAttachment;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileAttachmentService {
	/**
	 *
	 * <p>Method of listing all available file list that has been uploaded to back end server.</p>
	 *
	 * @param staffId staff id
	 * @return List of FileAttachment
	 */
	List<FileAttachment> listFilesByStaffId(Integer staffId);

	/**
	 * @deprecated Will be removed after FileAttachmentService success implemented.
	 * <p>Please use {@link #listFilesByStaffId(Integer)} instead.</p>
	 * @param eId
	 * @return
	 */
	List<FileAttachment> getListByEId(Integer eId);

	/**
	 * @deprecated Will be removed after FileAttachmentService success implemented.
	 * <p>Please use {@link #listFilesByStaffId(Integer)} instead.</p>
	 * @param mId
	 * @return
	 */
	List<FileAttachment> getListByMId(Integer mId);

	/**
	 * @deprecated Renamed method. Please use {@link #findById(Integer)} instead.
	 * @param fId
	 * @return
	 */
	FileAttachment getByPrimaryKey(int fId);

	/**
	 * Method of getting FileAttachment object by using file id.
	 * @param fId file id
	 * @return {@link FileAttachment} object
	 */
	FileAttachment findById(Integer fId);

	/**
	 * @deprecated Method will be removed after FileAttachmentService success implemented.
	 * <p>Please use {@link #uploadFiles(MultipartFile[], Integer)} instead.</p>
	 * @param fId
	 * @return
	 */
	Integer insert(FileAttachment row);

	/**
	 * Method for upload files based on staff id.
	 * @param files File arrays.
	 * @param staffId staff id
	 * @return Inserted row
	 */
	Integer uploadFiles(MultipartFile[] files, Integer staffId);

	/**
	 * @deprecated Will be removed after FileAttachmentService success implemented.
	 * @param row
	 * @return
	 */
	Integer update(FileAttachment row);

	/**
	 * Method for downloading file from back end server.
	 * @param fId file id
	 * @return Resource of file
	 */
	Resource downloadFile(Integer fId);

	/**
	 * Delete file from back end server.
	 * @param fId file id
	 * @return Deleted row
	 */
	Integer delete(int fId);
	
}
