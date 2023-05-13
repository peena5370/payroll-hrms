package com.company.payroll.controller.api;

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

import com.company.payroll.model.Salary;
import com.company.payroll.service.SalaryService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {
	private static final String VALUE_ONE = "{\"monthlysalary\": 0, \"annualsalary\": 0, \"dateupdate\": \"2023-04-28\"}";
	
	@Autowired
	private SalaryService salaryService;
	
	@Operation(summary="Get salary list")
	@GetMapping("/list")
	public ResponseEntity<PageInfo<Salary>> listSalary(@RequestParam(value="page", required=true) int page, 
			  									@RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(salaryService.getListByPage(page, offset));
	}
	
	@Operation(summary="Get salary info by id")
	@GetMapping("/list/information/{id}")
	public ResponseEntity<Salary> getById(@Parameter() @PathVariable("id") int sid) {
		return ResponseEntity.ok(salaryService.getById(sid));
	}
	
	@Operation(summary="Insert salary info",
			   responses= {@ApiResponse(responseCode="200",
					   					description="Value return 1 for insert success.",
					   					content=@Content(examples= {@ExampleObject(value="1")})),
					   	   @ApiResponse(responseCode="403",
					   			   		description="Value return 0 for insert fail.",
					   			   		content=@Content(examples= {@ExampleObject(value="0")}))})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			   	 content= {@Content(mediaType="application/json", 
	   			 schema= @Schema(implementation = Salary.class),
	   			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE)})})
	@PostMapping("/insert")
	public ResponseEntity<Integer> insert(@RequestBody Salary salary) {
		Integer status = salaryService.insert(salary);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@Operation(summary="Update salary info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for update success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for update fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> update(@RequestBody Salary salary) {
		Integer status = salaryService.update(salary);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@Operation(summary="Delete salary info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for delete success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for delete fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> delete(@Parameter() @PathVariable("id") int sid) {
		Integer status = salaryService.delete(sid);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}

}
