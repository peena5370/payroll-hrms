package com.company.payroll.controller.api

import com.company.payroll.model.SystemAccount
import com.company.payroll.service.SystemAccountService
import com.github.pagehelper.PageInfo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.NoSuchAlgorithmException
import java.util.*

@RestController
@RequestMapping("/api/accounts")
class AccountController(@Autowired private val systemAccountService: SystemAccountService) {
  companion object {
    private const val valueOne = """{"username": "string", "password": "string",
                                    "roles": "string", "mid": null, "eid": null}"""
    private const val valueTwo = """{"username": "string", "roles": "string", "mid": null, "eid": null, "aid": 0, 
                                    "modified_date": "2023-04-28T11:38:12.262Z", "status": 0}"""
    private const val valueThree = """{"username": "string", "password": "strings", "aid": 0, 
                                      "modified_date": "2023-04-28T11:38:12.262Z"}"""
  }

  @GetMapping
  fun list(@RequestParam(value = "page", required = true) page: Int, @RequestParam(value = "size", required = true) offset: Int): ResponseEntity<PageInfo<SystemAccount>> {
    return ResponseEntity.ok(systemAccountService.list(page, offset))
  }

  @Operation(summary = "Get account info by id")
  @GetMapping("/{id}")
  fun findById(@Parameter(description = "Account id") @PathVariable("id") id: Int): ResponseEntity<SystemAccount?> {
    return ResponseEntity.ok(systemAccountService.findById(id))
  }

  @Operation(summary = "Register new account")
  @RequestBody(content = [Content(mediaType = "application/json", schema = Schema(implementation = SystemAccount::class), examples = [ExampleObject(name = "Example 1", value = valueOne)])])
  @PostMapping
  @Throws(NoSuchAlgorithmException::class)
  protected fun insert(@RequestBody systemAccount: SystemAccount): ResponseEntity<String> {
    systemAccountService.insert(systemAccount)
    return ResponseEntity.ok("register success")
  }

  @Operation(summary = "Update account password", responses = [ApiResponse(responseCode = "200", description = "Value return 1 for update success.", content = [Content(examples = [ExampleObject(value = "1")])]), ApiResponse(responseCode = "403", description = "Value return 0 for update fail.", content = [Content(examples = [ExampleObject(value = "0")])])])
  @RequestBody(required = true, content = [Content(mediaType = "application/json", schema = Schema(implementation = SystemAccount::class), examples = [ExampleObject(name = "Example 1", value = valueThree)])])
  @PutMapping("/{id}/password")
  @Throws(NoSuchAlgorithmException::class)
  fun updateListPassword(@RequestBody systemAccount: SystemAccount): ResponseEntity<String> {
    systemAccountService.updateListPassword(systemAccount)
    return ResponseEntity.ok("update success")
  }

  @Operation(summary = "Update account.")
  @RequestBody(required = true, content = [Content(mediaType = "application/json", schema = Schema(implementation = SystemAccount::class), examples = [ExampleObject(name = "Example 1", value = valueTwo)])])
  @PutMapping("/{id}")
  fun updateStatusAndRoles(@RequestBody systemAccount: SystemAccount): ResponseEntity<String> {
    systemAccountService.modifyStatusRoles(systemAccount)
    return ResponseEntity.ok("update success")
  }

  @Operation(summary = "Delete account.", responses = [ApiResponse(responseCode = "200", description = "Value return 1 for delete success.", content = [Content(examples = [ExampleObject(value = "1")])]), ApiResponse(responseCode = "403", description = "Value return 0 for delete fail.", content = [Content(examples = [ExampleObject(value = "0")])])])
  @DeleteMapping("/{id}")
  fun delete(@Parameter(description = "Account id") @PathVariable("id") aid: Int): ResponseEntity<Int> {
    return ResponseEntity.ok(systemAccountService.delete(aid))
  }
}