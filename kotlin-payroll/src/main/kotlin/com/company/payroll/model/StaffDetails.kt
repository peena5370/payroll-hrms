package com.company.payroll.model

import com.company.payroll.annotation.NoArg
import java.time.LocalDate

@NoArg
data class StaffDetails(
    var staffId: Int,
    var staffName: String,
    var gender: String,
    var dateOfBirth: LocalDate,
    var age: Short,
    var martialStatus: String,
    var education: String,
    var address: String,
    var postalCode: Int,
    var state: String,
    var country: String,
    var phone: String,
    var email: String,
    var dateHired: LocalDate,
    var dateResign: LocalDate?,
    var imgPath: String?,
    var department: Department?,
    var title: Title?,
    var staffBankingInfo: StaffBankingInfo?,
    var staffSalary: StaffSalary?,
    var staffLeaveDetails: StaffLeaveDetails?,
    var manager: StaffDetails?,
    var deptNo: Int,
    var titleNo: Int,
    var managerId: Int?,
    var ldId: Int,
    var sId: Int,
    var bId: Int
)
