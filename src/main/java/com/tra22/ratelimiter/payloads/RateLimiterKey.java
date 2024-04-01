package com.tra22.ratelimiter.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class RateLimiterKey implements Serializable {
    private String userId;
    private String[] uri;
}