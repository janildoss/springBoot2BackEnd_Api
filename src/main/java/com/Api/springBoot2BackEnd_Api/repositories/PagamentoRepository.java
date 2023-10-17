package com.Api.springBoot2BackEnd_Api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Api.springBoot2BackEnd_Api.domain.Pagamento;

//@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
