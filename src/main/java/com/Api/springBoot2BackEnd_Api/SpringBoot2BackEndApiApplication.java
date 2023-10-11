package com.Api.springBoot2BackEnd_Api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Api.springBoot2BackEnd_Api.domain.Categoria;
import com.Api.springBoot2BackEnd_Api.domain.Produto;
import com.Api.springBoot2BackEnd_Api.repositories.CategoriaRepository;
import com.Api.springBoot2BackEnd_Api.repositories.ProdutoRepository;

@SpringBootApplication
public class SpringBoot2BackEndApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2BackEndApiApplication.class, args);
		}
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Override
	public void run(String... args) throws Exception {
		// Adicionar dados as tabelas ctegoria e produtos
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritorio");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2)); // ,cat3,cat4,cat5,cat6,cat7,cat8));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
	}	

}
