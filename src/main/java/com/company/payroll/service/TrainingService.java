package com.company.payroll.service;

import java.util.List;

import com.company.payroll.model.Training;

public interface TrainingService {

	/** 
	 * 
	 * @return
	 * */
	List<Training> listTraining();
	
	/** 
	 * 
	 * @return
	 * */
	Training getInfoById(int tid);
	
	/** 
	 * 
	 * @return
	 * */
	List<Training> listInfoBySapId(int esapid);
	
	/** 
	 * 
	 * @return
	 * */
	Integer insertTraining(Training training);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateTraining(Training training);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateStatusByManager(Training training);
	
	/** 
	 * 
	 * @return
	 * */
	Integer updateStatusByEmployee(int tid, int sessionstatus, int esapid);
	
	/** 
	 * 
	 * @return
	 * */
	Integer deleteTraining(int tid);
}
