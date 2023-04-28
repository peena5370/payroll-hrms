package com.company.payroll.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Resignation {
    private Integer resignId;

    private String reason;

    private Date resigndate;

    @Schema(description="Resign status: 0=ongoing, 1=completed, 2=rejected")
    private Byte resignstatus;

    private String attachment;
}