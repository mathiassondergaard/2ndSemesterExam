package com.alexnmat.exam.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
@Author: MSN & AFC
 */

@Configuration
public class SecurityConfiguration implements WebMvcConfigurer {

    //Used for encoding passwords
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
