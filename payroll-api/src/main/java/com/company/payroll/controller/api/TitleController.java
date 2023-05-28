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

import com.company.payroll.model.Title;
import com.company.payroll.service.TitleService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/title")
public class TitleController {
	private static final String VALUE_ONE = "{\"titlename\": \"string\", \"titledesc\": \"string\"}";
	
	@Autowired
	private TitleService titleService;

	@Operation(summary="Get title list")
	@GetMapping
	public ResponseEntity<PageInfo<Title>> listTitleByPage(@RequestParam(value="page", required=true) int page, 
														  @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(titleService.getListByPage(page, offset));
	}

	@Operation(summary="Get title info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Title>> getByTitleno(@Parameter() @PathVariable("id") int titleno) {
		return ResponseEntity.ok(titleService.getByTitleno(titleno));
	}
	
	@Operation(summary="Insert title info",
			   responses= {@ApiResponse(responseCode="200",
					   					description="Value return 1 for insert success.",
					   					content=@Content(examples= {@ExampleObject(value="1")})),
					   	   @ApiResponse(responseCode="403",
					   			   		description="Value return 0 for insert fail.",
					   			   		content=@Content(examples= {@ExampleObject(value="0")}))})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			   	 content= {@Content(mediaType="application/json", 
	   			 schema= @Schema(implementation = Title.class),
	   			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE)})})
	@PostMapping
	public ResponseEntity<Title> insert(@RequestBody Title title) {
		Title status = titleService.insert(title);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(status);
	}
	
	@Operation(summary="Update title info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for update success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for update fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@PutMapping("/{id}")
	public ResponseEntity<Title> update(@RequestBody Title title) {
		Title status = titleService.update(title);
		
		return ResponseEntity.ok(status);
	}
	
	@Operation(summary="Delete title info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for delete success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for delete fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@Parameter() @PathVariable("id") int titleno) {
		Integer status = titleService.delete(titleno);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
}
