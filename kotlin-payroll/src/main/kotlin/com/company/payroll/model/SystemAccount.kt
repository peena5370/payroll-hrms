package com.company.payroll.model

import com.company.payroll.annotation.NoArg
import java.time.LocalDateTime

@NoArg
data class SystemAccount(
    var aId: Int,
    var username: String,
    var password: String?,
    var secretKey: String?,
    var roles: String?,
    var dateCreated: LocalDateTime?,
    var dateModified: LocalDateTime?,
    var lastLogin: LocalDateTime?,
    var lastAttempt: UByte?,
    var accountStatus: UByte?,
    var imgPath: String?,
    var staffId: Int?
)
