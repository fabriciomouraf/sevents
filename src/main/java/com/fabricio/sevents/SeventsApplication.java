package com.fabricio.sevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableJpaRepositories(basePackages = "com.fabricio.sevents.api.repository")
@EnableRedisRepositories(basePackages = "com.fabricio.sevents.api.core.redis.repository")
@SpringBootApplication
public class SeventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeventsApplication.class, args);
	}

}
