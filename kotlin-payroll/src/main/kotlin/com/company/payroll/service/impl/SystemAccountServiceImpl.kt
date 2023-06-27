package com.company.payroll.service.impl

import com.company.payroll.mapper.AccountMapper
import com.company.payroll.model.SystemAccount
import com.company.payroll.service.SystemAccountService
import com.company.payroll.util.PasswordEncryption
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SystemAccountServiceImpl(@Autowired private val accountMapper: AccountMapper,
                               @Value("file.upload.directory") private val directory: String): SystemAccountService {
  override fun list(page: Int, offset: Int): PageInfo<SystemAccount> {
    PageHelper.startPage<SystemAccount>(page, offset)
    val list: List<SystemAccount> = accountMapper.selectList()
    return PageInfo<SystemAccount>(list)
  }

  override fun findById(aId: Int): SystemAccount {
    return accountMapper.selectByPrimaryKey(aId)
  }

  override fun insert(row: SystemAccount): SystemAccount {
    val demoPath: String = "${directory}/img/default/img_001.png"
    val secretKey: String = PasswordEncryption().convertSecretKeyToString(PasswordEncryption().generateSecretKey("HmacSHA256", 256))
    val passwordEncoder: PasswordEncoder = DelegatingPasswordEncoder("bcrypt", PasswordEncryption().generatePasswordEncoder(secretKey))

    val encodedPassword: String = passwordEncoder.encode(row.password)

    row.password = encodedPassword
    row.secretKey = secretKey
    row.dateCreated = LocalDateTime.now()
    row.accountStatus = 1u
    row.imgPath = demoPath

    accountMapper.insertSelective(row)

    return row
  }

  override fun updateListPassword(systemAccount: SystemAccount): SystemAccount {
    TODO("Not yet implemented")
  }

  override fun update(systemAccount: SystemAccount): SystemAccount {
    TODO("Not yet implemented")
  }

  override fun delete(aId: Int): Int {
    TODO("Not yet implemented")
  }

  override fun getByUsername(username: String): SystemAccount? {
    TODO("Not yet implemented")
  }

  override fun updateImagePath(systemAccount: SystemAccount): SystemAccount {
    TODO("Not yet implemented")
  }
}