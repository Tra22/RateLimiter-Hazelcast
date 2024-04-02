package com.tra22.ratelimiter.exception;

import com.tra22.ratelimiter.constant.RateLimiterHeader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BucketRateLimiterException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public ResponseEntity<?> bucketRateLimiterExceptionHandler(BucketRateLimiterException exception){
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .headers(httpHeaders ->  {
                    httpHeaders.set(RateLimiterHeader.RATE_LIMITER_WAIT_FOR_REFILL.name, String.valueOf(exception.getConsumptionProbe().getNanosToWaitForRefill()));
                    httpHeaders.set(RateLimiterHeader.RATE_LIMITER_RESET.name, String.valueOf(exception.getConsumptionProbe().getNanosToWaitForReset()));
                    httpHeaders.set(RateLimiterHeader.RATE_LIMITER_REMAINING.name, String.valueOf(exception.getConsumptionProbe().getRemainingTokens()));
                })
                .body(exception.getMessage());
    }
}
