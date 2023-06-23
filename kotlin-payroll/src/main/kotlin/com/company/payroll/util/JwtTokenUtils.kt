package com.company.payroll.util

import com.company.payroll.model.JwtTokenProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.security.Key
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Component
class JwtTokenUtils(@Autowired private val jwtTokenProperties: JwtTokenProperties) {
  private val alg: SignatureAlgorithm = SignatureAlgorithm.HS256
  private val issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS)

  /**
   * Generate token
   * @param claims
   * @return Token
   */
  fun generateToken(username: String, claims: Map<String, Any>): String {
    val key: Key = SecretKeySpec(Base64.getEncoder().encode(jwtTokenProperties.key.toByteArray()),
        alg.jcaName)
    val expiration = issuedAt.plus(jwtTokenProperties.ttl,
        ChronoUnit.SECONDS)
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(username)
        .setExpiration(Date.from(expiration))
        .signWith(key, alg)
        .compact()
  }

  /**
   * Get claims from token
   * @param token
   * @return Claims
   */
  fun getClaims(token: String): Claims {
    return Jwts.parserBuilder()
        .setSigningKey(Base64.getEncoder().encode(jwtTokenProperties.key.toByteArray()))
        .build()
        .parseClaimsJws(token)
        .body
  }

  /**
   * Get subject username
   * @param token
   * @return
   */
  fun getUsername(token: String): String? {
    val claims = getClaims(token)
    return claims.subject
  }

  /**
   * validate token
   * @param token
   * @return
   */
  fun validateToken(token: String): Boolean {
    return getUsername(token) != null && isExpired(token)
  }

  /**
   * Validate token is expired
   * @param token
   * @return
   */
  fun isExpired(token: String): Boolean {
    val claims = getClaims(token)
    return claims.expiration.after(Date.from(Instant.now()
        .truncatedTo(ChronoUnit.SECONDS)))
  }
}