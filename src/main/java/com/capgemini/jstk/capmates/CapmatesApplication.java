package com.capgemini.jstk.capmates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class CapmatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapmatesApplication.class, args);

	}
}
