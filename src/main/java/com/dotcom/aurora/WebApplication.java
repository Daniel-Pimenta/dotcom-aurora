package com.dotcom.aurora;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {
	
	private static final Logger log = LoggerFactory.getLogger(WebApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebApplication.class);
	}

	public static void main(String[] args) throws Exception {
		log.info("WebApplication... Iniciando");
		SpringApplication.run(WebApplication.class, args);
	}
}