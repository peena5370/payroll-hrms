package com.company.payroll.service.impl

import com.company.payroll.mapper.*
import com.company.payroll.model.StaffPromotion
import com.company.payroll.model.StaffResignation
import com.company.payroll.model.StaffTraining
import com.company.payroll.service.StaffMiscellaneousService
import com.company.payroll.util.FileUtils
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.math.BigDecimal
import java.nio.file.Paths

@Service
class StaffMiscellaneousServiceImpl(@Autowired private val staffPromotionMapper: StaffPromotionMapper,
                                    @Autowired private val staffDetailsMapper: StaffDetailsMapper,
                                    @Autowired private val staffSalaryMapper: StaffSalaryMapper,
                                    @Autowired private val staffResignationMapper: StaffResignationMapper,
                                    @Autowired private val fileUtils: FileUtils,
                                    @Autowired private val staffTrainingMapper: StaffTrainingMapper): StaffMiscellaneousService {
  override fun deletePromotion(pId: Int): Int {
    return staffPromotionMapper.deleteByPrimaryKey(pId)
  }

  override fun deleteResignation(resignId: Int): Int {
    var row = 0
    val resign = staffResignationMapper.selectByPrimaryKey(resignId)
    val bool = resign.filePath?.let { Paths.get(it) }?.let { fileUtils.delete(it) }

    if(bool == true) {
      row = staffResignationMapper.deleteByPrimaryKey(resignId)
    }

    return row
  }

  override fun deleteTraining(tId: Int): Int {
    return staffTrainingMapper.deleteByPrimaryKey(tId)
  }

  override fun findPromotionById(pId: Int): StaffPromotion {
    return staffPromotionMapper.selectByPrimaryKey(pId)
  }

  override fun findResignationById(resignId: Int): StaffResignation {
    return staffResignationMapper.selectByPrimaryKey(resignId)
  }

  override fun findTrainingById(tId: Int): StaffTraining {
    return staffTrainingMapper.selectByPrimaryKey(tId)
  }

  override fun insertPromotion(staffPromotion: StaffPromotion): StaffPromotion {
    val staffDetails = staffDetailsMapper.selectByPrimaryKey(staffPromotion.staffId)
    staffDetails.titleNo = staffPromotion.titleNo
    staffDetailsMapper.updateByPrimaryKeySelective(staffDetails)

    val staffSalary = staffSalaryMapper.selectByPrimaryKey(staffDetails.sId)
    staffSalary.monthlySalary = staffPromotion.promoteSalary
    staffSalary.annualSalary = staffSalary.monthlySalary.multiply(BigDecimal(13))
    staffSalary.dateUpdate = staffPromotion.promoteDate
    staffSalaryMapper.updateByPrimaryKeySelective(staffSalary)

    staffPromotionMapper.insertSelective(staffPromotion)

    return staffPromotion
  }

  override fun insertResignation(attachment: MultipartFile, staffResignation: StaffResignation): StaffResignation {
    val staffDetails = staffDetailsMapper.selectByPrimaryKey(staffResignation.staffId)
    staffDetails.dateResign = staffResignation.resignDate
    staffDetailsMapper.updateByPrimaryKeySelective(staffDetails)

    val filepath = "/staffs/resign/${staffResignation.staffId}"

    if (attachment.contentType == "application/msword" || attachment.contentType == "application/pdf" ||
        attachment.contentType == "application/wps-office.doc" || attachment.contentType == "application/wps-office.docx") {
      staffResignation.fileName = attachment.originalFilename
      staffResignation.fileSize = attachment.size
      staffResignation.filePath = fileUtils.fileUpload(attachment, filepath)

      staffResignationMapper.insertSelective(staffResignation)
    } else {
      throw InvalidFileNameException(attachment.name, "Invalid file format.")
    }

    return staffResignation
  }

  override fun insertTraining(staffTraining: StaffTraining): StaffTraining {
    staffTrainingMapper.insertSelective(staffTraining)
    return staffTraining
  }

  override fun listPromotion(page: Int, offset: Int): PageInfo<StaffPromotion> {
    PageHelper.startPage<StaffPromotion>(page, offset)
    return PageInfo<StaffPromotion>(staffPromotionMapper.selectList())
  }

  override fun listPromotionByStaffId(page: Int, offset: Int, staffId: Int): PageInfo<StaffPromotion> {
    PageHelper.startPage<StaffPromotion>(page, offset)
    return PageInfo<StaffPromotion>(staffPromotionMapper.selectListByStaffId(staffId))
  }

  override fun listResignation(page: Int, offset: Int): PageInfo<StaffResignation> {
    PageHelper.startPage<StaffResignation>(page, offset)
    return PageInfo<StaffResignation>(staffResignationMapper.selectList())
  }

  override fun listTraining(page: Int, offset: Int): PageInfo<StaffTraining> {
    PageHelper.startPage<StaffTraining>(page, offset)
    return PageInfo<StaffTraining>(staffTrainingMapper.selectList())
  }

  override fun listTrainingByStaffId(staffId: Int): List<StaffTraining> {
    return staffTrainingMapper.selectListByStaffId(staffId)
  }

  override fun updatePromotion(staffPromotion: StaffPromotion): StaffPromotion {
    val staffDetails = staffDetailsMapper.selectByPrimaryKey(staffPromotion.staffId)
    staffDetails.titleNo = staffPromotion.titleNo
    staffDetailsMapper.updateByPrimaryKeySelective(staffDetails)

    val staffSalary = staffSalaryMapper.selectByPrimaryKey(staffDetails.sId)
    staffSalary.monthlySalary = staffPromotion.promoteSalary
    staffSalary.annualSalary = staffSalary.monthlySalary.multiply(BigDecimal(13))
    staffSalary.dateUpdate = staffPromotion.promoteDate
    staffSalaryMapper.updateByPrimaryKeySelective(staffSalary)

    staffPromotionMapper.updateByPrimaryKeySelective(staffPromotion)

    return staffPromotion
  }

  override fun updateResignation(staffResignation: StaffResignation): StaffResignation {
    val staffDetails = staffDetailsMapper.selectByPrimaryKey(staffResignation.staffId)
    staffDetails.dateResign = staffResignation.resignDate
    staffDetailsMapper.updateByPrimaryKeySelective(staffDetails)

    staffResignationMapper.updateByPrimaryKeySelective(staffResignation)

    return staffResignation
  }

  override fun updateTraining(staffTraining: StaffTraining): StaffTraining {
    staffTrainingMapper.updateByPrimaryKeySelective(staffTraining)
    return staffTraining
  }
}