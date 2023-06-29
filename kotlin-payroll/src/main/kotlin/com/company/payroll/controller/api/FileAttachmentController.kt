package com.company.payroll.controller.api

import com.company.payroll.model.FileAttachment
import com.company.payroll.service.FileAttachmentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.*

@RestController
@RequestMapping("/api/staff/files")
class FileAttachmentController(@Autowired private val fileAttachmentService: FileAttachmentService) {

  private val log = KotlinLogging.logger {}

  @Operation(summary = "Get employee document list")
  @GetMapping("/{id}/all")
  fun listByStaffId(@Parameter(description = "Employee id") @PathVariable("id") staffId: Int): ResponseEntity<List<FileAttachment>> {
    return ResponseEntity.ok(fileAttachmentService.listFilesByStaffId(staffId))
  }

  @Operation(summary = "Upload multiple employee documents")
  @PostMapping("/{id}/upload/multiple")
  fun uploadMultipleFiles(@Parameter(description = "Files") @RequestParam("files") files: Array<MultipartFile>,
                          @Parameter(description = "Employee id") @PathVariable("id") staffId: Int): ResponseEntity<Int> {
   return ResponseEntity.ok(fileAttachmentService.uploadFiles(files, staffId))
  }

  @Operation(summary = "Download documents based on file id")
  @PostMapping("/documents/download/{id}")
  fun downloadFile(@Parameter(description = "File id") @PathVariable("id") id: Int, request: HttpServletRequest): ResponseEntity<Resource> {
    val resource: Resource? = fileAttachmentService.downloadFile(id)
    var contentType: String = ""
    if (resource != null) {
      contentType = try {
        request.servletContext.getMimeType(resource.file.absolutePath)
      } catch (e: IOException) {
        log.info { "Could not determine file type. Exception message: ${e.message}" }
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null)
      }
    }
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource?.filename + "\"")
        .body(resource)
  }

  @Operation(summary = "Download documents based on file id")
  @DeleteMapping("/documents/delete/{id}")
  fun deleteFile(@Parameter(description = "File id") @PathVariable("id") id: Int): ResponseEntity<String> {
    return when(fileAttachmentService.delete(id)) {
      0 -> ResponseEntity.status(HttpStatus.FORBIDDEN).body("Path delete failed.")
      1 -> ResponseEntity.ok("Success")
      else -> {
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File delete failed.")
      }
    }
  }
}