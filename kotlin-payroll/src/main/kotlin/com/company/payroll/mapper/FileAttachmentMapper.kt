package com.company.payroll.mapper

import com.company.payroll.model.FileAttachment
import org.springframework.stereotype.Repository

@Repository
interface FileAttachmentMapper {
  fun deleteByPrimaryKey(fId: Int): Int

  fun insert(row: FileAttachment): Int

  fun insertSelective(row: FileAttachment): Int

  fun selectByPrimaryKey(fId: Int): FileAttachment

  fun selectListByStaffId(staffId: Int): List<FileAttachment>

  fun updateByPrimaryKeySelective(row: FileAttachment): Int

  fun updateByPrimaryKey(row: FileAttachment): Int
}