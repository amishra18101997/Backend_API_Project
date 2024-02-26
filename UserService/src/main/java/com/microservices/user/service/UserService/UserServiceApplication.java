package com.microservices.user.service.UserService;

import com.microservices.user.service.UserService.controllers.UserController;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.logging.Logger;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UserServiceApplication {



	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
