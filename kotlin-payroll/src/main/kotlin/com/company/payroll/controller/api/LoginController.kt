package com.company.payroll.controller.api

import com.company.payroll.model.SystemAccount
import com.company.payroll.model.ResponseObject
import com.company.payroll.service.SystemAccountService
import com.company.payroll.util.JwtTokenUtils
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
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class LoginController(@Autowired private val systemAccountService: SystemAccountService,
                      @Autowired private val jwtTokenUtils: JwtTokenUtils) {
//                      @Autowired private val authenticationManager: AuthenticationManager) {

  private val log = KotlinLogging.logger {}

  private val VALUE_ONE = "{\"username\": \"string\", \"password\": \"string\"}"
  private val VALUE_TWO = "{\"username\": \"testaccount\", \"password\": \"Abcde@12345\"}"
  private val VALUE_THREE = "{\"code\": 200, \"msg\": \"Login Success.\"}"
  private val VALUE_FOUR = "{\"code\": 401, \"msg\": \"Account has reach max attempt login.\"}"
  private val VALUE_FIVE = "{\"code\": 401, \"msg\": \"Wrong user password.\"}"
  private val VALUE_SIX = "{\"code\": 500, \"msg\": \"The account does not exist.\"}"

  @Operation(summary = "System login API", description = """API will consist of the response status from back-end server.
This API will also generate an authorization token for the client which saved at the response header('Authorization').""", parameters = [Parameter(name = "username", required = true, description = "Username"), Parameter(name = "password", required = true, description = "Password")], responses = [ApiResponse(responseCode = "200", description = "Login success.", content = [Content(mediaType = "application/json", schema = Schema(implementation = ResponseObject::class), examples = [ExampleObject(value = "VALUE_THREE")])]), ApiResponse(responseCode = "401", description = "Unauthorized request.", content = [Content(mediaType = "application/json", schema = Schema(implementation = ResponseObject::class), examples = [ExampleObject(name = "Example 1", value = "VALUE_FOUR"), ExampleObject(name = "Example 2", value = "VALUE_FIVE")])]), ApiResponse(responseCode = "500", description = "Account not available in server.", content = [Content(mediaType = "application/json", schema = Schema(implementation = ResponseObject::class), examples = [ExampleObject(value = "VALUE_SIX")])])])
  @RequestBody(required = true, content = [Content(mediaType = "application/json", schema = Schema(implementation = SystemAccount::class), examples = [ExampleObject(name = "Example 1", value = "VALUE_ONE"), ExampleObject(name = "Example 2", value = "VALUE_TWO")])])
  @PostMapping("/login")
  fun backendLoginValidate(request: HttpServletRequest, @RequestBody systemAccount: SystemAccount): ResponseEntity<ResponseObject> {
    TODO("to be updated")
//    val resp = ResponseObject()
//    var token: String? = null
//    val claims: MutableMap<String, Any> = HashMap()
//    val authToken = UsernamePasswordAuthenticationToken(account.username, account.password)
//    try {
//      val obj: Account? = systemAccountService.getByUsername(account.username)
//      val passwordEncoder: PasswordEncoder = DelegatingPasswordEncoder("bcrypt", obj?.secretkey?.let { PasswordEncryption().generatePasswordEncoder(it) })
//      val match = passwordEncoder.matches(account.password, obj?.password)
//      if (match && obj?.lastAttempt!! < 5u) {
//        when (obj.accountStatus) {
//          0 -> log.info { "Inactive account has success login." }
//          1 -> {
//            val obj2 = Account()
//            obj2.aId = obj.aId
//            obj2.lastAttempt = 1.toByte().toUByte()
//            obj2.lastLogin = LocalDateTime.now()
//            systemAccountService.update(obj2)
//            when (obj.roles) {
//              "role_admin" -> {
//                authenticationManager.authenticate(authToken)
//                claims["roles"] = obj.roles
//                claims["id"] = obj.staffId
//                claims["client_address"] = request.remoteAddr
//                token = jwtTokenUtils.generateToken(account.username, claims)
//              }
//
//              "role_manager" -> {
//                authenticationManager.authenticate(authToken)
//                claims["roles"] = obj.roles
//                claims["id"] = obj.staffId
//                claims["client_address"] = request.remoteAddr
//                token = jwtTokenUtils.generateToken(account.username, claims)
//              }
//
//              else -> {
//                authenticationManager.authenticate(authToken)
//                claims["roles"] = obj.roles
//                claims["id"] = obj.staffId
//                claims["client_address"] = request.remoteAddr
//                token = jwtTokenUtils.generateToken(account.username, claims)
//              }
//            }
//            resp.code = 200
//            resp.msg = "Login success."
//          }
//
//          2 -> log.info("Locked account has success login.")
//          else -> {}
//        }
//      } else if (obj?.lastAttempt!! >= 6u) {
//        val obj3 = Account()
//        obj3.aId = obj.aId
//        obj3.accountStatus = 2.toByte().toUByte()
//        systemAccountService.update(obj3)
//        resp.code = 401
//        resp.msg = "Account has reach max attempt login."
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp)
//      } else {
//        val obj4 = Account()
//        obj4.aId = obj.aId
//        obj4.lastAttempt = (obj.lastAttempt + 1u).toByte().toUByte()
//        systemAccountService.update(obj4)
//        resp.code = 401
//        resp.msg = "Wrong user password."
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp)
//      }
//    } catch (e: NullPointerException) {
//      log.error("Error message: {}", e.message)
//      resp.code = 500
//      resp.msg = "The account does not exist."
//      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp)
//    } catch (e: NoSuchAlgorithmException) {
//      log.error("Error message: {}", e.message)
//      resp.code = 500
//      resp.msg = "The account does not exist."
//      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp)
//    }
//    return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer $token").body<ResponseObject?>(resp)
  }

  @Operation(summary = "System logout API", description = "TODO")
  @PostMapping("/logout")
  fun logout(): ResponseEntity<ResponseObject> {
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
}