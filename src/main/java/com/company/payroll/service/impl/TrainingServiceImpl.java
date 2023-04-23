package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.TrainingMapper;
import com.company.payroll.model.Training;
import com.company.payroll.service.TrainingService;

@Service
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	private TrainingMapper trainingMapper;
	
	@Override
	public List<Training> listTraining() {
		// TODO Auto-generated method stub
		return trainingMapper.selectTraining();
	}

	@Override
	public Training getInfoById(int tid) {
		// TODO Auto-generated method stub
		return trainingMapper.selectTrainingbyId(tid);
	}

	@Override
	public List<Training> listInfoBySapId(int esapid) {
		// TODO Auto-generated method stub
		return trainingMapper.selectTrainingbySapId(esapid);
	}

	@Override
	public Integer insertTraining(Training training) {
		// TODO Auto-generated method stub
		return trainingMapper.insertTraining(training);
	}

	@Override
	public Integer updateTraining(Training training) {
		// TODO Auto-generated method stub
		return trainingMapper.updateTraining(training);
	}

	@Override
	public Integer updateStatusByManager(Training training) {
		// TODO Auto-generated method stub
		return trainingMapper.updateTrainingStatusbyManager(training);
	}

	@Override
	public Integer updateStatusByEmployee(int tid, int sessionstatus, int esapid) {
		// TODO Auto-generated method stub
		return trainingMapper.updateTrainingStatusbyEmployee(tid, sessionstatus, esapid);
	}

	@Override
	public Integer deleteTraining(int tid) {
		// TODO Auto-generated method stub
		return trainingMapper.deleteTraining(tid);
	}

}
