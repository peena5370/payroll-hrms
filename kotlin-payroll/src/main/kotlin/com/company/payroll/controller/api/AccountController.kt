package com.company.payroll.controller.api

import com.company.payroll.service.SystemAccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/accounts")
class AccountController(@Autowired private val systemAccountService: SystemAccountService) {
  private val valueOne = "{\"username\": \"string\", \"password\": \"string\", \"\n" +
                         " + \"\"roles\": \"string\", \"mid\": null, \"eid\": null}"
}