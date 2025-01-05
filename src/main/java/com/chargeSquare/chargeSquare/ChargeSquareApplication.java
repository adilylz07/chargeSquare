package com.chargeSquare.chargeSquare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ChargeSquareApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChargeSquareApplication.class, args);
	}

}
