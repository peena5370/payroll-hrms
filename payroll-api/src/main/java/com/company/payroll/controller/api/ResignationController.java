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

import com.company.payroll.model.Resignation;
import com.company.payroll.service.ResignationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/resign")
public class ResignationController {
	private static final String VALUE_ONE = "{\"reason\": \"string\", \"resigndate\": \"2023-04-28T13:46:24.820Z\", "
										  + "\"resignstatus\": \"string\", \"attachment\": \"string\"}";
	
	@Autowired
	private ResignationService resignationService;

	@Operation(summary="Get resignation list")
	@GetMapping("/list")
	public ResponseEntity<List<Resignation>> listResignation() {
		return ResponseEntity.ok(resignationService.getList());
	}
	
	@Operation(summary="Get resign info by id")
	@GetMapping("/list/information/{id}")
	public ResponseEntity<Resignation> getById(@Parameter() @PathVariable("id") int id) {
		return ResponseEntity.ok(resignationService.getById(id));
	}
	
	@Operation(summary="Insert resign info",
			   responses= {@ApiResponse(responseCode="200",
					   					description="Value return 1 for insert success.",
					   					content=@Content(examples= {@ExampleObject(value="1")})),
					   	   @ApiResponse(responseCode="403",
					   			   		description="Value return 0 for insert fail.",
					   			   		content=@Content(examples= {@ExampleObject(value="0")}))})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			   	 content= {@Content(mediaType="application/json", 
	   			 schema= @Schema(implementation = Resignation.class),
	   			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE)})})
	@PostMapping("/insert")
	public ResponseEntity<Integer> insert(@RequestBody Resignation resignation) {
		Integer status = resignationService.insert(resignation);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@Operation(summary="Update resign info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for update success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for update fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> update(@RequestBody Resignation resignation) {
		Integer status = resignationService.update(resignation);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@Operation(summary="Delete resign info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for delete success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for delete fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> delete(@Parameter @PathVariable("id") int id) {
		Integer status = resignationService.delete(id);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
}
