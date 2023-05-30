package com.company.payroll.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.EmployeeMapper;
import com.company.payroll.mapper.ManagerMapper;
import com.company.payroll.mapper.PromotionMapper;
import com.company.payroll.mapper.ResignationMapper;
import com.company.payroll.mapper.SalaryMapper;
import com.company.payroll.mapper.TrainingMapper;
import com.company.payroll.model.Employee;
import com.company.payroll.model.Manager;
import com.company.payroll.model.Promotion;
import com.company.payroll.model.Resignation;
import com.company.payroll.model.Salary;
import com.company.payroll.model.Training;
import com.company.payroll.service.StaffMiscellaneousService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class StaffMiscellaneousServiceImpl implements StaffMiscellaneousService {

	@Autowired
	private PromotionMapper promotionMapper;
	
	@Autowired
	private ResignationMapper resignationMapper;
	
	@Autowired
	private TrainingMapper trainingMapper;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private ManagerMapper managerMapper;
	
	@Autowired
	private SalaryMapper salaryMapper;
	
	@Override
	public Integer deletePromotion(int pid) {
		return promotionMapper.deleteByPrimaryKey(pid);
	}

	@Override
	public Integer deleteResignation(int resignid) {
		Integer row = 0;

		Optional<Manager> manager = Optional.ofNullable(managerMapper.selectByResignId(resignid));
		Optional<Employee> employee = Optional.ofNullable(employeeMapper.selectByResignId(resignid));
		
		if(manager.isPresent()) {
			Manager managerUpdate = manager.get();
			managerUpdate.setResignId(null);
			managerUpdate.setDateresign(null);
			managerMapper.updateByPrimaryKey(managerUpdate);
			row = 1;
		} else if(employee.isPresent()) {
			Employee employeeUpdate = employee.get();
			employeeUpdate.setResignId(null);
			employeeUpdate.setDateresign(null);
			employeeMapper.updateByPrimaryKey(employeeUpdate);
			row = 1;
		} else {
			throw new RuntimeException();
		}
		
		resignationMapper.deleteByPrimaryKey(resignid);
		
		return row;
	}

	@Override
	public Integer deleteTraining(int tId) {
		return trainingMapper.deleteByPrimaryKey(tId);
	}

	@Override
	public Optional<Promotion> findPromotionById(int pid) {
		return Optional.ofNullable(promotionMapper.selectByPrimaryKey(pid));
	}

	@Override
	public Optional<Resignation> findResignationById(int resignid) {
		return Optional.ofNullable(resignationMapper.selectByPrimaryKey(resignid));
	}

	@Override
	public Optional<List<Training>> findTrainingByEId(int eId) {
		return Optional.ofNullable(trainingMapper.selectListByEId(eId));
	}

	@Override
	public Optional<Training> findTrainingById(int tId) {
		return Optional.ofNullable(trainingMapper.selectByPrimaryKey(tId));
	}

	@Override
	public Promotion insertPromotion(Promotion promotion) {
		if(promotion.getEId()==null) {
			Manager manager = managerMapper.selectByPrimaryKey(promotion.getMId());
			manager.setTitleno(promotion.getTitleno());
			
			managerMapper.updateByPrimaryKey(manager);
			
			Salary salary = salaryMapper.selectByPrimaryKey(manager.getSId());
			salary.setMonthlysalary(promotion.getPromotesalary());
			salary.setDateupdate(promotion.getPromotedate());
			BigDecimal annual = promotion.getPromotesalary().multiply(BigDecimal.valueOf(13));
			salary.setAnnualsalary(annual);
			
			salaryMapper.updateByPrimaryKey(salary);
			
			promotionMapper.insertSelective(promotion);
		} else if(promotion.getMId()==null) {
			Employee employee = employeeMapper.selectByPrimaryKey(promotion.getEId());
			employee.setTitleno(promotion.getTitleno());
			
			employeeMapper.updateByPrimaryKey(employee);
			
			Salary salary = salaryMapper.selectByPrimaryKey(employee.getSId());
			salary.setMonthlysalary(promotion.getPromotesalary());
			salary.setDateupdate(promotion.getPromotedate());
			BigDecimal annual = promotion.getPromotesalary().multiply(BigDecimal.valueOf(13));
			salary.setAnnualsalary(annual);
			
			salaryMapper.updateByPrimaryKey(salary);
			
			promotionMapper.insertSelective(promotion);
		} else {
			throw new RuntimeException();
		}
		
		return promotion;
	}

	@Override
	public Resignation insertResignation(Resignation resign) {
		resignationMapper.insertSelective(resign);
		
		if(resign.getEId()==null) {
			Manager manager = managerMapper.selectByPrimaryKey(resign.getMId());
			manager.setResignId(resign.getResignId());
			manager.setDateresign(resign.getResigndate());
			
			managerMapper.updateByPrimaryKey(manager);
		} else if(resign.getMId()==null) {
			Employee employee = employeeMapper.selectByPrimaryKey(resign.getEId());
			employee.setResignId(resign.getResignId());
			employee.setDateresign(resign.getResigndate());
			
			employeeMapper.updateByPrimaryKey(employee);
		} else {
			throw new RuntimeException();
		}
		
		return resign;
	}

	@Override
	public Training insertTraining(Training training) {
		trainingMapper.insertSelective(training);
		return training;
	}

	@Override
	public PageInfo<Promotion> listPromotion(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Promotion> list = promotionMapper.selectList();
		return new PageInfo<Promotion>(list);
	}

	@Override
	public PageInfo<Resignation> listResignation(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Resignation> list = resignationMapper.selectList();
		return new PageInfo<Resignation>(list);
	}

	@Override
	public PageInfo<Training> listTraining(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Training> list = trainingMapper.selectList();
		return new PageInfo<Training>(list);
	}

	@Override
	public Promotion updatePromotion(Promotion promotion) {
		if(promotion.getEId()==null) {
			Manager manager = managerMapper.selectByPrimaryKey(promotion.getMId());
			manager.setTitleno(promotion.getTitleno());
			
			managerMapper.updateByPrimaryKey(manager);
			
			Salary salary = salaryMapper.selectByPrimaryKey(manager.getSId());
			salary.setMonthlysalary(promotion.getPromotesalary());
			salary.setDateupdate(promotion.getPromotedate());
			BigDecimal annual = promotion.getPromotesalary().multiply(BigDecimal.valueOf(13));
			salary.setAnnualsalary(annual);
			
			salaryMapper.updateByPrimaryKey(salary);
			
			promotionMapper.updateByPrimaryKeySelective(promotion);
		} else if(promotion.getMId()==null) {
			Employee employee = employeeMapper.selectByPrimaryKey(promotion.getEId());
			employee.setTitleno(promotion.getTitleno());
			
			employeeMapper.updateByPrimaryKey(employee);
			
			Salary salary = salaryMapper.selectByPrimaryKey(employee.getSId());
			salary.setMonthlysalary(promotion.getPromotesalary());
			salary.setDateupdate(promotion.getPromotedate());
			BigDecimal annual = promotion.getPromotesalary().multiply(BigDecimal.valueOf(13));
			salary.setAnnualsalary(annual);
			
			salaryMapper.updateByPrimaryKey(salary);
			
			promotionMapper.updateByPrimaryKeySelective(promotion);
		} else {
			throw new RuntimeException();
		}
		return promotion;
	}

	@Override
	public Resignation updateResignation(Resignation resign) {
		Optional<Employee> employeePresent = Optional.ofNullable(employeeMapper.selectByResignId(resign.getResignId()));
		Optional<Manager> managerPresent = Optional.ofNullable(managerMapper.selectByResignId(resign.getResignId()));
		
		if(employeePresent.isPresent()) {
			Employee employee = employeePresent.get();
			employee.setDateresign(resign.getResigndate());
			employeeMapper.updateByPrimaryKeySelective(employee);
		} else if(managerPresent.isPresent()) {
			Manager manager = managerPresent.get();
			manager.setDateresign(resign.getResigndate());
			managerMapper.updateByPrimaryKeySelective(manager);
		} else {
			throw new RuntimeException();
		}
		
		resignationMapper.updateByPrimaryKeySelective(resign);
		
		return resign;
	}

	@Override
	public Training updateTraining(Training training) {
		trainingMapper.updateByPrimaryKeySelective(training);
		return training;
	}

}
