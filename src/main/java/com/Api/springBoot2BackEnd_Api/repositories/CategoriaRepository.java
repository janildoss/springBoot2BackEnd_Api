package com.Api.springBoot2BackEnd_Api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Api.springBoot2BackEnd_Api.domain.Categoria;

//@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	

}
