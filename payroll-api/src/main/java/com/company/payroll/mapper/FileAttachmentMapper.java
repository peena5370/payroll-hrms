package com.company.payroll.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.FileAttachment;

@Repository
public interface FileAttachmentMapper {
	int deleteByPrimaryKey(Integer fId);
	int insert(FileAttachment row);
	int insertSelective(FileAttachment row);
	List<FileAttachment> selectListByStaffId(Integer staffId);
	FileAttachment selectByPrimaryKey(Integer fId);
	int updateByPrimaryKeySelective(FileAttachment row);
	int updateByPrimaryKey(FileAttachment row);

	/**
	 * @deprecated will be removed after FileAttachmentMapper has success implemented.
	 * <p>Please use {@link #selectListByStaffId(Integer)} instead.}</p>
	 * @param mId
	 * @return
	 */
	List<FileAttachment> selectListByMId(Integer mId);
}
