package com.siparis.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.siparis"})
@ComponentScan(basePackages = {"com.siparis"})
@EnableJpaRepositories(basePackages = {"com.siparis"})
@SpringBootApplication
public class StarterApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(StarterApplication.class, args);
	}

}
