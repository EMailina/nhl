package com.league.nhl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@EntityScan(basePackages = "com.league.nhl")
@EnableJpaAuditing
public class NhlApplication {

	public static void main(String[] args) {
		SpringApplication.run(NhlApplication.class, args);
	}

}
