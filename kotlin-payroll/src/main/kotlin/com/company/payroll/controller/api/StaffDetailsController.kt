package com.company.payroll.controller.api

import com.company.payroll.model.StaffDetails
import com.company.payroll.service.StaffDetailsService
import com.github.pagehelper.PageInfo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

/**
 * @author caroline
 * <p> Created: 23 June 2023
 */
@RestController
@RequestMapping("/api/staff")
class StaffDetailsController(@Autowired private val staffDetailsService: StaffDetailsService) {

  private val log = KotlinLogging.logger {}

  @Operation(summary = "Get employee list")
  @GetMapping
  fun listStaff(@RequestParam(value = "page", required = true) page: Int,
                @RequestParam(value = "size", required = true) offset: Int): ResponseEntity<PageInfo<StaffDetails>> {
    return ResponseEntity.ok(staffDetailsService.listStaffDetails(page, offset))
  }

  @Operation(summary = "Get employee info by id")
  @GetMapping("/{id}")
  fun getById(@PathVariable("id") staffId: Int): ResponseEntity<StaffDetails> {
    return ResponseEntity.ok(staffDetailsService.findByStaffId(staffId))
  }

  @Operation(summary = "Load employee image")
  @PostMapping("/{staff_id}/image/loads")
  fun loadStaffImage(@PathVariable("staff_id") staffId: Int, request: HttpServletRequest): ResponseEntity<Resource> {
    val resource: Resource? = staffDetailsService.loadStaffImage(staffId)
    var contentType = ""
    if (resource != null) {
      contentType = try {
        request.servletContext.getMimeType(resource.file.absolutePath)
      } catch (e: IOException) {
        log.info { "Could not determine file type. Exception message: ${e.message}" }
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null)
      }
    }
    return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource)
  }

  @Operation(summary = "Insert employee info")
  @PostMapping
  fun insert(@Parameter(description = "image file") @RequestPart("img") image: MultipartFile,
             @RequestPart("employee") staffDetails: StaffDetails): ResponseEntity<StaffDetails> {
    return ResponseEntity.ok(staffDetailsService.addStaffDetails(image, staffDetails))
  }

  @Operation(summary = "Update employee info.")
  @PutMapping("/{id}")
  fun update(@RequestBody staffDetails: StaffDetails): ResponseEntity<StaffDetails> {
    return ResponseEntity.ok(staffDetailsService.updateStaffDetails(staffDetails))
  }

  @Operation(summary = "Delete employee info.")
  @DeleteMapping("/{id}")
  fun delete(@PathVariable("id") staffId: Int): ResponseEntity<Int> {
    return ResponseEntity.ok(staffDetailsService.deleteStaffDetails(staffId))
  }

  @Operation(summary = "Get active employee count.")
  @GetMapping("/count/{dept_no}/active")
  fun count(@PathVariable("dept_no") deptNo: Int): ResponseEntity<Int> {
    return ResponseEntity.ok(staffDetailsService.countActiveStaff(deptNo))
  }
}