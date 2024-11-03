package com.league.nhl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EntityScan(basePackages = "com.league.nhl")
@EnableJpaAuditing
public class NhlApplication {

	public static void main(String[] args) {
		SpringApplication.run(NhlApplication.class, args);
	}

}
