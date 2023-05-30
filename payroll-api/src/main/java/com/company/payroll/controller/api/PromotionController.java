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

import com.company.payroll.model.Promotion;
import com.company.payroll.service.StaffMiscellaneousService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/promotion")
public class PromotionController {
	private static final String VALUE_ONE = "{\"currentsalary\": 0, \"promotesalary\": 0, \"promotedate\": \"2023-04-28\", "
										  + "\"titleno\": 0, \"comment\": \"string\", \"mid\": 0, \"eid\": 0}";
	
	@Autowired
	private StaffMiscellaneousService staffMiscellaneousService;

	@Operation(summary="Get promotion list")
	@GetMapping
	public ResponseEntity<PageInfo<Promotion>> listPromotion(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffMiscellaneousService.listPromotion(page, offset));
	}
	
	@Operation(summary="Get promotion info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Promotion>> getById(@PathVariable("id") int pid) {
		return ResponseEntity.ok(staffMiscellaneousService.findPromotionById(pid));
	}
	
	@Operation(summary="Insert promotion info",
			   responses= {@ApiResponse(responseCode="200",
					   					description="Value return 1 for insert success.",
					   					content=@Content(examples= {@ExampleObject(value="1")})),
					   	   @ApiResponse(responseCode="403",
					   			   		description="Value return 0 for insert fail.",
					   			   		content=@Content(examples= {@ExampleObject(value="0")}))})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			   	 content= {@Content(mediaType="application/json", 
	   			 schema= @Schema(implementation = Promotion.class),
	   			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE)})})
	@PostMapping
	public ResponseEntity<Promotion> insert(@RequestBody Promotion promotion) {
		return ResponseEntity.ok(staffMiscellaneousService.insertPromotion(promotion));
	}
	
	@Operation(summary="Update promotion info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for update success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for update fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@PutMapping("/{id}")
	public ResponseEntity<Promotion> update(@RequestBody Promotion promotion) {
		return ResponseEntity.ok(staffMiscellaneousService.updatePromotion(promotion));
	}
	
	@Operation(summary="Delete promotion info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for delete success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for delete fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int pid) {
		Integer status = staffMiscellaneousService.deletePromotion(pid);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
}
