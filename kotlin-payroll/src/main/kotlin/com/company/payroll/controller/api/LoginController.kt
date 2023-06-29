package com.company.payroll.controller.api

import com.company.payroll.model.ResponseObject
import com.company.payroll.model.SystemAccount
import com.company.payroll.service.SystemAccountService
import com.company.payroll.util.JwtTokenUtils
import com.company.payroll.util.PasswordEncryption
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/users")
class LoginController(@Autowired private val systemAccountService: SystemAccountService,
                      @Autowired private val jwtTokenUtils: JwtTokenUtils,
                      @Autowired private val authenticationManager: AuthenticationManager) {

  private val log = KotlinLogging.logger {}

  companion object {
    private const val VALUE_ONE = "{\"username\": \"string\", \"password\": \"string\"}"
    private const val VALUE_TWO = "{\"username\": \"demouser123\", \"password\": \"Abcde@12345\"}"
    private const val VALUE_THREE = "{\"code\": 200, \"msg\": \"Login Success.\"}"
    private const val VALUE_FOUR = "{\"code\": 401, \"msg\": \"Account has reach max attempt login.\"}"
    private const val VALUE_FIVE = "{\"code\": 401, \"msg\": \"Wrong user password.\"}"
    private const val VALUE_SIX = "{\"code\": 500, \"msg\": \"The account does not exist.\"}"
  }


  @Operation(summary = "System login API", description = """API will consist of the response status from back-end server.
This API will also generate an authorization token for the client which saved at the response header('Authorization').""",
      parameters = [Parameter(name = "username", required = true, description = "Username"), Parameter(name = "password", required = true, description = "Password")], responses = [ApiResponse(responseCode = "200", description = "Login success.", content = [Content(mediaType = "application/json", schema = Schema(implementation = ResponseObject::class), examples = [ExampleObject(value = VALUE_THREE)])]), ApiResponse(responseCode = "401", description = "Unauthorized request.", content = [Content(mediaType = "application/json", schema = Schema(implementation = ResponseObject::class), examples = [ExampleObject(name = "Example 1", value = VALUE_FOUR), ExampleObject(name = "Example 2", value = VALUE_FIVE)])]), ApiResponse(responseCode = "500", description = "Account not available in server.", content = [Content(mediaType = "application/json", schema = Schema(implementation = ResponseObject::class), examples = [ExampleObject(value = VALUE_SIX)])])])
  @RequestBody(required = true, content = [Content(mediaType = "application/json", schema = Schema(implementation = SystemAccount::class), examples = [ExampleObject(name = "Example 1", value = VALUE_ONE), ExampleObject(name = "Example 2", value = VALUE_TWO)])])
  @PostMapping("/login")
  fun loginValidate(request: HttpServletRequest, @RequestBody systemAccount: SystemAccount): ResponseEntity<ResponseObject> {
    val resp = ResponseObject(0, "")
    var token = ""
    val claims: MutableMap<String, String> = HashMap()
    val authToken = UsernamePasswordAuthenticationToken(systemAccount.username, systemAccount.password)
    try {
      val accountDetails = systemAccountService.findByUsername(systemAccount.username)
      val passwordEncoder: PasswordEncoder = DelegatingPasswordEncoder("bcrypt", PasswordEncryption().generatePasswordEncoder(accountDetails.secretKey.toString()))
      val match = passwordEncoder.matches(systemAccount.password, accountDetails.password)
      if (match && accountDetails.lastAttempt!! < 5u) {
        when (accountDetails.accountStatus) {
          0.toUByte() -> {
            log.warn { "Inactive account has success login." }
            resp.code = 401
            resp.msg = "Inactive account has success login."
          }
          1.toUByte() -> {
            val obj2 = SystemAccount(accountDetails.aId, accountDetails.username, null, null, null, null ,
                null, LocalDateTime.now(), 1.toUByte(), null, null, null)
            systemAccountService.modifyStatusRoles(obj2)
            authenticationManager.authenticate(authToken)
            claims["roles"] = accountDetails.roles.toString()
            claims["id"] = accountDetails.aId.toString()
            claims["client_address"] = request.remoteAddr
            token = jwtTokenUtils.generateToken(accountDetails.username, claims)

            resp.code = 200
            resp.msg = "Login success."
          }

          2.toUByte() -> {
            log.warn { "Locked account has success login." }
            resp.code = 401
            resp.msg = "Locked account has success login."
          }
          else -> {
            log.warn { "There should be any error at back-end." }
            resp.code = 500
            resp.msg = "User account status may not be an accurate value."
          }
        }
      } else if (accountDetails.lastAttempt!! >= 6u) {
        val obj3 = SystemAccount(accountDetails.aId, accountDetails.username, null, null, null, null ,
            null, null, null, 2.toUByte(), null, null)
        systemAccountService.modifyStatusRoles(obj3)

        resp.code = 401
        resp.msg = "Account has reach max attempt login."

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp)
      } else {
        val obj4 = SystemAccount(accountDetails.aId, accountDetails.username, null, null, null, null ,
            null, null, accountDetails.lastAttempt!!.inc(), null, null, null)
        systemAccountService.modifyStatusRoles(obj4)

        resp.code = 401
        resp.msg = "Wrong user password."

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp)
      }
    } catch (e: NullPointerException) {
      log.error { "Error message: ${e.message}" }
      resp.code = 500
      resp.msg = "The account does not exist."

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp)
    }

    return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer $token").body(resp)
  }

  @Operation(summary = "System logout API", description = "TODO")
  @PostMapping("/logout")
  fun logout(request: HttpServletRequest): ResponseEntity<ResponseObject> {
    val header = request.getHeader("Authorization")
    val resp = ResponseObject(200, "Success logout.")
    /**
     * TODO to be add up validate user logout.
     * Items needed:
     * 1. remote address
     * 2. token
     * 3. redis cache token
     *
     * Blacklist token with Redis container
     * 1. replace Bearer header to Blacklist header
     * 2. save to Redis
     */
    return ResponseEntity.ok(resp)
  }

  @Operation(summary = "Upload profile image")
  @PostMapping("/image/upload")
  fun uploadUserImage(@RequestParam("img") image: MultipartFile, request: HttpServletRequest): ResponseEntity<SystemAccount> {
    val header = request.getHeader("Authorization")
    val claims = jwtTokenUtils.getClaims(header.substring(7))
    val accountDetails = systemAccountService.findByUsername(claims.subject)

    return ResponseEntity.ok(systemAccountService.updateImagePath(image, accountDetails))
  }

  @Operation(summary = "Load profile image")
  @PostMapping("/image/download")
  fun downloadUserImage(request: HttpServletRequest): ResponseEntity<Resource> {
    val header = request.getHeader("Authorization")
    val claims = jwtTokenUtils.getClaims(header.substring(7))
    val resource: Resource? = systemAccountService.downloadAccountImage(claims.subject)
    var contentType = ""
    if (resource != null) {
      contentType = try {
        request.servletContext.getMimeType(resource.file.absolutePath)
      } catch (e: IOException) {
        log.error { "Could not determine file type. Exception message: ${e.message}" }
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null)
      }
    }

    return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource)
  }
}