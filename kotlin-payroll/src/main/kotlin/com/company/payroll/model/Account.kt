package com.company.payroll.model

import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor
import java.time.LocalDateTime

@AllArgsConstructor
@RequiredArgsConstructor
data class Account(
    var aId: Int,
    var username: String,
    var password: String,
    var secretkey: String,
    var roles: String,
    var dateCreated: LocalDateTime,
    var dateModified: LocalDateTime,
    var lastLogin: LocalDateTime,
    var lastAttempt: UByte,
    var accountStatus: UByte,
    var imgPath: String,
    var staffId: Int
)
