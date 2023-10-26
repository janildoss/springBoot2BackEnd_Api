package com.Api.springBoot2BackEnd_Api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Api.springBoot2BackEnd_Api.domain.Cidade;
import com.Api.springBoot2BackEnd_Api.domain.Cliente;
import com.Api.springBoot2BackEnd_Api.domain.Endereco;
import com.Api.springBoot2BackEnd_Api.domain.enums.TipoCliente;
import com.Api.springBoot2BackEnd_Api.dto.ClienteDTO;
import com.Api.springBoot2BackEnd_Api.dto.ClienteNewDTO;
import com.Api.springBoot2BackEnd_Api.repositories.ClienteRepository;
import com.Api.springBoot2BackEnd_Api.repositories.EnderecoRepository;
import com.Api.springBoot2BackEnd_Api.services.exceptions.DataIntegrityException;
import com.Api.springBoot2BackEnd_Api.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	/*public Cliente buscar(Integer id) {
		
		Optional<Cliente> obj = repo.findById(id);		
		return obj.orElseThrow(null) ;
	}*/
	//Tratamento para quando pesquisar categoria nao existente POR ID
		public Cliente find(Integer id) {
			Optional<Cliente> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
		
		@Transactional
		public Cliente insert(Cliente obj) {
			obj.setId(null);					
			repo.save(obj);			
	        enderecoRepository.saveAll(obj.getEnderecos());
			return obj;		
		}
		
		//No update se o id for null faz um insert
	    public Cliente update(Cliente obj) {
			Cliente newObj = find(obj.getId());
			updateData(newObj, obj);
			return repo.save(newObj);
		}
		
		 public void delete(Integer id) {
				find(id);
				try {
					repo.deleteById(id);					
				}
				catch(DataIntegrityViolationException e){
					throw new DataIntegrityException("Não é possivel excluir um cliente que possui pedido.");
				}
				repo.deleteById(id);
		}		
			
		public List<Cliente> findAll(){
				return repo.findAll();				
		}
			
		public Page<Cliente> findPage(Integer page, Integer linesPerpage, String orderBy, String direction){
				PageRequest pageRequest = PageRequest.of(page, linesPerpage, Direction.valueOf(direction),orderBy);
				return repo.findAll(pageRequest);
		}
		//Metodo auxiliar que instancia um Cliente Dto
		public Cliente fromDTO(ClienteDTO objDto) {
				return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(),null,null);
				//throw new UnsupportedOperationException();
		}
		
		public Cliente fromDTO(ClienteNewDTO objDto) {
			Cliente  cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
			Cidade   cid = new Cidade(objDto.getCidadeId(),null,null);			
			Endereco end = new Endereco(null,objDto.getLogradouro(),objDto.getNumero(), objDto.getComplemento(),objDto.getBairro(),objDto.getCep(),cli, cid);			
			cli.getEnderecos().add(end);			
			cli.getTelefones().add(objDto.getTelefone1());
			
			if(objDto.getTelefone2()!=null) {
				cli.getTelefones().add(objDto.getTelefone2());
			}
			if(objDto.getTelefone3()!=null) {
				cli.getTelefones().add(objDto.getTelefone3());
			}
			
			return cli;		
    	}
		
		private void updateData(Cliente newObj,Cliente obj) {			
			newObj.setNome(obj.getNome());
			newObj.setEmail(obj.getEmail());
		}
				
	
}
