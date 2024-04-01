package com.tra22.ratelimiter.config;

import com.tra22.ratelimiter.config.handlers.RateLimiterAnnotationHandlerInterceptorAdapter;
import com.tra22.ratelimiter.payloads.RateLimiterKey;
import io.github.bucket4j.grid.hazelcast.HazelcastProxyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigHazelcastRateLimiter implements WebMvcConfigurer {
    @Autowired
    private HazelcastProxyManager<RateLimiterKey> hazelcastProxyManager;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RateLimiterAnnotationHandlerInterceptorAdapter(hazelcastProxyManager));
    }
}