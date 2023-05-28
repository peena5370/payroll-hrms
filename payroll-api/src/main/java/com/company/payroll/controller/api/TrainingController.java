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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.Training;
import com.company.payroll.service.TrainingService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/training")
public class TrainingController {
	private static final String VALUE_ONE = "{\"trainingtitle\": \"string\", \"description\": \"string\", \"mid\": 0, \"eid\": 0, "
										  + "\"date_start\": \"2023-04-28T13:56:54.865Z\", \"date_end\": \"2023-04-28T13:56:54.865Z\", "
										  + "\"status\": \"string\"}";
	
	@Autowired
	private TrainingService trainingService;
	
	@Operation(summary="Get training list")
	@GetMapping
	public ResponseEntity<PageInfo<Training>> listTraining(@RequestParam(value="page", required=true) int page, 
			  										@RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(trainingService.getListByPage(page, offset));
	}
	
	@Operation(summary="Get training info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Training> getById(@Parameter() @PathVariable("id") int tId) {
		return ResponseEntity.ok(trainingService.getById(tId));
	}
	
	@Operation(summary="Get training list by employee id")
	@GetMapping("/{id}/all")
	public ResponseEntity<List<Training>> getListByEId(@Parameter(description="employee id") @PathVariable("id") int eId) {
		return ResponseEntity.ok(trainingService.getListByEId(eId));
	}
	
	@Operation(summary="Insert training info",
			   responses= {@ApiResponse(responseCode="200",
					   					description="Value return 1 for insert success.",
					   					content=@Content(examples= {@ExampleObject(value="1")})),
					   	   @ApiResponse(responseCode="403",
					   			   		description="Value return 0 for insert fail.",
					   			   		content=@Content(examples= {@ExampleObject(value="0")}))})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			   	 content= {@Content(mediaType="application/json", 
	   			 schema= @Schema(implementation = Training.class),
	   			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE)})})
	@PostMapping
	public ResponseEntity<Integer> insert(@RequestBody Training training) {
		Integer status = trainingService.insert(training);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@Operation(summary="Update training info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for update success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for update fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@PutMapping("/{id}")
	public ResponseEntity<Integer> update(@RequestBody Training training) {
		Integer status = trainingService.update(training);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}

	@Operation(summary="Delete training info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for delete success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for delete fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@Parameter() @PathVariable("id") int tId) {
		Integer status = trainingService.delete(tId);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}	
}
