package com.Api.springBoot2BackEnd_Api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.Api.springBoot2BackEnd_Api.domain.Categoria;
import com.Api.springBoot2BackEnd_Api.repositories.CategoriaRepository;
import com.Api.springBoot2BackEnd_Api.services.exceptions.DataIntegrityException;
import com.Api.springBoot2BackEnd_Api.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;
	
	/*public Categoria buscar(Integer id) {
		
		Optional<Categoria> obj = repo.findById(id);		
		return obj.orElseThrow(null) ;
	}*/
	//Tratamento para quando pesquisar categoria nao existente POR ID
		public Categoria find(Integer id) {
			Optional<Categoria> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}	
	
		public Categoria insert(Categoria obj) {
			obj.setId(null);
			return repo.save(obj);
		}
		
		//No update se o id for null faz um insert
			public Categoria update(Categoria obj) {
			find(obj.getId());
			return repo.save(obj);
		}
		
			public void delete(Integer id) {
				find(id);
				try {
					repo.deleteById(id);					
				}
				catch(DataIntegrityViolationException e){
					throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos.");
				}
				repo.deleteById(id);
			}		
			
			public List<Categoria> findAll(){
				return repo.findAll();
				
			}
}
