package com.company.payroll.controller.api

import com.company.payroll.model.Title
import com.company.payroll.service.CompanyInfoService
import com.github.pagehelper.PageInfo
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/system/title")
class TitleController(@Autowired private val companyInfoService: CompanyInfoService) {
  @Operation(summary = "Get title list")
  @GetMapping
  fun listTitleByPage(@RequestParam(value = "page", required = true) page: Int, @RequestParam(value = "size", required = true) offset: Int): ResponseEntity<PageInfo<Title>> {
    return ResponseEntity.ok(companyInfoService.listTitle(page, offset))
  }

  @Operation(summary = "Get title info by id")
  @GetMapping("/{id}")
  fun getByTitleNo(@PathVariable("id") titleNo: Int): ResponseEntity<Title> {
    return ResponseEntity.ok(companyInfoService.findTitleById(titleNo))
  }

  @Operation(summary = "Insert title info")
  @PostMapping
  fun insert(@RequestBody title: Title): ResponseEntity<Title> {
    return ResponseEntity.status(HttpStatus.CREATED).body(companyInfoService.insertTitle(title))
  }

  @Operation(summary = "Update title info.")
  @PutMapping("/{id}")
  fun update(@RequestBody title: Title): ResponseEntity<Title> {
    return ResponseEntity.ok(companyInfoService.updateTitle(title))
  }

  @Operation(summary = "Delete title info.")
  @DeleteMapping("/{id}")
  fun delete(@PathVariable("id") titleNo: Int): ResponseEntity<Int> {
    return ResponseEntity.ok(companyInfoService.deleteTitle(titleNo))
  }
}