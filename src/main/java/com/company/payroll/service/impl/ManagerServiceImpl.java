package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.payroll.mapper.ManagerMapper;
import com.company.payroll.model.Manager;
import com.company.payroll.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {

	private ManagerMapper managerMapper;
	
	public ManagerServiceImpl(ManagerMapper managerMapper) {
		this.managerMapper = managerMapper;
	}
	
	@Override
	public List<Manager> getList() {
		return managerMapper.selectList();
	}

	@Override
	public Manager getById(int mid) {
		return managerMapper.selectByPrimaryKey(mid);
	}

	@Override
	public Integer insert(Manager manager) {
		return managerMapper.insertSelective(manager);
	}

	@Override
	public Integer update(Manager manager) {
		return managerMapper.updateByPrimaryKeySelective(manager);
	}

	@Override
	public Integer delete(int mid) {
		return managerMapper.deleteByPrimaryKey(mid);
	}
	
//	@Override
//	public Integer countManager() {
//		// TODO Auto-generated method stub
//		return managerMapper.countManager();
//	}

//	@Override
//	public Integer countAvailableManager() {
//		// TODO Auto-generated method stub
//		return managerMapper.countAvailableManager();
//	}
}
