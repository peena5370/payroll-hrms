package com.company.payroll.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class FileAttachment {
	private Integer fId;
	
	@JsonProperty("filename")
	private String fileName;
	
	@JsonProperty("size")
	private long fileSize;
	
	@JsonProperty("path")
	private String attachmentPath;
	
	private Integer mId;
	
	private Integer eId;
}
