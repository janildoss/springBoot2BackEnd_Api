package com.Api.springBoot2BackEnd_Api.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.Api.springBoot2BackEnd_Api.services.DBService;
import com.Api.springBoot2BackEnd_Api.services.EmailService;
import com.Api.springBoot2BackEnd_Api.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	@Autowired
	private DBService dbservice;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbservice.instantiateTestDatabase();
		return true;
	}
	
	@Bean // disponivel como um componente
	public EmailService emailService() {
		return new MockEmailService();
	}
}
