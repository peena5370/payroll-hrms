package com.company.payroll.util

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import java.util.*
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec
import kotlin.collections.HashMap
import kotlin.math.pow


class PasswordEncryption {
  private val random: SecureRandom = SecureRandom()
  private val characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
  private val defaultIterations = 310000
  private val defaultSaltLength = 32
  private val skfa = SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512
  private val initStrength = 12
  private val defaultProcessorCost = 2.0.pow(14.0).toInt()
  private val defaultRamCost = 8
  private val parallelization = 1
  private val defaultKeyLength = 32

  /**
   * Method to generate the salt value.
   * @param  length      Salt value length
   * @return  salt string
   */
  fun getSaltValue(length: Int): String {
    val finalVal = StringBuilder(length)
    for (i in 0 until length) {
      finalVal.append(characters[random.nextInt(characters.length)])
    }
    return String(finalVal)
  }

  /**
   * Method to generate the hash value
   * @param  password    Array character password
   * @param  salt
   * Salt value
   */
  private fun hash(password: CharArray, salt: ByteArray): ByteArray {
    val spec = PBEKeySpec(password, salt, defaultIterations, defaultKeyLength)
    Arrays.fill(password, Character.MIN_VALUE)
    return try {
      val skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
      skf.generateSecret(spec).encoded
    } catch (e: NoSuchAlgorithmException) {
      throw AssertionError("Error while hashing a password: " + e.message, e)
    } catch (e: InvalidKeySpecException) {
      throw AssertionError("Error while hashing a password: " + e.message, e)
    } finally {
      spec.clearPassword()
    }
  }

  /**
   * Method to encrypt the password using the original password and salt value.
   * @param  password    Get string password
   * @param  salt      Get salt value
   * @return  Base64 string
   */
  fun generateSecurePassword(password: String, salt: String): String? {
    var finalVal: String? = null
    val securePassword = hash(password.toCharArray(), salt.toByteArray())
    finalVal = Base64.getEncoder().encodeToString(securePassword)
    return finalVal
  }

  /**
   * Method to verify if both password matches or not
   * @param  providedPassword  Get password string
   * @param  securedPassword    Get Encrypted password
   * @param  salt        Get salt value
   * @return  boolean
   */
  fun verifyUserPassword(providedPassword: String, securedPassword: String, salt: String): Boolean {
    var finalVal = false
    // Generate New secure password with the same salt
    val newSecurePassword = generateSecurePassword(providedPassword, salt)
    // Check if two passwords are equal
    finalVal = newSecurePassword.equals(securedPassword, ignoreCase = true)
    return finalVal
  }

  /**
   *
   * Created 22 Apr 2023
   * @param cipher
   * @param keySize  key length(128, 192, 256) in bits
   * @return
   * @throws NoSuchAlgorithmException
   */
  @Throws(NoSuchAlgorithmException::class)
  fun generateSecretKey(cipher: String, keySize: Int): SecretKey? {
    val keyGenerator: KeyGenerator = KeyGenerator.getInstance(cipher)
    keyGenerator.init(keySize, SecureRandom.getInstanceStrong())
    return keyGenerator.generateKey()
  }

  /**
   *
   * Created 22 Apr 2023
   * @param secretKey
   * @return
   * @throws NoSuchAlgorithmException
   */
  @Throws(NoSuchAlgorithmException::class)
  fun generatePasswordEncoder(secretKey: String): Map<String, PasswordEncoder> {
    val encoders: MutableMap<String, PasswordEncoder> = HashMap()
    encoders["bcrypt"] = BCryptPasswordEncoder()
    // TODO
    encoders["pbkdf2"] = Pbkdf2PasswordEncoder(secretKey, defaultSaltLength, defaultIterations, skfa)
    encoders["pbkdf2@SpringSecurity_v5_8"] = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8()
    encoders["scrypt@SpringSecurity_v5_8"] = SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8()
    encoders["argon2@SpringSecurity_v5_8"] = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8()
    return encoders
  }

  /**
   * Getting password secret key
   * @param password
   * @param salt
   * @return
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeySpecException
   */
  @Throws(NoSuchAlgorithmException::class, InvalidKeySpecException::class)
  fun getKeyFromPassword(password: String, salt: String): SecretKey {
    val skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
    val pbeKeySpec = PBEKeySpec(password.toCharArray(), salt.toByteArray(), defaultIterations, defaultSaltLength)
    return SecretKeySpec(skf.generateSecret(pbeKeySpec).encoded, "AES")
  }

  /**
   * Method of converting secret key to string
   * Created 22 Apr 2023
   * @param secretKey
   * @return
   */
  fun convertSecretKeyToString(secretKey: SecretKey): String {
    val rawData = secretKey.encoded
    return Base64.getEncoder().encodeToString(rawData)
  }

  /**
   * Method of converting string to secret key
   * Created 22 Apr 2023
   * @param encodedKey
   * @return
   */
  fun convertStringToSecretKey(encodedKey: String?): SecretKey {
    val decodedKey: ByteArray = Base64.getDecoder().decode(encodedKey)
    return SecretKeySpec(decodedKey, 0, decodedKey.size, "AES")
  }

  /**
   * Generate random salt value
   * @param size
   * @return
   */
  fun generateRandomSalt(size: Int): ByteArray {
    val salt = ByteArray(size)
    random.nextBytes(salt)
    return salt
  }

  /**
   * Generate random string with selected string length
   * @param length
   * @return
   */
  fun getRandomString(length: Int): String {
    val stringBuilder = StringBuilder(length)
    for (i in 0 until length) {
      stringBuilder.append(characters[random.nextInt(characters.length)])
    }
    return String(stringBuilder)
  }

  /**
   *
   * Created 22 Apr 2023
   * @param password
   * @return
   */
  fun encodePasswordAsBCrypt(password: String): String {
    val encoder = BCryptPasswordEncoder(initStrength, SecureRandom())
    return encoder.encode(password)
  }

  /**
   *
   * Created 22 Apr 2023
   * @param rawPassword
   * @param encryptedPassword
   * @return
   */
  fun bCryptPasswordMatched(rawPassword: String, encryptedPassword: String): Boolean {
    var bool = false
    val encoder = BCryptPasswordEncoder(initStrength, SecureRandom())
    if (encoder.matches(rawPassword, encryptedPassword)) {
      bool = true
    }
    return bool
  }

  /**
   *
   * Created 22 Apr 2023
   * @param password
   * @param secretKey
   * @return
   */
  fun encodePasswordAsPbkdf2(password: String, secretKey: String): String {
    val pbkdf2PasswordEncoder = Pbkdf2PasswordEncoder(secretKey, defaultSaltLength,
        defaultIterations, skfa)
    pbkdf2PasswordEncoder.setEncodeHashAsBase64(true)
    return pbkdf2PasswordEncoder.encode(password)
  }

  /**
   *
   * Created 22 Apr 2023
   * @param rawPassword
   * @param hashedPassword
   * @param secretKey
   * @return
   */
  fun pbkdf2PasswordMatched(rawPassword: String, hashedPassword: String, secretKey: String): Boolean {
    var bool = false
    val pbkdf2PasswordEncoder = Pbkdf2PasswordEncoder(secretKey, defaultSaltLength,
        defaultIterations, skfa)
    pbkdf2PasswordEncoder.setEncodeHashAsBase64(true)
    if (pbkdf2PasswordEncoder.matches(rawPassword, hashedPassword)) {
      bool = true
    }
    return bool
  }

  /**
   *
   * Created 22 Apr 2023
   * @param plainPassword
   * @return
   */
  fun encodePasswordAsSCrypt(plainPassword: String): String {
    val sCryptPasswordEncoder = SCryptPasswordEncoder(defaultProcessorCost, defaultRamCost,
        parallelization, defaultKeyLength, defaultSaltLength)
    return sCryptPasswordEncoder.encode(plainPassword)
  }

  /**
   *
   * Created 22 Apr 2023
   * @param rawPassword
   * @param hashedPassword
   * @return
   */
  fun scryptPasswordMatched(rawPassword: String, hashedPassword: String): Boolean {
    var bool = false
    val sCryptPasswordEncoder = SCryptPasswordEncoder(defaultProcessorCost, defaultRamCost,
        parallelization, defaultKeyLength, defaultSaltLength)
    if (sCryptPasswordEncoder.matches(rawPassword, hashedPassword)) {
      bool = true
    }
    return bool
  }
}