package com.company.payroll.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/promotion")
public class PromotionController {

    @GetMapping
    public ResponseEntity<Object> getEmployeePromotionsInfoByOffsetLimitWithOrderField
            (@RequestParam(value = "offset", required = false, defaultValue = "0") String offset,
             @RequestParam(value = "limit", required = false, defaultValue = "5") String limit,
             @RequestParam(value = "field", required = false, defaultValue = "promotionId-asc") String field) {
        return ResponseEntity.ok("TODO");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeePromotionInfoById(@PathVariable("id") String promotionId) {
        return ResponseEntity.ok("TODO");
    }

    @PostMapping
    public ResponseEntity<Object> createEmployeePromotion(@RequestBody Object obj) {
        return ResponseEntity.ok("TODO");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmployeePromotionInfoById(@PathVariable("id") String promotionId, @RequestBody Object obj) {
        return ResponseEntity.ok("TODO");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployeePromotionInfoById(@PathVariable("id") String promotionId) {
        return ResponseEntity.ok("TODO");
    }
}
