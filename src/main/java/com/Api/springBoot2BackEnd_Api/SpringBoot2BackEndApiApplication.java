package com.Api.springBoot2BackEnd_Api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Api.springBoot2BackEnd_Api.domain.Categoria;
import com.Api.springBoot2BackEnd_Api.repositories.CategoriaRepository;

@SpringBootApplication
public class SpringBoot2BackEndApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2BackEndApiApplication.class, args);
		}
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// Adicionar dados as tabelas ctegoria e produtos
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritorio");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2)); // ,cat3,cat4,cat5,cat6,cat7,cat8));
		
	}	

}
