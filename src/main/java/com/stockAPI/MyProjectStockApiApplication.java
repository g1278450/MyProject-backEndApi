package com.stockAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MyProjectStockApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyProjectStockApiApplication.class, args);
	}

}
