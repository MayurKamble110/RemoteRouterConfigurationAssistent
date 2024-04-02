package com.fs.remoterouterconfigurationassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fs.remoterouterconfigurationassistant.databases",
							   "com.fs.remoterouterconfigurationassistant.api",
							   "com.fs.remoterouterconfigurationassistant.flaskserver"})
public class RemoteRouterConfigurationAssistantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemoteRouterConfigurationAssistantApplication.class, args);
	}

}
