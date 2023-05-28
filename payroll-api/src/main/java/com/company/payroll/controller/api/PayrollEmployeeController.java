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

import com.company.payroll.model.PayrollEmployee;
import com.company.payroll.service.PayrollEmployeeService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/payroll/employee")
public class PayrollEmployeeController {
	private static final String VALUE_ONE = "{\"basicpay\": 0, \"overtimepay\": 0, \"allowance\": 0, \"transport\": 0, "
										  + "\"otherdeduction\": 0, \"totalpay\": 0, \"payperiod\": \"string\", \"paymentdate\": \"2023-04-28\", "
										  + "\"eid\": 0, \"employee_epf\": 0, \"employee_socso\": 0, \"employee_eis\": 0, \"employer_epf\": 0, "
										  + "\"employer_socso\": 0, \"employer_eis\": 0, \"mtd_pcb\": 0}";
	
	@Autowired
	private PayrollEmployeeService payrollEmployeeService;

	@Operation(summary="Get employee payroll list")
	@GetMapping
	public ResponseEntity<PageInfo<PayrollEmployee>> listPayrollEmployee(@RequestParam(value="page", required=true) int page, 
			  														@RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(payrollEmployeeService.getListByPage(page, offset));
	}
	
	@Operation(summary="Get payroll list by employee id")
	@GetMapping("/{id}/all")
	public ResponseEntity<List<PayrollEmployee>> listPayrollEmployeeByEId(@Parameter(description="employee id") @PathVariable("id")int eid) {
		return ResponseEntity.ok(payrollEmployeeService.getListByEId(eid));
	}
	
	@Operation(summary="Get employee payroll list by id")
	@GetMapping("/{id}")
	public ResponseEntity<PayrollEmployee> getById(@Parameter() @PathVariable("id") int id) {
		return ResponseEntity.ok(payrollEmployeeService.getById(id));
	}
	
	@Operation(summary="Insert employee payroll info",
			   responses= {@ApiResponse(responseCode="200",
					   					description="Value return 1 for insert success.",
					   					content=@Content(examples= {@ExampleObject(value="1")})),
					   	   @ApiResponse(responseCode="403",
					   			   		description="Value return 0 for insert fail.",
					   			   		content=@Content(examples= {@ExampleObject(value="0")}))})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			   	 content= {@Content(mediaType="application/json", 
	   			 schema= @Schema(implementation = PayrollEmployee.class),
	   			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE)})})
	@PostMapping
	public ResponseEntity<Integer> insert(@RequestBody PayrollEmployee payrollEmployee) {
		Integer status = payrollEmployeeService.insert(payrollEmployee);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@Operation(summary="Update employee payroll info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for update success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for update fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@PutMapping("/{id}")
	public ResponseEntity<Integer> update(@RequestBody PayrollEmployee payrollEmployee) {
		Integer status = payrollEmployeeService.update(payrollEmployee);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@Operation(summary="Delete employee payroll info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for delete success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for delete fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@Parameter() @PathVariable("id") int prid) {
		Integer status = payrollEmployeeService.delete(prid);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
}
