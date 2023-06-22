package com.company.payroll.service.impl

import com.company.payroll.model.FileAttachment
import com.company.payroll.service.FileAttachmentService
import org.springframework.stereotype.Service

@Service
class FileAttachmentServiceImpl: FileAttachmentService {
  override fun getListByStaffId(staffId: Int): List<FileAttachment>? {
    TODO("Not yet implemented")
  }

  override fun getByPrimaryKey(fId: Int): FileAttachment? {
    TODO("Not yet implemented")
  }

  override fun insert(row: FileAttachment): Int {
    TODO("Not yet implemented")
  }

  override fun update(row: FileAttachment): Int {
    TODO("Not yet implemented")
  }

  override fun delete(fId: Int): Int {
    TODO("Not yet implemented")
  }
}