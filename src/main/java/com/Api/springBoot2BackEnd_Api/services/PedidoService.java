package com.Api.springBoot2BackEnd_Api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.springBoot2BackEnd_Api.domain.Pedido;
import com.Api.springBoot2BackEnd_Api.repositories.PedidoRepository;
import com.Api.springBoot2BackEnd_Api.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;
	
	/*public Pedido buscar(Integer id) {
		
		Optional<Ccategoria> obj = repo.findById(id);		
		return obj.orElseThrow(null) ;
	}*/
	//Tratamento para quando pesquisar categoria nao existente POR ID
		public Pedido buscar(Integer id) {
			Optional<Pedido> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		}
	
	//Tratamento para quando pesquisar categoria nao existente POR ID
	//	public Pedido find(Integer id) {
	//		Optional<Pedido> obj = repo.findById(id);
	//		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	//	}
}
