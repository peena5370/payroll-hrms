package com.company.payroll.service.impl

import com.company.payroll.model.SystemAccount
import com.company.payroll.service.SystemAccountService
import com.github.pagehelper.PageInfo
import org.springframework.stereotype.Service

@Service
class SystemAccountServiceImpl: SystemAccountService {
  override fun list(page: Int, offset: Int): PageInfo<SystemAccount> {
    TODO("Not yet implemented")
  }

  override fun findById(aId: Int): SystemAccount? {
    TODO("Not yet implemented")
  }

  override fun insert(row: SystemAccount): SystemAccount {
    TODO("Not yet implemented")
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