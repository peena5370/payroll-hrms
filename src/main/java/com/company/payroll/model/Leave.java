package com.company.payroll.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Leave {
    private Integer lId;

    private String refnum;

    private String leavetype;

    private String reason;

    private LocalDate applicationdate;

    private LocalDateTime datefrom;

    private LocalDateTime dateto;

    private Byte leavestatus;

    private LocalDate approveddate;

    private Integer eId;

    private Integer mId;
}