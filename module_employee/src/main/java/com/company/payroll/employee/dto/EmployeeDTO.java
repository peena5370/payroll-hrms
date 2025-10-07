package com.company.payroll.employee.dto;

import com.company.payroll.employee.constant.EmploymentStatus;
import com.company.payroll.employee.constant.Gender;

import java.time.LocalDate;

/**
 * 1. first_name
 * 2. last_name
 * 3. date_of_birth
 * 4. gender
 * 5. email
 * 6. phone_number
 * 7. address_line_1
 * 8. address_line_2
 * 9. city
 * 10. state_province
 * 11. postal_code
 * 12. country
 * 13. hire_date
 * 14. employment_status
 * 15. job_title
 * 16. manager_id
 */
public record EmployeeDTO(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String icNumber,
        Gender gender,
        String email,
        String phoneNumber,
        String addressLine1,
        String addressLine2,
        String city,
        String state,
        String country,
        LocalDate hireDate,
        EmploymentStatus employmentStatus,
        String jobTitle,
        Long managerId
) {
}
