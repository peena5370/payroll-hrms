package com.company.payroll.controller.api;

import java.util.List;
import java.util.Optional;

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

import com.company.payroll.model.Leave;
import com.company.payroll.service.StaffApplicationService;
import com.github.pagehelper.PageInfo;

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
	
	@Autowired
	private StaffApplicationService staffApplicationService;

	@Operation(summary="Get leave list")
	@GetMapping
	public ResponseEntity<PageInfo<Leave>> listLeave(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffApplicationService.listLeave(page, offset));
	}
	
	@Operation(summary="Get leave list by employee id")
	@GetMapping("/{id}/all")
	public ResponseEntity<Optional<List<Leave>>> listByEId(@Parameter(description="employee id") @PathVariable("id") int eid) {
		return ResponseEntity.ok(staffApplicationService.findLeaveByEId(eid));
	}
	
	@Operation(summary="Get leave info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Leave>> getById(@Parameter() @PathVariable("id") int lid) {
		return ResponseEntity.ok(staffApplicationService.findLeaveById(lid));
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
	@PostMapping
	public ResponseEntity<Leave> insert(@RequestBody Leave leave) {
		return ResponseEntity.ok(staffApplicationService.insertLeave(leave));
	}
	
	@Operation(summary="Update leave info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for update success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for update fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@PutMapping("/{id}")
	public ResponseEntity<Leave> update(@RequestBody Leave leave) {
		return ResponseEntity.ok(staffApplicationService.updateLeave(leave));
	}
	
	@Operation(summary="Delete leave info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for delete success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for delete fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@Parameter() @PathVariable("id") int lid) {
		Integer status = staffApplicationService.deleteLeave(lid);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
}
