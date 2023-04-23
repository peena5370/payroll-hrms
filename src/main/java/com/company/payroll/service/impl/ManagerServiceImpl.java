package com.company.payroll.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.ManagerMapper;
import com.company.payroll.model.Manager;
import com.company.payroll.service.ManagerService;

//@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerMapper managerMapper;
	
	@Override
	public List<Manager> listManager() {
		// TODO Auto-generated method stub
		return managerMapper.selectManagerList();
	}

	@Override
	public Manager getInfoByUsername(String username) {
		// TODO Auto-generated method stub
		return managerMapper.selectManagerbyUsername(username);
	}
	

	@Override
	public Manager getInfoById(int mid) {
		// TODO Auto-generated method stub
		return managerMapper.selectManagerbyId(mid);
	}

	@Override
	public Manager getInfoBySapId(int msapid) {
		// TODO Auto-generated method stub
		return managerMapper.selectManagerbySapId(msapid);
	}

	@Override
	public Integer countManager() {
		// TODO Auto-generated method stub
		return managerMapper.countManager();
	}

	@Override
	public Integer countAvailableManager() {
		// TODO Auto-generated method stub
		return managerMapper.countAvailableManager();
	}

	@Override
	public Integer getManagerSapId() {
		// TODO Auto-generated method stub
		return managerMapper.selectManagerSapId();
	}

	@Override
	public Integer insertManager(Manager manager) {
		// TODO Auto-generated method stub
		return managerMapper.insertManager(manager);
	}

	@Override
	public Integer updateInfoById(Manager manager) {
		// TODO Auto-generated method stub
		return managerMapper.updateManagerbyId(manager);
	}

	@Override
	public Integer updateInfoBySapId(Manager manager) {
		// TODO Auto-generated method stub
		return managerMapper.updateManagerbySapId(manager);
	}

	@Override
	public Integer updateResignDate(LocalDate dateresign, int msapid) {
		// TODO Auto-generated method stub
		return managerMapper.updateResignDatebySapId(dateresign, msapid);
	}

	@Override
	public Integer deleteManager(int mid) {
		// TODO Auto-generated method stub
		return managerMapper.deleteManager(mid);
	}

}
