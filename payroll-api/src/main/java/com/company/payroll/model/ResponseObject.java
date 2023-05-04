package com.company.payroll.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ResponseObject {
	@Schema(description="Response Http code", accessMode=Schema.AccessMode.READ_ONLY)
	private int code;
	@Schema(description="Response message", accessMode=Schema.AccessMode.READ_ONLY)
	private String msg;
}
