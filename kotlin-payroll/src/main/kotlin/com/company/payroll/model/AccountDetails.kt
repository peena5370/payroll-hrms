package com.company.payroll.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class AccountDetails(
    var authorities: MutableCollection<out GrantedAuthority>,
    var password: String,
    var username: String,
    var accountNonExpired: Boolean,
    var accountNonLocked: Boolean,
    var credentialNonExpired: Boolean,
    var enabled: Boolean
): UserDetails {
  override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities

  override fun getPassword(): String = password

  override fun getUsername(): String = username

  override fun isAccountNonExpired(): Boolean = accountNonExpired

  override fun isAccountNonLocked(): Boolean = accountNonLocked

  override fun isCredentialsNonExpired(): Boolean = credentialNonExpired

  override fun isEnabled(): Boolean = enabled
}
