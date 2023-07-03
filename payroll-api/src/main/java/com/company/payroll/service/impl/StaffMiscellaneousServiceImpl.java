package com.company.payroll.service.impl;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.company.payroll.model.*;
import com.company.payroll.util.FileUtils;
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.StaffDetailsMapper;
import com.company.payroll.mapper.ManagerMapper;
import com.company.payroll.mapper.StaffPromotionMapper;
import com.company.payroll.mapper.StaffResignationMapper;
import com.company.payroll.mapper.StaffSalaryMapper;
import com.company.payroll.mapper.StaffTrainingMapper;
import com.company.payroll.model.StaffSalary;
import com.company.payroll.service.StaffMiscellaneousService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StaffMiscellaneousServiceImpl implements StaffMiscellaneousService {

	@Autowired
	private StaffDetailsMapper staffDetailsMapper;

	@Autowired
	private StaffPromotionMapper staffPromotionMapper;

	@Autowired
	private StaffSalaryMapper staffSalaryMapper;
	
	@Autowired
	private StaffResignationMapper staffResignationMapper;
	
	@Autowired
	private StaffTrainingMapper staffTrainingMapper;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public Integer deletePromotion(Integer pId) {
		return staffPromotionMapper.deleteByPrimaryKey(pId);
	}

	@Override
	public Integer deleteResignation(Integer resignId) {
		int row = 0;

		Optional<StaffResignation> staffResignation = Optional.ofNullable(staffResignationMapper.selectByPrimaryKey(resignId));
		if(staffResignation.isPresent()) {
			boolean bool = fileUtils.delete(Paths.get(staffResignation.get().getFilePath()));
			if(bool) {
				row += staffDetailsMapper.deleteByPrimaryKey(resignId);
			}
		} else {
			return null;
		}

		return row;
	}

	@Override
	public Integer deleteTraining(Integer tId) {
		return staffTrainingMapper.deleteByPrimaryKey(tId);
	}

	@Override
	public Optional<StaffPromotion> findPromotionById(Integer pId) {
		return Optional.ofNullable(staffPromotionMapper.selectByPrimaryKey(pId));
	}

	@Override
	public Optional<StaffResignation> findResignationById(Integer resignId) {
		return Optional.ofNullable(staffResignationMapper.selectByPrimaryKey(resignId));
	}

	@Override
	public Optional<List<StaffTraining>> findTrainingByEId(int eId) {
		return Optional.empty();
	}

	@Override
	public Optional<List<StaffTraining>> findTrainingByMId(int mId) {
		return Optional.empty();
	}

	@Override
	public List<StaffTraining> listTrainingByStaffId(Integer staffId) {
		return staffTrainingMapper.selectListByStaffId(staffId);
	}

	@Override
	public Optional<StaffTraining> findTrainingById(Integer tId) {
		return Optional.ofNullable(staffTrainingMapper.selectByPrimaryKey(tId));
	}

	@Override
	public StaffPromotion insertPromotion(StaffPromotion staffPromotion) {
		StaffDetails staffDetails = staffDetailsMapper.selectByPrimaryKey(staffPromotion.getStaffId());
		staffDetails.setTitleNo(staffPromotion.getTitleNo());
		staffDetailsMapper.updateByPrimaryKeySelective(staffDetails);

		StaffSalary staffSalary = staffSalaryMapper.selectByPrimaryKey(staffDetails.getSId());
		staffSalary.setMonthlySalary(staffPromotion.getPromoteSalary());
		staffSalary.setAnnualSalary(staffSalary.getMonthlySalary().multiply(BigDecimal.valueOf(13)));
		staffSalary.setDateUpdate(staffPromotion.getPromoteDate());
		staffSalaryMapper.updateByPrimaryKeySelective(staffSalary);

		staffPromotionMapper.insertSelective(staffPromotion);

		return staffPromotion;
	}

	@Override
	public StaffResignation insertResignation(StaffResignation resign) {
		return null;
	}

	@Override
	public StaffResignation insertResignation(MultipartFile attachment, StaffResignation staffResignation) {
		StaffDetails staffDetails = staffDetailsMapper.selectByPrimaryKey(staffResignation.getStaffId());
		staffDetails.setDateResign(staffResignation.getResignDate());
		staffDetailsMapper.updateByPrimaryKeySelective(staffDetails);

		String filePath = "/staffs/resign/" + staffResignation.getStaffId().toString();

		if(Objects.equals(attachment.getContentType(), "application/msword") || Objects.equals(attachment.getContentType(), "application/pdf")
				||Objects.equals(attachment.getContentType(), "application/wps-office.doc") || Objects.equals(attachment.getContentType(), "application/wps-office.docx")) {
			staffResignation.setFileName(attachment.getOriginalFilename());
			staffResignation.setFileSize(attachment.getSize());
			staffResignation.setFilePath(fileUtils.fileUpload(attachment, filePath));

			staffResignationMapper.insertSelective(staffResignation);
		} else {
			throw new InvalidFileNameException(attachment.getOriginalFilename(), "Invalid file format");
		}

		return staffResignation;
	}

	@Override
	public StaffTraining insertTraining(StaffTraining staffTraining) {
		staffTrainingMapper.insertSelective(staffTraining);
		return staffTraining;
	}

	@Override
	public PageInfo<StaffPromotion> listPromotion(int page, int offset) {
		PageHelper.startPage(page, offset);
		return new PageInfo<StaffPromotion>(staffPromotionMapper.selectList());
	}

	@Override
	public PageInfo<StaffPromotion> listPromotionByEId(int page, int offset, int eId) {
		return null;
	}

	@Override
	public PageInfo<StaffPromotion> listPromotionByStaffId(int page, int offset, Integer staffId) {
		PageHelper.startPage(page, offset);
		return new PageInfo<StaffPromotion>(staffPromotionMapper.selectListByStaffId(staffId));
	}

	@Override
	public PageInfo<StaffResignation> listResignation(int page, int offset) {
		PageHelper.startPage(page, offset);
		return new PageInfo<StaffResignation>(staffResignationMapper.selectList());
	}

	@Override
	public PageInfo<StaffTraining> listTraining(int page, int offset) {
		PageHelper.startPage(page, offset);
		return new PageInfo<StaffTraining>(staffTrainingMapper.selectList());
	}

	@Override
	public StaffPromotion updatePromotion(StaffPromotion staffPromotion) {
		StaffDetails staffDetails = staffDetailsMapper.selectByPrimaryKey(staffPromotion.getStaffId());
		staffDetails.setTitleNo(staffPromotion.getTitleNo());
		staffDetailsMapper.updateByPrimaryKeySelective(staffDetails);

		StaffSalary staffSalary = staffSalaryMapper.selectByPrimaryKey(staffDetails.getSId());
		staffSalary.setMonthlySalary(staffPromotion.getPromoteSalary());
		staffSalary.setAnnualSalary(staffSalary.getMonthlySalary().multiply(BigDecimal.valueOf(13)));
		staffSalary.setDateUpdate(staffPromotion.getPromoteDate());
		staffSalaryMapper.updateByPrimaryKeySelective(staffSalary);

		staffPromotionMapper.updateByPrimaryKeySelective(staffPromotion);

		return staffPromotion;
	}

	@Override
	public StaffResignation updateResignation(StaffResignation staffResignation) {
		StaffDetails staffDetails = staffDetailsMapper.selectByPrimaryKey(staffResignation.getStaffId());
		staffDetails.setDateResign(staffResignation.getResignDate());
		staffDetailsMapper.updateByPrimaryKeySelective(staffDetails);

		staffResignationMapper.updateByPrimaryKeySelective(staffResignation);
		return staffResignation;
	}

	@Override
	public StaffTraining updateTraining(StaffTraining staffTraining) {
		staffTrainingMapper.updateByPrimaryKeySelective(staffTraining);
		return staffTraining;
	}

	@Override
	public Resource downloadResignationAttachment(Integer resignId) {
		StaffResignation resignationDetails = staffResignationMapper.selectByPrimaryKey(resignId);

		return fileUtils.download(Paths.get(resignationDetails.getFilePath()));
	}

}
