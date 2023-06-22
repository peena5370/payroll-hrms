package com.company.payroll.service.impl

import com.company.payroll.model.Account
import com.company.payroll.service.SystemAccountService
import com.github.pagehelper.PageInfo
import org.springframework.stereotype.Service

@Service
class SystemAccountServiceImpl: SystemAccountService {
  override fun list(page: Int, offset: Int): PageInfo<Account> {
    TODO("Not yet implemented")
  }

  override fun findById(aId: Int): Account? {
    TODO("Not yet implemented")
  }

  override fun insert(row: Account): Account {
    TODO("Not yet implemented")
  }

  override fun updateListPassword(account: Account): Account {
    TODO("Not yet implemented")
  }

  override fun update(account: Account): Account {
    TODO("Not yet implemented")
  }

  override fun delete(aId: Int): Int {
    TODO("Not yet implemented")
  }

  override fun getByUsername(username: String): Account? {
    TODO("Not yet implemented")
  }

  override fun updateImagePath(account: Account): Account {
    TODO("Not yet implemented")
  }
}