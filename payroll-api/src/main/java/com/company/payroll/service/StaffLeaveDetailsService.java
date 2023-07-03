package com.company.payroll.service;

import com.company.payroll.model.StaffLeaveDetails;
import com.github.pagehelper.PageInfo;

public interface StaffLeaveDetailsService {
    /**
     * Method of listing staff leave details
     * @param page page number
     * @param offset page data limit
     * @return List of staff leave details
     */
    PageInfo<StaffLeaveDetails> list(int page, int offset);

    /**
     * Method of retrieve staff leave detail based of leave detail id
     * @param ldId leave detail id
     * @return {@link StaffLeaveDetails} object
     */
    StaffLeaveDetails findById(Integer ldId);

    /**
     * Method of updating staff leave detail based on leave detail id
     * @param staffLeaveDetails {@link StaffLeaveDetails} object
     * @return {@link StaffLeaveDetails} object after updated.
     */
    StaffLeaveDetails update(StaffLeaveDetails staffLeaveDetails);
}
