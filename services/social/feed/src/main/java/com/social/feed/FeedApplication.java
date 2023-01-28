package com.social.feed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableResourceServer
@ComponentScan("com.social.*")
@EnableJpaRepositories(basePackages = "com.social.data.repository")
@EntityScan(basePackages = "com.social.data.model")
public class FeedApplication {
	public static void main(String[] args) {
		SpringApplication.run(FeedApplication.class, args);
	}
}
