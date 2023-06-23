package com.company.payroll.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class AccountDetails(
    var accountAuthorities: MutableCollection<out GrantedAuthority>,
    var accountPassword: String,
    var accountUsername: String,
    var accountExpired: Boolean,
    var accountLocked: Boolean,
    var credentialExpired: Boolean,
    var accountEnabled: Boolean
): UserDetails {
  override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
    return this.accountAuthorities
  }

  override fun getPassword(): String {
    return this.accountPassword
  }

  override fun getUsername(): String {
    return this.accountUsername
  }

  override fun isAccountNonExpired(): Boolean {
    return this.accountExpired
  }

  override fun isAccountNonLocked(): Boolean {
    return this.accountLocked
  }

  override fun isCredentialsNonExpired(): Boolean {
    return this.credentialExpired
  }

  override fun isEnabled(): Boolean {
    return this.accountEnabled
  }
}
