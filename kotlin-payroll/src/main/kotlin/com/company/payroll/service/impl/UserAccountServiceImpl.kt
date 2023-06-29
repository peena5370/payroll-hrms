package com.company.payroll.service.impl

import com.company.payroll.mapper.AccountMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserAccountServiceImpl(@Autowired private val accountMapper: AccountMapper): UserDetailsService {
  override fun loadUserByUsername(username: String): UserDetails {
    val account = accountMapper.selectByUsername(username)
    if(account.password.isNullOrEmpty()) {
      throw UsernameNotFoundException("Username: \'$username\' is not exist in database.")
    }

    return User(account.username, account.password, ArrayList())
  }
}