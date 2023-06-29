package com.company.payroll.model

import com.company.payroll.annotation.NoArg

@NoArg
data class FileAttachment(
    var fId: Int?,
    var fileName: String,
    var fileSize: Long,
    var filePath: String,
    var staffId: Int
)
