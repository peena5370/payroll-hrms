package com.company.payroll.controller.api;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	private TrainingService trainingService;
	
	public TrainingController(TrainingService trainingService) {
		this.trainingService = trainingService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Training>> listTraining() {
		return ResponseEntity.ok(trainingService.getList());
	}
	
	@GetMapping("/list/information/{id}")
	public ResponseEntity<Training> getById(@PathVariable("id")int tId) {
		return ResponseEntity.ok(trainingService.getById(tId));
	}
	
	@GetMapping("/{id}/list")
	public ResponseEntity<List<Training>> getListByEId(@PathVariable("id")int eId) {
		return ResponseEntity.ok(trainingService.getListByEId(eId));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Integer> insertTraining(@RequestBody Training training) {
		Integer status = trainingService.insert(training);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> updateTraining(@RequestBody Training training) {
		Integer status = trainingService.update(training);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}

	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> deleteTraining(@PathVariable("id")int tId) {
		Integer status = trainingService.delete(tId);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
//	@PutMapping("/list/information/{id}/update/status")
//	public Integer updateStatusByManager(@PathVariable("id")int id, @RequestBody Training training) {
//		int sessionStatus = training.getSessionStatus();
//		int mid = training.getMId();
//		
//		Training train = new Training(id, sessionStatus, mid);
//		
//		return trainingService.updateStatusByManager(train);
//	}
//	
//	@PutMapping("/list/employee/{id}/update/status")
//	public Integer updateStatusByEmployee(@PathVariable("id")int sapid, @RequestBody Training training) {
//		int tid = training.getTId();
//		int sessionStatus = training.getSessionStatus();
//		
//		return trainingService.updateStatusByEmployee(tid, sessionStatus, sapid);
//	}
	
}
