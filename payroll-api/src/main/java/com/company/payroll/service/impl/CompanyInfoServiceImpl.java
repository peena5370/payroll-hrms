package com.company.payroll.service.impl;

import java.util.List;
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
	public Integer deleteDepartment(int deptno) {
		return departmentMapper.deleteByPrimaryKey(deptno);
	}

	@Override
	public Integer deleteTitle(int titleno) {
		return titleMapper.deleteByPrimaryKey(titleno);
	}

	@Override
	public Optional<Department> findDepartmentById(int deptno) {
		return Optional.ofNullable(departmentMapper.selectByPrimaryKey(deptno));
	}

	@Override
	public Optional<Title> findTitleById(int titleno) {
		return Optional.ofNullable(titleMapper.selectByPrimaryKey(titleno));
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
		List<Department> list = departmentMapper.selectList();
		return new PageInfo<Department>(list);
	}

	@Override
	public PageInfo<Title> listTitle(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Title> list = titleMapper.selectList();
		return new PageInfo<Title>(list);
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
