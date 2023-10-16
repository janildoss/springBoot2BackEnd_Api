package com.Api.springBoot2BackEnd_Api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Api.springBoot2BackEnd_Api.domain.Endereco;

//@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
