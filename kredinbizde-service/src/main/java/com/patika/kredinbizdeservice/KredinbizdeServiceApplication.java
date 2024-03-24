package com.patika.kredinbizdeservice;

import com.patika.kredinbizdeservice.controller.UserController;
import com.patika.kredinbizdeservice.model.User;
import com.patika.kredinbizdeservice.service.IUserService;
import com.patika.kredinbizdeservice.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class KredinbizdeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KredinbizdeServiceApplication.class, args);

        //IUserService userService = new UserService();

        //IUserService userService2 = new UserService2();

        //UserController userController = new UserController(userService);
       // userController.create(new User());
    }

    /*
    @Bean
    @Scope("prototype")
    public IUserService userService() {
        return new UserService();
    }*/

}
