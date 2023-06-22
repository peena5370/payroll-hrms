package com.company.payroll.service

import com.company.payroll.model.FileAttachment

interface FileAttachmentService {
  fun getListByStaffId(staffId: Int): List<FileAttachment>?

  fun getByPrimaryKey(fId: Int): FileAttachment?

  fun insert(row: FileAttachment): Int

  fun update(row: FileAttachment): Int

  fun delete(fId: Int): Int
}