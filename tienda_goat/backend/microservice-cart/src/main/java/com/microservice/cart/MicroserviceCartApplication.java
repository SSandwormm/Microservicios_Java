package com.microservice.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients(basePackages = "com.microservice.cart.client")
public class MicroserviceCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCartApplication.class, args);
	}

}
