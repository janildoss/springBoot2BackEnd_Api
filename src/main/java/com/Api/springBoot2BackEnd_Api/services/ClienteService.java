package com.Api.springBoot2BackEnd_Api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.springBoot2BackEnd_Api.domain.Cliente;
import com.Api.springBoot2BackEnd_Api.repositories.ClienteRepository;
import com.Api.springBoot2BackEnd_Api.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;
	
	/*public Cliente buscar(Integer id) {
		
		Optional<Cliente> obj = repo.findById(id);		
		return obj.orElseThrow(null) ;
	}*/
	//Tratamento para quando pesquisar categoria nao existente POR ID
		public Cliente find(Integer id) {
			Optional<Cliente> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
		
		
		
		
	
}
