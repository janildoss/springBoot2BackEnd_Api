package com.Api.springBoot2BackEnd_Api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.Api.springBoot2BackEnd_Api.domain.Pedido;

//@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
