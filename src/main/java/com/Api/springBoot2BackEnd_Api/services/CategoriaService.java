package com.Api.springBoot2BackEnd_Api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.Api.springBoot2BackEnd_Api.domain.Categoria;
import com.Api.springBoot2BackEnd_Api.dto.CategoriaDTO;
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
			    Categoria newObj = find(obj.getId());
				updateData(newObj, obj);
				return repo.save(newObj);
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
			
		public Page<Categoria> findPage(Integer page, Integer linesPerpage, String orderBy, String direction){
				PageRequest pageRequest = PageRequest.of(page, linesPerpage, Direction.valueOf(direction),orderBy);
				return repo.findAll(pageRequest);
		}
			//Metodo auxiliar que instancia um Categoria Dto
		public Categoria fromDTO(CategoriaDTO objDto) {
				return new Categoria(objDto.getId(),objDto.getNome());
		}
		
		private void updateData(Categoria newObj,Categoria obj) {			
			newObj.setNome(obj.getNome());
			//newObj.setEmail(obj.getEmail());
		}
}
