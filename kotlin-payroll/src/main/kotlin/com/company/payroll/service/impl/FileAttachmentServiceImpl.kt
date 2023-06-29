package com.company.payroll.service.impl

import com.company.payroll.mapper.FileAttachmentMapper
import com.company.payroll.model.FileAttachment
import com.company.payroll.service.FileAttachmentService
import com.company.payroll.util.FileUtils
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Paths

@Service
class FileAttachmentServiceImpl(@Autowired private val fileAttachmentMapper: FileAttachmentMapper,
                                @Autowired private val fileUtils: FileUtils): FileAttachmentService {
  override fun listFilesByStaffId(staffId: Int): List<FileAttachment> {
    return fileAttachmentMapper.selectListByStaffId(staffId)
  }

  override fun findById(fId: Int): FileAttachment {
    return fileAttachmentMapper.selectByPrimaryKey(fId)
  }

  override fun uploadFiles(files: Array<MultipartFile>, staffId: Int): Int {
    val filePath = "/attachments/$staffId"
    var row = 0
    val list: MutableList<Array<MultipartFile>> = listOf(files).stream().toList()
      for (arr in list) {
        for (file in arr) {
          if (file.contentType == "application/msword" || file.contentType == "application/pdf"
              || file.contentType == "application/wps-office.doc" || file.contentType == "application/wps-office.docx") {
            val fileDetails = FileAttachment(null, file.originalFilename.toString(), file.size, fileUtils.fileUpload(file, filePath), staffId)
            row += fileAttachmentMapper.insertSelective(fileDetails)
          } else {
            throw InvalidFileNameException(file.name, "Invalid file format.")
          }
        }
      }
    return row
  }

  override fun downloadFile(fId: Int): Resource? {
    val fileDetails = fileAttachmentMapper.selectByPrimaryKey(fId)

    return fileUtils.download(Paths.get(fileDetails.filePath))
  }

  override fun delete(fId: Int): Int {
    var row = 0
    val fileDetails = fileAttachmentMapper.selectByPrimaryKey(fId)
    val bool = fileUtils.delete(Paths.get(fileDetails.filePath))

    if(bool) {
      row = 1
    }
    return row
  }
}