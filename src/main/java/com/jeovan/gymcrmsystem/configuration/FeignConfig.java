package com.jeovan.gymcrmsystem.configuration;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@RequiredArgsConstructor
public class FeignConfig {

    private final HttpServletRequest request;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String header = request.getHeader("Authorization");
            if (StringUtils.hasText(header)) {
                requestTemplate.header("Authorization", header);
            }
        };
    }
}
