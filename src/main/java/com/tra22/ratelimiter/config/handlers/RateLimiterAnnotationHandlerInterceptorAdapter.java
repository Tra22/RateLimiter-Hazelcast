package com.tra22.ratelimiter.config.handlers;

import com.tra22.ratelimiter.config.annotation.RateLimiter;
import com.tra22.ratelimiter.exception.BucketRateLimiterException;
import com.tra22.ratelimiter.payloads.RateLimiterKey;
import com.tra22.ratelimiter.utils.RateLimiterUtils;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.grid.hazelcast.HazelcastProxyManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;
import java.util.Optional;

@Component
public class RateLimiterAnnotationHandlerInterceptorAdapter implements HandlerInterceptor {
    private final HazelcastProxyManager<RateLimiterKey> proxyManager;
    public RateLimiterAnnotationHandlerInterceptorAdapter(HazelcastProxyManager<RateLimiterKey> proxyManager) {
        this.proxyManager = proxyManager;
    }
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {

        if (handler instanceof HandlerMethod handlerMethod) {

            //if into handlerMethod is present RateLimiter or RateLimiters annotation, we get it, if not, we get empty Optional
            Optional<List<RateLimiter>> rateLimiters = RateLimiterUtils.getRateLimiters(handlerMethod);

            if (rateLimiters.isPresent()) {
                //Get path from RequestMapping annotation(respectively we can get annotations such: GetMapping, PostMapping, PutMapping, DeleteMapping, because all of than annotations are extended from RequestMapping)
                RequestMapping requestMapping = handlerMethod.getMethodAnnotation(RequestMapping.class);

                //To get unique key we use bundle of 2-x values: path from RequestMapping and user id
                assert requestMapping != null;
                RateLimiterKey key = new RateLimiterKey(RateLimiterUtils.getUniqueKeyName(request), requestMapping.value());
                //Further we set key in proxy to get Bucket from cache or create a new Bucket
                Bucket bucket = this.proxyManager.builder().build(key, () -> RateLimiterUtils.rateLimiterAnnotationsToBucketConfiguration(rateLimiters.get()));

                ConsumptionProbe consumptionProbe =  bucket.tryConsumeAndReturnRemaining(1);
                //Try to consume token, if we don’t do that, we throw custom exception
                if (!consumptionProbe.isConsumed()) {
                    throw new BucketRateLimiterException("RateLimiter is applied.",  consumptionProbe);
                }
            }
        }
        return true;
    }
}
