package com.patika.kredinbizdeservice.configuration;

import com.patika.kredinbizdeservice.service.IUserService;
import com.patika.kredinbizdeservice.service.UserService;
import com.patika.kredinbizdeservice.service.UserService2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public IUserService userService4() {
        return new UserService2();
    }
}
