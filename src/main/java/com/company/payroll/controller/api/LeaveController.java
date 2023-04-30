package com.company.payroll.controller.api;

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

import com.company.payroll.model.Leave;
import com.company.payroll.service.LeaveService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {
	private static final String VALUE_ONE = "{\"leavetype\": \"string\", \"reason\": \"string\", "
										  + "\"applicationdate\": \"2023-04-28\", \"approveddate\": \"2023-04-28\", \"mid\": 0, "
										  + "\"eid\": 0, \"reference_number\": \"string\", \"date_start\": \"2023-04-28T12:49:19.260Z\", "
										  + "\"date_end\": \"2023-04-28T12:49:19.260Z\", \"status\": \"string\"}";
	
	private LeaveService leaveService;
	
	public LeaveController(LeaveService leaveService) {
		this.leaveService = leaveService;
	}
	
	@Operation(summary="Get leave list")
	@GetMapping("/list")
	public ResponseEntity<List<Leave>> listLeave() {
		return ResponseEntity.ok(leaveService.getList());
	}
	
	@Operation(summary="Get leave list by employee id")
	@GetMapping("/{id}/list")
	public ResponseEntity<List<Leave>> listByEId(@Parameter(description="employee id") @PathVariable("id") int eid) {
		return ResponseEntity.ok(leaveService.getListByEId(eid));
	}
	
	@Operation(summary="Get leave info by id")
	@GetMapping("/list/information/{id}")
	public ResponseEntity<Leave> getById(@Parameter() @PathVariable("id") int lid) {
		return ResponseEntity.ok(leaveService.getById(lid));
	}
	
	@Operation(summary="Add leave info",
			   responses= {@ApiResponse(responseCode="200",
					   					description="Value return 1 for insert success.",
					   					content=@Content(examples= {@ExampleObject(value="1")})),
					   	   @ApiResponse(responseCode="403",
					   			   		description="Value return 0 for insert fail.",
					   			   		content=@Content(examples= {@ExampleObject(value="0")}))})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			   	 content= {@Content(mediaType="application/json", 
	   			 schema= @Schema(implementation = Leave.class),
	   			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE)})})
	@PostMapping("/insert")
	public ResponseEntity<Integer> insert(@RequestBody Leave leave) {
		Integer status = leaveService.insert(leave);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@Operation(summary="Update leave info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for update success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for update fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> update(@RequestBody Leave leave) {
		Integer status = leaveService.update(leave);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@Operation(summary="Delete leave info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for delete success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for delete fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> delete(@Parameter() @PathVariable("id") int lid) {
		Integer status = leaveService.delete(lid);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
}
