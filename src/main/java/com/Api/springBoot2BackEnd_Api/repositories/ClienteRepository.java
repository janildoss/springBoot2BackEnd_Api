package com.Api.springBoot2BackEnd_Api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Api.springBoot2BackEnd_Api.domain.Cliente;

//@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	@Transactional
	Cliente findByEmail(String email);
}
