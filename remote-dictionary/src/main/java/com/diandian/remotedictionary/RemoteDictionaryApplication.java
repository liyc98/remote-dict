package com.diandian.remotedictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RemoteDictionaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemoteDictionaryApplication.class, args);
	}
}
