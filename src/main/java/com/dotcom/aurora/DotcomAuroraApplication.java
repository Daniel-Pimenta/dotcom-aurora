package com.dotcom.aurora;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DotcomAuroraApplication {

	private static final Logger log = LoggerFactory.getLogger(DotcomAuroraApplication.class);
	
	public static void main(String[] args) {
		log.info("DotcomAuroraApplication... Iniciando");
		SpringApplication.run(DotcomAuroraApplication.class, args);
	}

}

