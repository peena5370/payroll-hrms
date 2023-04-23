package com.company.payroll.model;

import java.util.Date;

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

    private Byte resignstatus;

    private String attachment;
}