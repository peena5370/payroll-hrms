package com.company.payroll.user.cron;

import com.company.payroll.user.dto.JwkKey;
import com.company.payroll.user.dto.JwkUriResponse;
import com.company.payroll.user.service.PayrollCoreService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class JwkCacheService {
    // Define jwk cache store name
    public static final String JWK_URI_CACHE = "jwkUriCache";

    private final PayrollCoreService payrollCoreService;

    public JwkCacheService(PayrollCoreService payrollCoreService) {
        this.payrollCoreService = payrollCoreService;
    }

    @PostConstruct
    public void initLoad() {
        // Calls the scheduled method once to load the initial value
        log.info("Starting initial load of JWK URI cache.");
        this.refreshJwkUriCache();
    }

    @Scheduled(fixedRateString = "${security.jwk.refresh-rate-ms:14400000}")
    @CachePut(value = JWK_URI_CACHE, key = "'jwkUri'")
    public List<JwkKey> refreshJwkUriCache() {
        try {
            JwkUriResponse response = payrollCoreService.getJwkUri();

            log.info("Successfully refreshed JWK URI.");


            return response.data().keys();
        } catch (RuntimeException e) {
            log.error("FATAL: Failed to refresh JWK URI cache.", e);

            return null;
        }
    }

    @Cacheable(value = JWK_URI_CACHE, key = "'jwkUri'")
    public List<JwkKey> getCachedJwkUri() {
        return refreshJwkUriCache();
    }
}
