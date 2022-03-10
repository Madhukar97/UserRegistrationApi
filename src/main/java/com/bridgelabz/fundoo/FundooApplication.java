package com.bridgelabz.fundoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication (exclude = {SecurityAutoConfiguration.class })
@EnableScheduling
public class FundooApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundooApplication.class, args);
	}

}
