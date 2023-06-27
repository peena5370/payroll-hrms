package com.company.payroll.controller.api

import com.company.payroll.model.StaffResignation
import com.company.payroll.service.StaffMiscellaneousService
import com.company.payroll.util.FileUtils
import com.github.pagehelper.PageInfo
import io.swagger.v3.oas.annotations.Operation
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
import java.nio.file.Paths

@RestController
@RequestMapping("/api/resign")
class StaffResignationController(@Autowired private val staffMiscellaneousService: StaffMiscellaneousService,
                                 @Autowired private val fileUtils: FileUtils) {
  private val log = KotlinLogging.logger {}

  @Operation(summary = "Get resignation list")
  @GetMapping
  fun listResignation(@RequestParam(value = "page", required = true) page: Int, @RequestParam(value = "size", required = true) offset: Int): ResponseEntity<PageInfo<StaffResignation>> {
    return ResponseEntity.ok(staffMiscellaneousService.listResignation(page, offset))
  }

  @Operation(summary = "Get resign info by id")
  @GetMapping("/{id}")
  fun getById(@PathVariable("id") id: Int): ResponseEntity<StaffResignation?> {
    return ResponseEntity.ok(staffMiscellaneousService.findResignationById(id))
  }

  @Operation(summary = "Insert resign info")
  @PostMapping
  fun insert(@RequestPart("file") file: MultipartFile, @RequestPart("resignation") staffResignation: StaffResignation): ResponseEntity<String?>? {
    val filepath = "/files/staffs/resign_files/${staffResignation.staffId}"

    if (file.contentType == "application/msword" || file.contentType == "application/pdf" || file.contentType == "application/wps-office.doc" || file.contentType == "application/wps-office.docx") {
      staffResignation.fileName = file.originalFilename!!
      staffResignation.fileSize = file.size
      staffResignation.filePath = fileUtils.fileUpload(file, filepath)
      staffMiscellaneousService.insertResignation(staffResignation)
    } else {
      return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("unsupported media type")
    }
    return ResponseEntity.ok("success")
  }

  @Operation(summary = "Download attachment")
  @PostMapping("/{id}/attachment/download")
  fun downloadAttachment(@PathVariable("id") id: Int, request: HttpServletRequest): ResponseEntity<out Resource>? {
    val resign: StaffResignation? = staffMiscellaneousService.findResignationById(id)
    val resource: Resource? = resign?.filePath?.let { Paths.get(it) }?.let { fileUtils.download(it) }
    var contentType: String? = null
    if (resource != null) {
      contentType = try {
        request.servletContext.getMimeType(resource.file.absolutePath)
      } catch (e: IOException) {
        log.info { "Could not determine file type. Exception message: ${e.message}" }
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null)
      }
    }
    return contentType?.let { MediaType.parseMediaType(it) }?.let {
      ResponseEntity.ok()
        .contentType(it)
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource?.filename + "\"")
        .body(resource)
    }
  }

  @Operation(summary = "Update resign info.")
  @PutMapping("/{id}")
  fun update(@RequestBody staffResignation: StaffResignation): ResponseEntity<StaffResignation> {
    return ResponseEntity.ok(staffMiscellaneousService.updateResignation(staffResignation))
  }

  @Operation(summary = "Delete resign info.")
  @DeleteMapping("/{id}")
  fun delete(@PathVariable("id") id: Int): ResponseEntity<Int?>? {
    val resign: StaffResignation? = staffMiscellaneousService.findResignationById(id)
    if (resign != null) {
      fileUtils.delete(Paths.get(resign.filePath))
    }
    return ResponseEntity.ok(staffMiscellaneousService.deleteResignation(id))
  }
}