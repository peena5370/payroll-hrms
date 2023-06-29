package com.company.payroll.service

import com.company.payroll.model.FileAttachment
import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile

interface FileAttachmentService {
  fun listFilesByStaffId(staffId: Int): List<FileAttachment>

  fun findById(fId: Int): FileAttachment

  fun uploadFiles(files: Array<MultipartFile>, staffId: Int): Int

  fun downloadFile(fId: Int): Resource?

  fun delete(fId: Int): Int
}