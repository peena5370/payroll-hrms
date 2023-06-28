package com.company.payroll.service.impl

import com.company.payroll.mapper.AccountMapper
import com.company.payroll.model.SystemAccount
import com.company.payroll.service.SystemAccountService
import com.company.payroll.util.FileUtils
import com.company.payroll.util.PasswordEncryption
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

@Service
class SystemAccountServiceImpl(@Autowired private val accountMapper: AccountMapper,
                               @Value("file.upload.directory") private val directory: String,
                               @Autowired private val fileUtils: FileUtils): SystemAccountService {
  override fun list(page: Int, offset: Int): PageInfo<SystemAccount> {
    PageHelper.startPage<SystemAccount>(page, offset)
    return PageInfo<SystemAccount>(accountMapper.selectList())
  }

  override fun findById(aId: Int): SystemAccount {
    return accountMapper.selectByPrimaryKey(aId)
  }

  override fun findByUsername(username: String): SystemAccount {
    return accountMapper.selectByUsername(username)
  }

  override fun insert(systemAccount: SystemAccount): SystemAccount {
    val defaultPath = "$directory/account/default_img.png"
    val secretKey: String = PasswordEncryption().convertSecretKeyToString(PasswordEncryption().generateSecretKey("HmacSHA256", 256))
    val passwordEncoder: PasswordEncoder = DelegatingPasswordEncoder("bcrypt", PasswordEncryption().generatePasswordEncoder(secretKey))

    val encodedPassword: String = passwordEncoder.encode(systemAccount.password)

    systemAccount.password = encodedPassword
    systemAccount.secretKey = secretKey
    systemAccount.dateCreated = LocalDateTime.now()
    systemAccount.accountStatus = 1u
    systemAccount.imgPath = defaultPath

    accountMapper.insertSelective(systemAccount)

    return systemAccount
  }

  override fun updateListPassword(systemAccount: SystemAccount): SystemAccount {
    val secretKey: String = PasswordEncryption().convertSecretKeyToString(PasswordEncryption().generateSecretKey("HmacSHA256", 256))
    val passwordEncoder: PasswordEncoder = DelegatingPasswordEncoder("bcrypt", PasswordEncryption().generatePasswordEncoder(secretKey))

    val encodedPassword: String = passwordEncoder.encode(systemAccount.password)

    systemAccount.password = encodedPassword
    systemAccount.secretKey = secretKey
    systemAccount.dateModified = LocalDateTime.now()

    accountMapper.updateByPrimaryKeySelective(systemAccount)

    return systemAccount
  }

  override fun modifyStatusRoles(systemAccount: SystemAccount): SystemAccount {
    systemAccount.dateModified = LocalDateTime.now()

    accountMapper.updateByPrimaryKeySelective(systemAccount)

    return systemAccount
  }

  override fun delete(aId: Int): Int {
    return accountMapper.deleteByPrimaryKey(aId)
  }

  override fun updateImagePath(image: MultipartFile, systemAccount: SystemAccount): SystemAccount {
    val filepath = "/account/${systemAccount.username}"
    val contentType = image.contentType
    if (contentType == "image/jpeg" || contentType == "image/png" || contentType == "image/gif") {
      systemAccount.imgPath = fileUtils.imageUpload(image, filepath)
      systemAccount.dateModified = LocalDateTime.now()

      accountMapper.updateImagePathByUsername(systemAccount)
    } else {
      throw InvalidFileNameException(image.name, "File format are invalid.")
    }

    return systemAccount
  }

  override fun modifyPassword(systemAccount: SystemAccount): SystemAccount {
    val secretKey: String = PasswordEncryption().convertSecretKeyToString(PasswordEncryption().generateSecretKey("HmacSHA256", 256))
    val passwordEncoder: PasswordEncoder = DelegatingPasswordEncoder("bcrypt", PasswordEncryption().generatePasswordEncoder(secretKey))

    val encodedPassword: String = passwordEncoder.encode(systemAccount.password)

    systemAccount.password = encodedPassword
    systemAccount.secretKey = secretKey
    systemAccount.dateModified = LocalDateTime.now()

    accountMapper.updatePasswordByUsername(systemAccount)

    return systemAccount
  }
}