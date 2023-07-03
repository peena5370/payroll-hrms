package com.company.payroll.service;

import com.company.payroll.model.StaffAttendance;
import com.github.pagehelper.PageInfo;

import java.time.LocalDate;

public interface StaffAttendanceService {
    /**
     * Method of listing staff attendance.
     * @param staffId staff id
     * @param startDate query date start
     * @param endDate query date end
     * @param page page number
     * @param offset page data limit
     * @return List of Staff Attendance
     */
    PageInfo<StaffAttendance> getStaffAttendance(Integer staffId, LocalDate startDate, LocalDate endDate, int page, int offset);

    /**
     * Method used by staff for check in when it first comes to work on the day.
     * <p>Parameters that needed by staff attendance are: <b>attendanceDate, checkInOne, and staffId</b></p>
     * @param staffAttendance {@link StaffAttendance} object
     * @return Inserted row
     */
    Integer staffCheckIn(StaffAttendance staffAttendance);

    /**
     * Method used to update check in time for the following check in and check out status.
     * <p>Parameters that required for this method are: <b>staffId, attendanceDate</b></p>
     * <p>For the following parameter, use may switch it between <b>checkOutOne, checkInTwo, checkOutTwo, checkInThree, and checkOutThree</b></p>
     * @param staffAttendance
     * @return
     */
    Integer attendanceUpdate(StaffAttendance staffAttendance);
}
