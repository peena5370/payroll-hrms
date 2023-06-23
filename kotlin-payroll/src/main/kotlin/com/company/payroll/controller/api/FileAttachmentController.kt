package com.company.payroll.controller.api

import com.company.payroll.model.Account
import com.company.payroll.model.FileAttachment
import com.company.payroll.service.FileAttachmentService
import com.company.payroll.service.SystemAccountService
import com.company.payroll.util.FileUtils
import com.company.payroll.util.JwtTokenUtils
import io.jsonwebtoken.Claims
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
import java.nio.file.Paths
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@RestController
@RequestMapping("/api/files")
class FileAttachmentController(@Autowired private val fileUtils: FileUtils,
                               @Autowired private val jwtTokenUtils: JwtTokenUtils,
                               @Autowired private val systemAccountService: SystemAccountService,
                               @Autowired private val fileAttachmentService: FileAttachmentService) {

  private val log = KotlinLogging.logger {}

  @Operation(summary = "Upload profile image")
  @PostMapping("/profile/image/upload")
  fun uploadImage(@RequestParam("file") file: MultipartFile, request: HttpServletRequest): ResponseEntity<Account> {
    TODO("to be updated")
//    var status: Account? = null
//    val header = request.getHeader("Authorization")
//    val token = header.substring(7)
//    val claims: Claims = jwtTokenUtils.getClaims(token)
//    val date = LocalDate.now()
//    val imgpath = ("/images/" + claims.subject + "/" + date.year
//        + "/" + date.monthValue + "/" + date.dayOfMonth)
//    val contentType = file.contentType
//    if (contentType == "image/jpeg" || contentType == "image/png" || contentType == "image/gif") {
//      val uploadPath = fileUtils.imageUpload(file, imgpath)
//      if (uploadPath != "") {
//        val account = Account()
//        account.username = claims.subject
//        account.imgPath = uploadPath
//        account.dateModified = LocalDateTime.now()
//        status = systemAccountService.updateImagePath(account)
//        if (status == null) {
//          return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status)
//        }
//      }
//    } else {
//      return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(status)
//    }
//    return ResponseEntity.ok(status)
  }

  @Operation(summary = "Load profile image")
  @PostMapping("/profile/image/loads")
  fun loadImage(request: HttpServletRequest): ResponseEntity<Resource> {
    TODO("to be updated")
//    val header = request.getHeader("Authorization")
//    val token = header.substring(7)
//    val claims: Claims = jwtTokenUtils.getClaims(token)
//    val account: Account? = systemAccountService.getByUsername(claims.subject.toString())
//    val resource: Resource? = account?.imgPath?.let { Paths.get(it) }?.let { fileUtils.download(it) }
//    var contentType: String? = null
//    if (resource != null) {
//      contentType = try {
//        request.servletContext.getMimeType(resource.getFile().getAbsolutePath())
//      } catch (e: IOException) {
//        log.info("Could not determine file type. Exception message: {}", e)
//        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null)
//      }
//    }
//    return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body<Resource?>(resource)
  }

  @Operation(summary = "Get employee document list")
  @GetMapping("/documents/employee/{id}/list")
  fun getEmployeeList(@Parameter(description = "Employee id") @PathVariable("id") id: Int): ResponseEntity<List<FileAttachment>> {
    return ResponseEntity.ok(fileAttachmentService.getListByStaffId(id))
  }

  @Operation(summary = "Get manager document list")
  @GetMapping("/documents/manager/{id}/list")
  fun getManagerList(@Parameter(description = "Manager id") @PathVariable("id") id: Int): ResponseEntity<List<FileAttachment>> {
    return ResponseEntity.ok(fileAttachmentService.getListByStaffId(id))
  }

  @Operation(summary = "Upload multiple employee documents")
  @PostMapping("/documents/upload/employee/{id}")
  fun uploadEmployeeFiles(@Parameter(description = "Files") @RequestParam("files") files: Array<MultipartFile>,
                          @Parameter(description = "Employee id") @PathVariable("id") id: Int): ResponseEntity<String> {
    TODO("to be updated")
//    val filepath = "/files/employees/$id"
//    val obj = FileAttachment()
//    val fileList: MutableList<Array<MultipartFile>>? = Arrays.asList(files).stream().toList()
//    for (file in fileList) {
//      if (file.contentType == "application/msword" || file.contentType == "application/pdf" || file.contentType == "application/wps-office.doc" || file.contentType == "application/wps-office.docx") {
//        val path = fileUtils.fileUpload(file, filepath)
//        obj.fileName = file.originalFilename!!
//        obj.fileSize = file.size
//        obj.filePath = path
//        obj.staffId = id
//        fileAttachmentService.insert(obj)
//      } else {
//        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Media type not supported.")
//      }
//    }
//    return ResponseEntity.ok("Success")
  }

  @Operation(summary = "Upload multiple manager documents")
  @PostMapping("/documents/upload/manager/{id}")
  fun uploadManagerFiles(@Parameter(description = "Files") @RequestParam("files") files: Array<MultipartFile>,
                         @Parameter(description = "Manager id") @PathVariable("id") id: Int): ResponseEntity<String> {
    TODO("to be updated")
//    val filepath = "/files/manager/$id"
//    val obj = FileAttachment()
//    val fileList: MutableList<Array<MultipartFile>>? = Arrays.asList(files).stream().toList()
//    for (file in fileList) {
//      if (file.contentType == "application/msword" || file.contentType == "application/pdf" || file.contentType == "application/wps-office.doc" || file.contentType == "application/wps-office.docx") {
//        val path = fileUtils.fileUpload(file, filepath)
//        obj.fileName = file.originalFilename!!
//        obj.fileSize = file.size
//        obj.filePath = path
//        obj.staffId = id
//        fileAttachmentService.insert(obj)
//      } else {
//        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Media type not supported.")
//      }
//    }
//    return ResponseEntity.ok("Success")
  }

  @Operation(summary = "Download documents based on file id")
  @PostMapping("/documents/download/{id}")
  fun downloadFile(@Parameter(description = "File id") @PathVariable("id") id: Int, request: HttpServletRequest): ResponseEntity<Resource> {
    val obj = fileAttachmentService.getByPrimaryKey(id)
    val resource: Resource? = obj?.filePath?.let { Paths.get(it) }?.let { fileUtils.download(it) }
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
    val obj = fileAttachmentService.getByPrimaryKey(id)
    val status = obj?.fId?.let { fileAttachmentService.delete(it) }
    if (status == 0) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Path delete failed.")
    } else {
      val bool = obj?.filePath?.let { Paths.get(it) }?.let { fileUtils.delete(it) }
      if (!bool!!) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File delete failed.")
      }
    }
    return ResponseEntity.ok("Success")
  }
}