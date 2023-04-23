package com.company.payroll.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
	
	/** 
	 * Convert datetime into timestamp
	 * @param	ldt
	 * @return	timestamp
	 * */
	public static Timestamp dateTimetoTimestamp(LocalDateTime ldt) {
		return Timestamp.valueOf(ldt);	
	}
	
	/** 
	 * Convert String with formatted(yyyy-MM-dd) date.
	 * @param	date
	 * @return	localdate
	 * */
	public static LocalDate stringToFormatDate(String date) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

}
