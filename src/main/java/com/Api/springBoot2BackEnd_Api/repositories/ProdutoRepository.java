package com.Api.springBoot2BackEnd_Api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Api.springBoot2BackEnd_Api.domain.Produto;

//@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
