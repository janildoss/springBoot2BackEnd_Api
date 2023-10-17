package com.Api.springBoot2BackEnd_Api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Api.springBoot2BackEnd_Api.domain.ItemPedido;

//@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
