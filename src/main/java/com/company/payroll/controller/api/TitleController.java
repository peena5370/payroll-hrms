package com.company.payroll.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.company.payroll.model.Title;
import com.company.payroll.service.TitleService;

@RestController
@RequestMapping("/title")
public class TitleController {
	
	@Autowired
	private TitleService titleService;
	
	public TitleController(TitleService titleService) {
		this.titleService = titleService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Title>> listTitle() {
		return ResponseEntity.ok(titleService.getList());
	}

	@GetMapping("/list/information/{id}")
	public ResponseEntity<Title> getByTitleno(@PathVariable("id")int titleno) {
		return ResponseEntity.ok(titleService.getByTitleno(titleno));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Integer> insert(@RequestBody Title title) {
		Integer status = titleService.insert(title);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> update(@RequestBody Title title) {
		Integer status = titleService.update(title);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> delete(@PathVariable("id")int titleno) {
		Integer status = titleService.delete(titleno);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
//	@GetMapping("/list/drop-down")
//	public List<Title> listTitlenoAndName() {
//		return titleService.listTitlenoAndName();
//	}
//	
//	@GetMapping("/list/count/all")
//	public Integer countTitle() {
//		return titleService.countTitle();
//	}
}
