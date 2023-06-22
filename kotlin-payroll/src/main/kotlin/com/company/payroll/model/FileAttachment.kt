package com.company.payroll.model

import lombok.AllArgsConstructor
import lombok.RequiredArgsConstructor

@AllArgsConstructor
@RequiredArgsConstructor
data class FileAttachment(
    var fId: Int,
    var fileName: String,
    var fileSize: Long,
    var filePath: String,
    var staffId: Int
)
