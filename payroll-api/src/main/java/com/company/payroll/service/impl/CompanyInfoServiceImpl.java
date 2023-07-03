package com.company.payroll.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.DepartmentMapper;
import com.company.payroll.mapper.TitleMapper;
import com.company.payroll.model.Department;
import com.company.payroll.model.Title;
import com.company.payroll.service.CompanyInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

	@Autowired
	private DepartmentMapper departmentMapper;

	@Autowired
	private TitleMapper titleMapper;

	@Override
	public Integer deleteDepartment(Integer deptNo) {
		return departmentMapper.deleteByPrimaryKey(deptNo);
	}

	@Override
	public Integer deleteTitle(Integer titleNo) {
		return titleMapper.deleteByPrimaryKey(titleNo);
	}

	@Override
	public Optional<Department> findDepartmentById(Integer deptNo) {
		return Optional.ofNullable(departmentMapper.selectByPrimaryKey(deptNo));
	}

	@Override
	public Optional<Title> findTitleById(Integer titleNo) {
		return Optional.ofNullable(titleMapper.selectByPrimaryKey(titleNo));
	}

	@Override
	public Department insertDepartment(Department department) {
		departmentMapper.insertSelective(department);
		return department;
	}

	@Override
	public Title insertTitle(Title title) {
		titleMapper.insert(title);
		return title;
	}

	@Override
	public PageInfo<Department> listDepartment(int page, int offset) {
		PageHelper.startPage(page, offset);
		return new PageInfo<Department>(departmentMapper.selectList());
	}

	@Override
	public PageInfo<Title> listTitle(int page, int offset) {
		PageHelper.startPage(page, offset);
		return new PageInfo<Title>(titleMapper.selectList());
	}

	@Override
	public Department updateDepartment(Department department) {
		departmentMapper.updateByPrimaryKeySelective(department);
		return department;
	}

	@Override
	public Title updateTitle(Title title) {
		titleMapper.updateByPrimaryKeySelective(title);
		return title;
	}

}
