package com.company.payroll.util.util;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageHelper {

    private PageHelper() {

    }

    /**
     * Helper method which use to generate data sort object with their sort field and sort sequence which used by hibernate
     * @param columnMap A map of key value pair with the key(frontend-field-name), value(backend-field-name)
     * @param sortField String of sort field combination like frontendFieldName1-asc,frontendFieldName2-desc with comma separated
     * @return Sort object
     */
    public static Sort buildSortFromSortField(Map<String, String> columnMap, String sortField) {
        if (sortField == null || sortField.trim().isEmpty()) {
            return Sort.unsorted();
        }

        List<Sort.Order> orders = new ArrayList<>();
        String[] sortParams = sortField.split(",");

        for (String param : sortParams) {
            String[] fieldParts = param.trim().split("-");
            if (fieldParts.length == 2) {
                String frontendFieldName = fieldParts[0];
                String entityFieldName = columnMap.get(frontendFieldName);

                if (entityFieldName != null) {
                    Sort.Direction direction = fieldParts[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
                    orders.add(new Sort.Order(direction, entityFieldName));
                } else {
                    System.err.println("Unknown sort parameter: " + frontendFieldName);
                }
            }
        }

        return orders.isEmpty() ? Sort.unsorted() : Sort.by(orders);
    }
}
