package ru.ifmo.soa.lab4.primary_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PrimaryBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimaryBackApplication.class, args);
	}

}
