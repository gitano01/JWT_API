package com.secjwt;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.efv.*")
public class JWT_UsersApplication {

	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(JWT_UsersApplication.class);
		
		Map<String,Object> map = new HashMap<>();
		map.put("server.port", "8003");
		map.put("server.servlet.context-path", "/api");
		app.setDefaultProperties(map);
		app.run(args);
		
	}

}
