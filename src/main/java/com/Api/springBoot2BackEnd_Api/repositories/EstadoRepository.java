package com.Api.springBoot2BackEnd_Api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Api.springBoot2BackEnd_Api.domain.Estado;

//@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
