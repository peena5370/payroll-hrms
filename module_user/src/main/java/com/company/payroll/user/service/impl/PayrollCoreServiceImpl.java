package com.company.payroll.user.service.impl;

import com.company.payroll.user.dto.JwkUriResponse;
import com.company.payroll.user.service.PayrollCoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
@Service
public class PayrollCoreServiceImpl implements PayrollCoreService {
    private final WebClient client;
    private static final String JWK_URI_URL = "/core/json/jwk_uri";

    public PayrollCoreServiceImpl(@Qualifier("payrollCoreClientBuilder") WebClient client) {
        this.client = client;
    }

    public JwkUriResponse getJwkUri() {
        JwkUriResponse response = null;
        try {
            response = client.post()
                    .uri(JWK_URI_URL)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            ClientResponse::createException
                    )
                    .bodyToMono(JwkUriResponse.class)
                    .block();

            if (response == null) {
                throw new IllegalStateException("Received null response from JWK URI endpoint.");
            }

            log.info("PayrollCoreService Successfully fetched JWK URI.");
        } catch (WebClientResponseException e) {
            log.error("PayrollCoreService Error fetching JWK URI. Status: " + e.getStatusCode() + ", Body: " + e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("PayrollCoreService An unexpected error occurred: " + e.getMessage());
        }

        return response;
    }
}
