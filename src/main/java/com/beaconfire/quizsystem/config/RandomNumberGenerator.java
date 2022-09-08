package com.beaconfire.quizsystem.config;

import com.beaconfire.quizsystem.controller.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.Random;

@Configuration
public class RandomNumberGenerator {

    @Bean
    public Random getRandom(){
        return new Random();
    }

    @Bean
    public LoginInterceptor getLoginInterceptor(){
        return new LoginInterceptor();
    }
}
