package com.company.payroll.model

import lombok.RequiredArgsConstructor

@RequiredArgsConstructor
data class ResponseObject(
    var code: Int,
    var msg: String
)
