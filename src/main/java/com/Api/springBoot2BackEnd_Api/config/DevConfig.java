package com.Api.springBoot2BackEnd_Api.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.Api.springBoot2BackEnd_Api.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {
	@Autowired
	private DBService dbservice;
	       
	//Creiar variavel do tipo especifico para controlar orientar o jpa 
	//na criação do banco...create, update, none
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if(!"create".equals(strategy)) {
			return false;
		}
		
		dbservice.instantiateTestDatabase();
		return true;
	}
}
