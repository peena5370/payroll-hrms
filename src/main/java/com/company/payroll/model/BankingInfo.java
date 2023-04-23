package com.company.payroll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author leeshengxian
 * Updated at 22 Apr 2023
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class BankingInfo {
    private Integer bId;

    private String bankname;

    private Long accountnum;

    private String incometaxno;

    private Integer epfno;
	
	public BankingInfo(String bankname, long accountnum, String incometaxno, Integer epfno) {
		this.bankname = bankname;
		this.accountnum = accountnum;
		this.incometaxno = incometaxno;
		this.epfno = epfno;
	}
}