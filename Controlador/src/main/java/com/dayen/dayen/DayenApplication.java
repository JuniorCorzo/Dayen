package com.dayen.dayen;

import com.dayen.dayen.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class DayenApplication {

	public static void main(String[] args) {
		SpringApplication.run(DayenApplication.class, args);
	}

}
