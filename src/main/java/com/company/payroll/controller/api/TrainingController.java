package com.company.payroll.controller.api;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.Training;
import com.company.payroll.service.TrainingService;

@RestController
@RequestMapping("/training")
public class TrainingController {
	
	@Autowired
	private TrainingService trainingService;
	
	@GetMapping("/list")
	public List<Training> listTraining() {
		return trainingService.listTraining();
	}
	
	@GetMapping("/list/information/{id}")
	public Training getInfoById(@PathVariable("id")int id) {
		return trainingService.getInfoById(id);
	}
	
	@GetMapping("/list/employee/{id}")
	public List<Training> listTrainingBySapId(@PathVariable("id")int sapid) {
		return trainingService.listInfoBySapId(sapid);
	}
	
	@PostMapping("/insert")
	public Integer insertTraining(@RequestBody Training training) {
		return trainingService.insertTraining(training);
	}
	
	@PutMapping("/list/information/{id}/update")
	public Integer updateTraining(@PathVariable("id")int id, @RequestBody Training training) {
		String trainingTitle = training.getTrainingTitle();
		String desc = training.getDescription();
		LocalDateTime dateFrom = training.getStartDate();
		LocalDateTime dateTo = training.getEndDate();
		int sessionStatus = training.getSessionStatus();
		int eid = training.getEId();
		int mid = training.getMId();
		
		Training train = new Training(id, trainingTitle, desc, dateFrom, dateTo, sessionStatus, eid, mid);

		return trainingService.updateTraining(train);
	}
	
	@PutMapping("/list/information/{id}/update/status")
	public Integer updateStatusByManager(@PathVariable("id")int id, @RequestBody Training training) {
		int sessionStatus = training.getSessionStatus();
		int mid = training.getMId();
		
		Training train = new Training(id, sessionStatus, mid);
		
		return trainingService.updateStatusByManager(train);
	}
	
	@PutMapping("/list/employee/{id}/update/status")
	public Integer updateStatusByEmployee(@PathVariable("id")int sapid, @RequestBody Training training) {
		int tid = training.getTId();
		int sessionStatus = training.getSessionStatus();
		
		return trainingService.updateStatusByEmployee(tid, sessionStatus, sapid);
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public Integer deleteTraining(@PathVariable("id")int tid) {
		return trainingService.deleteTraining(tid);
	}
}
