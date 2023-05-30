package com.company.payroll.controller.api;

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

import com.company.payroll.model.Manager;
import com.company.payroll.service.StaffDetailsService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {
	private static final String VALUE_ONE = "{\"fullname\": \"string\", \"gender\": \"string\", "
										  + "\"age\": 0, \"martialstatus\": \"string\", \"education\": \"string\", "
										  + "\"address\": \"string\", \"state\": \"string\", \"country\": \"string\", \"phone\": \"string\", "
										  + "\"datehired\": \"2023-04-28\", \"attachment\": \"string\", \"imgUser\": \"string\", \"deptno\": 0, "
										  + "\"titleno\": 0, \"bid\": 0, \"sid\": 0, \"date_of_birth\": \"2023-04-28\", "
										  + "\"company_email\": \"string\"}";
	
	@Autowired
	private StaffDetailsService staffDetailsService;
	
	@Operation(summary="Get manager list")
	@GetMapping
	public ResponseEntity<PageInfo<Manager>> listManager(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffDetailsService.listManager(page, offset));
	}
	
	@Operation(summary="Get manager info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Manager>> getById(@PathVariable("id") int mid) {
		return ResponseEntity.ok(staffDetailsService.findManagerById(mid));
	}
	
	@Operation(summary="Insert manager info",
			   responses= {@ApiResponse(responseCode="200",
					   					description="Value return 1 for insert success.",
					   					content=@Content(examples= {@ExampleObject(value="1")})),
					   	   @ApiResponse(responseCode="403",
					   			   		description="Value return 0 for insert fail.",
					   			   		content=@Content(examples= {@ExampleObject(value="0")}))})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
		   	 content= {@Content(mediaType="application/json", 
			 schema= @Schema(implementation = Manager.class),
			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE)})})
	@PostMapping
	public ResponseEntity<Manager> insert(@RequestBody Manager manager) {
		return ResponseEntity.ok(staffDetailsService.registerManager(manager));
	}
	
	@Operation(summary="Update manager info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for update success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for update fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@PutMapping("/{id}")
	public ResponseEntity<Manager> update(@RequestBody Manager manager) {
		return ResponseEntity.ok(staffDetailsService.updateManager(manager));
	}

	@Operation(summary="Delete manager info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for delete success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for delete fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int id) {
		Integer status = staffDetailsService.deleteManager(id);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}

//	@GetMapping("/list/count/all")
//	public Integer countManager() {
//		return managerService.countManager();
//	}

//	@GetMapping("/list/count/active")
//	public Integer countAvailableManager() {
//		return managerService.countAvailableManager();
//	}
}
