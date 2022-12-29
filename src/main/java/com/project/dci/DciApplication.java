package com.project.dci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DciApplication {

	public static void main(String[] args) {
		SpringApplication.run(DciApplication.class, args);
	}

}
