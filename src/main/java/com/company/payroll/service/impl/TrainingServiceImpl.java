package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.payroll.mapper.TrainingMapper;
import com.company.payroll.model.Training;
import com.company.payroll.service.TrainingService;

@Service
public class TrainingServiceImpl implements TrainingService {

	private TrainingMapper trainingMapper;
	
	public TrainingServiceImpl(TrainingMapper trainingMapper) {
		this.trainingMapper = trainingMapper;
	}
	
	@Override
	public List<Training> getList() {
		return trainingMapper.selectList();
	}

	@Override
	public Training getById(int tId) {
		return trainingMapper.selectByPrimaryKey(tId);
	}

	@Override
	public List<Training> getListByEId(int eId) {
		return trainingMapper.selectListByEId(eId);
	}

	@Override
	public Integer insert(Training training) {
		return trainingMapper.insertSelective(training);
	}

	@Override
	public Integer update(Training training) {
		return trainingMapper.updateByPrimaryKeySelective(training);
	}

	@Override
	public Integer delete(int tId) {
		return trainingMapper.deleteByPrimaryKey(tId);
	}
}
