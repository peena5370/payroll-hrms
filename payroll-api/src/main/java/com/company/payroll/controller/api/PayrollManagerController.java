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

import com.company.payroll.model.PayrollManager;
import com.company.payroll.service.PayrollManagerService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/payroll/manager")
public class PayrollManagerController {
	private static final String VALUE_ONE = "{\"basicpay\": 0, \"overtimepay\": 0, \"allowance\": 0, \"transport\": 0, "
										  + "\"otherdeduction\": 0, \"totalpay\": 0, \"payperiod\": \"string\", \"paymentdate\": \"2023-04-28\", "
										  + "\"mid\": 0, \"manager_epf\": 0, \"manager_socso\": 0, \"manager_eis\": 0, \"employer_epf\": 0, "
										  + "\"employer_socso\": 0, \"employer_eis\": 0, \"mtd_pcb\": 0}";
	
	@Autowired
	private PayrollManagerService payrollManagerService;

	@Operation(summary="Get manager payroll list")
	@GetMapping
	public ResponseEntity<PageInfo<PayrollManager>> listPayrollManager(@RequestParam(value="page", required=true) int page, 
			  													@RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(payrollManagerService.getListByPage(page, offset));
	}
	
	@Operation(summary="Get payroll list by manager id")
	@GetMapping("/{id}/all")
	public ResponseEntity<List<PayrollManager>> listPayrollManagerByMId(@Parameter(description="Manager id") @PathVariable("id") int mid) {
		return ResponseEntity.ok(payrollManagerService.getListByMId(mid));
	}
	
	@Operation(summary="Get manager payroll info by id")
	@GetMapping("/{id}")
	public ResponseEntity<PayrollManager> getById(@Parameter() @PathVariable("id") int prMgrId) {
		return ResponseEntity.ok(payrollManagerService.getById(prMgrId));
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
	   			 schema= @Schema(implementation = PayrollManager.class),
	   			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE)})})
	@PostMapping
	public ResponseEntity<Integer> insert(@RequestBody PayrollManager payrollManager) {
		Integer status = payrollManagerService.insert(payrollManager);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@Operation(summary="Update manager payroll info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for update success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for update fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@PutMapping("/{id}")
	public ResponseEntity<Integer> update(@RequestBody PayrollManager payrollManager) {
		Integer status = payrollManagerService.update(payrollManager);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@Operation(summary="Delete manager payroll info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for delete success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for delete fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int prMgrId) {
		Integer status = payrollManagerService.delete(prMgrId);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
}
