package com.tra22.ratelimiter.constant;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RateLimiterHeader {
    RATE_LIMITER_WAIT_FOR_REFILL("RateLimiter-Wait_For_Refill"), RATE_LIMITER_RESET("RateLimiter-Reset"), RATE_LIMITER_REMAINING("RateLimiter-Remaining");
    public final String name;
}
