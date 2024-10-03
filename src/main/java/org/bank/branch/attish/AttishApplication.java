package org.bank.branch.attish;

import org.bank.branch.attish.security.jwt.KeyProperties;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableConfigurationProperties(KeyProperties.class)
public class AttishApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttishApplication.class, args);
	}

}
