package com.dogecoin.cryptoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class CryptoapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoapiApplication.class, args);
	}

}
