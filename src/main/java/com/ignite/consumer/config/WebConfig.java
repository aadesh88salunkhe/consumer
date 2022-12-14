package com.ignite.consumer.config;

import com.ignite.consumer.interceptor.RequestProcessingTimeInterceptor;
import com.ignite.consumer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private UserRepository userRepository;
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new RequestProcessingTimeInterceptor(userRepository)).addPathPatterns("/**");
    }
}
