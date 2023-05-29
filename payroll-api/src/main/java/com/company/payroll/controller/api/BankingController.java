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

import com.company.payroll.model.BankingInfo;
import com.company.payroll.service.BankingService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/banking")
public class BankingController {
	private static final String VALUE_ONE = "{\"bank_name\": \"string\", \"account_bank\": 0, "
										  + "\"account_income_tax\": \"string\", \"account_epf\": 0}";
	
	@Autowired
	private BankingService bankingService;

	@Operation(summary="Get banking list")
	@GetMapping
	public ResponseEntity<PageInfo<BankingInfo>> listBankingInfo(@RequestParam(value="page", required=true) int page, 
			  												@RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(bankingService.list(page, offset));	
	}
	
	@Operation(summary="Get banking info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<BankingInfo>> getById(@Parameter(description="Banking id") @PathVariable("id") int bid) {
		return ResponseEntity.ok(bankingService.findById(bid));
	}
	
//	@Operation(summary="Add banking info",
//			   responses= {@ApiResponse(responseCode="200",
//					   					description="Value return 1 for insert success.",
//					   					content=@Content(examples= {@ExampleObject(value="1")})),
//					   	   @ApiResponse(responseCode="403",
//					   			   		description="Value return 0 for insert fail.",
//					   			   		content=@Content(examples= {@ExampleObject(value="0")}))})
//	@io.swagger.v3.oas.annotations.parameters.RequestBody(
//			   	 content= {@Content(mediaType="application/json", 
//	   			 schema= @Schema(implementation = BankingInfo.class),
//	   			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE)})})
//	@PostMapping
//	public ResponseEntity<Integer> insert(@RequestBody BankingInfo bankingInfo) {
//		Integer status = bankingService.insert(bankingInfo);
//		if(status==0) {
//			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
//		}
//		
//		return ResponseEntity.ok(status);
//	}
	
	@Operation(summary="Update banking info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for update success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for update fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@PutMapping("/{id}")
	public ResponseEntity<BankingInfo> update(@RequestBody BankingInfo bankingInfo) {
//		BankingInfo status = bankingService.update(bankingInfo);
//		if(status==0) {
//			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
//		}
		
		return ResponseEntity.ok(bankingService.update(bankingInfo));
	}
	
//	@Operation(summary="Delete banking info.",
//			   responses= {@ApiResponse(responseCode="200",
//										description="Value return 1 for delete success.",
//										content=@Content(examples= {@ExampleObject(value="1")})),
//					   	  @ApiResponse(responseCode="403",
//					   	  				description="Value return 0 for delete fail.",
//					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Integer> delete(@Parameter(description="Banking id") @PathVariable("id") int bid) {
//		Integer status = bankingService.delete(bid);
//		if(status==0) {
//			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
//		}
//		
//		return ResponseEntity.ok(status);
//	}
}
