package com.company.payroll;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
public class SpringBootPayrollApplication extends SpringBootServletInitializer {

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(SpringBootPayrollApplication.class);
//	}

	public static void main(String[] args) {
//		SpringApplication.run(SpringBootPayrollApplication.class, args);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		String format = sdf.format(new Date(System.currentTimeMillis()));
		LocalDateTime ldt = LocalDateTime.of(2023, 10, 20, 17, 00, 30);
		
		
		System.out.println("custom date: " + ldt);
		
		java.util.Date newDate = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		
		System.out.println("new date: " + newDate);
		
		System.out.println("new format date: " + sdf.format(newDate));
		

        System.out.println("format date: " + format);
        
        System.out.println("instance: " + LocalDateTime.now());
        
        Calendar startRange = Calendar.getInstance();
        startRange.add(Calendar.DATE, -1);
        startRange.set(Calendar.HOUR_OF_DAY, 0);
        startRange.set(Calendar.MINUTE, 0);
        startRange.set(Calendar.SECOND, 0);
        startRange.set(Calendar.MILLISECOND, 0);

        Calendar endRange = Calendar.getInstance();
        endRange.set(Calendar.HOUR_OF_DAY, 23);
        endRange.set(Calendar.MINUTE, 59);
        endRange.set(Calendar.SECOND, 59);
        endRange.set(Calendar.MILLISECOND, 997);
        
        System.out.println("start: " + LocalDateTime.ofInstant(startRange.toInstant(), startRange.getTimeZone().toZoneId()) + 
        		"\tend: " + LocalDateTime.ofInstant(endRange.toInstant(), endRange.getTimeZone().toZoneId()));
        
	}
}