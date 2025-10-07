package com.company.payroll.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record JwkKey(
        String alg,
        String kty,

        // use for keytype RSA
        String n,
        String e,

        // use for keytype EC
        String x,
        String y,
        String crv,

        @JsonProperty("x5t")
        String x5t,

        @JsonProperty("x5c")
        List<String> xtc,

        String kid,
        String use
) { }
