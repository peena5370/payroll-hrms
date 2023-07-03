package com.company.payroll.service.impl;

import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

import com.company.payroll.mapper.*;
import com.company.payroll.model.*;
import com.company.payroll.util.FileUtils;
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.company.payroll.service.StaffDetailsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StaffDetailsServiceImpl implements StaffDetailsService {
	
	@Autowired
	private StaffDetailsMapper staffDetailsMapper;
	
	@Autowired
	private StaffBankingInfoMapper staffBankingInfoMapper;
	
	@Autowired
	private StaffSalaryMapper staffSalaryMapper;

	@Autowired
	private StaffLeaveDetailsMapper staffLeaveDetailsMapper;

	@Autowired
	private FileUtils fileUtils;

	@Override
	public Integer countActiveEmployee(int deptno) {
		return null;
	}
	

	@Override
	public Integer countActiveManager(int deptno) {
		return null;
	}

	@Override
	public Integer countActiveStaff(Integer deptNo) {
		return null;
	}

	@Override
	public Integer deleteEmployee(int eid) {
		return null;
	}

	@Override
	public Integer deleteManager(int mid) {
		return null;
	}

	@Override
	public Integer deleteStaffDetails(Integer staffId) {
		int row = 0;
		StaffDetails staffDetails = staffDetailsMapper.selectByPrimaryKey(staffId);

		boolean status1 = fileUtils.delete(Paths.get(staffDetails.getImgPath()));
		int status2 = staffDetailsMapper.deleteByPrimaryKey(staffDetails.getStaffId());
		int status3 = staffBankingInfoMapper.deleteByPrimaryKey(staffDetails.getBId());
		int status4 = staffSalaryMapper.deleteByPrimaryKey(staffDetails.getSId());
		int status5 = staffLeaveDetailsMapper.deleteByPrimaryKey(staffDetails.getLdId());

		if(status1 && status2==1 && status3==1 && status4==1 && status5==1) {
			row += 1;
		}

		return row;
	}

	@Override
	public Optional<StaffDetails> findEmployeeById(int eid) {
		return Optional.empty();
	}

	@Override
	public Optional<Manager> findManagerById(int mid) {
		return Optional.empty();
	}

	@Override
	public Optional<StaffDetails> findByStaffId(Integer staffId) {
		return Optional.ofNullable(staffDetailsMapper.selectByPrimaryKey(staffId));
	}

	@Override
	public PageInfo<StaffDetails> listEmployee(int page, int offset) {
		return null;
	}

	@Override
	public PageInfo<Manager> listManager(int page, int offset) {
		return null;
	}

	@Override
	public PageInfo<StaffDetails> listStaffDetails(int page, int offset) {
		PageHelper.startPage(page, offset);
		return new PageInfo<StaffDetails>(staffDetailsMapper.selectList());
	}

	@Override
	public StaffDetails registerEmployee(StaffDetails staffDetails) {
		return null;
	}

	@Override
	public Manager registerManager(Manager manager) {
		return null;
	}

	@Override
	public StaffDetails addStaffDetails(MultipartFile staffImage, StaffDetails staffDetails) {
		String filePath = "/staff/list";
		String contentType = staffImage.getContentType();
		if(Objects.equals(contentType, "image/jpeg") || Objects.equals(contentType, "image/png")
				|| Objects.equals(contentType, "image/gif")) {
			staffDetails.setImgPath(fileUtils.imageUpload(staffImage, filePath));
		} else {
			throw new InvalidFileNameException(staffImage.getOriginalFilename(), "Invalid file format");
		}

		staffBankingInfoMapper.insertSelective(staffDetails.getStaffBankingInfo());
		StaffBankingInfo bankingInfo = staffBankingInfoMapper.selectByPrimaryKey(staffDetails.getStaffBankingInfo().getBId());

		staffSalaryMapper.insertSelective(staffDetails.getStaffSalary());
		StaffSalary salaryInfo = staffSalaryMapper.selectByPrimaryKey(staffDetails.getStaffSalary().getSId());

		staffLeaveDetailsMapper.insertSelective(staffDetails.getStaffLeaveDetails());
		StaffLeaveDetails leaveInfo = staffLeaveDetailsMapper.selectByPrimaryKey(staffDetails.getStaffLeaveDetails().getLdId());

		StaffDetails manager = staffDetailsMapper.selectByPrimaryKey(staffDetails.getManagerId());
		staffDetails.setManagerId(manager.getStaffId());

		if(bankingInfo!=null && salaryInfo!=null && leaveInfo!=null) {
			staffDetails.setLdId(leaveInfo.getLdId());
			staffDetails.setSId(salaryInfo.getSId());
			staffDetails.setBId(bankingInfo.getBId());
		} else {
			return null;
		}

		staffDetailsMapper.insertSelective(staffDetails);

		return staffDetails;
	}

	@Override
	public StaffDetails updateEmployee(StaffDetails staffDetails) {
		return null;
	}

	@Override
	public Manager updateManager(Manager manager) {
		return null;
	}

	@Override
	public StaffDetails updateStaffDetails(StaffDetails staffDetails) {
		staffDetailsMapper.updateByPrimaryKeySelective(staffDetails);
		return staffDetails;
	}

	@Override
	public Resource loadStaffImage(Integer staffId) {
		StaffDetails staffDetails = staffDetailsMapper.selectByPrimaryKey(staffId);
		return fileUtils.download(Paths.get(staffDetails.getImgPath()));
	}
}
