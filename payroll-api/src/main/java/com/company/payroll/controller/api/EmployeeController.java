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

import com.company.payroll.model.Employee;
import com.company.payroll.service.EmployeeService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	private static final String VALUE_ONE = "{\"fullname\": \"string\", \"gender\": \"string\", \"age\": 0, "
										  + "\"martialstatus\": \"string\", \"education\": \"string\", \"address\": \"string\", "
										  + "\"state\": \"string\", \"country\": \"string\", \"phone\": \"string\", \"datehired\": \"2023-04-28\", "
										  + "\"attachment\": \"string\", \"imgUser\": \"string\", \"deptno\": 0, \"titleno\": 0, \"mid\": 0, "
										  + "\"bid\": 0, \"sid\": 0, \"date_of_birth\": \"2023-04-28\", \"company_email\": \"string\"}";

	@Autowired
	private EmployeeService employeeService;
	
	@Operation(summary="Get employee list")
	@GetMapping("/list")
	public ResponseEntity<PageInfo<Employee>> listEmployee(@RequestParam(value="page", required=true) int page, 
			  										@RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(employeeService.getListByPage(page, offset));
	}
	
	@Operation(summary="Get employee info by id")
	@GetMapping("/list/information/{id}")
	public ResponseEntity<Employee> getById(@Parameter() @PathVariable("id") int eid) {
		return ResponseEntity.ok(employeeService.getById(eid));
	}

	@Operation(summary="Insert employee info",
			   responses= {@ApiResponse(responseCode="200",
					   					description="Value return 1 for insert success.",
					   					content=@Content(examples= {@ExampleObject(value="1")})),
					   	   @ApiResponse(responseCode="403",
					   			   		description="Value return 0 for insert fail.",
					   			   		content=@Content(examples= {@ExampleObject(value="0")}))})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
		   	 content= {@Content(mediaType="application/json", 
  			 schema= @Schema(implementation = Employee.class),
  			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE)})})
	@PostMapping("/insert")
	public ResponseEntity<Integer> insert(@RequestBody Employee employee) {
		Integer status = employeeService.insert(employee);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@Operation(summary="Update employee info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for update success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for update fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> update(@RequestBody Employee employee) {
		Integer status = employeeService.update(employee);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@Operation(summary="Delete employee info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for delete success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for delete fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> delete(@Parameter() @PathVariable("id") int eid) {
		Integer status = employeeService.delete(eid);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
//	@GetMapping("/list/count/all")
//	public Integer countEmployee() {
//		return employeeService.countEmployee();
//	}
//	
//	@GetMapping("/list/count/active")
//	public Integer countAvailableEmployee() {
//		return employeeService.countAvailableEmployee();
//	}
}
