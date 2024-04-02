package com.tra22.ratelimiter.controllers;

import com.tra22.ratelimiter.config.annotation.RateLimiter;
import com.tra22.ratelimiter.config.annotation.RateLimiters;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class ExampleController   {
    @RateLimiters({
        @RateLimiter(timeUnit = TimeUnit.SECONDS, timeValue = 10, restriction = 2),
        @RateLimiter(timeValue = 10, restriction = 5)
    })
    @GetMapping("/example")
    public ResponseEntity<Object> example() {
        return ResponseEntity.ok("Ok");
    }
}