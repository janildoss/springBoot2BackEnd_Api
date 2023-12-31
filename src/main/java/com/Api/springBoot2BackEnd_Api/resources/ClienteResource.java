package com.Api.springBoot2BackEnd_Api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Api.springBoot2BackEnd_Api.domain.Cliente;
import com.Api.springBoot2BackEnd_Api.dto.ClienteDTO;
import com.Api.springBoot2BackEnd_Api.dto.ClienteNewDTO;
import com.Api.springBoot2BackEnd_Api.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	@Autowired
	private ClienteService service;
    //@RequestMapping(method= RequestMethod.GET)
    @GetMapping(value="/{id}")
    public ResponseEntity<Cliente> listar(@PathVariable Integer id){
    	Cliente obj =  service.find(id);
    	 return ResponseEntity.ok(obj);  
    }
    
    @PostMapping 
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){    	
    	Cliente obj = service.fromDTO(objDto);
    	obj = service.insert(obj);
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
    			.path("/{id}").buildAndExpand(obj.getId()).toUri();
    	return ResponseEntity.created(uri).build();
    }
    
    
    @PutMapping(value="/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){
	    Cliente obj = service.fromDTO(objDto);
    	obj.setId(id);
    	obj = service.update(obj);
    	return ResponseEntity.noContent().build();
    } 
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Integer id){
	   	service.delete(id);
    	return ResponseEntity.noContent().build();
    } 
    
    //Buscar todas as categorias
    @GetMapping() 
    public ResponseEntity<List<ClienteDTO>> findAll(){
    	List<Cliente> list = service.findAll();
    	//Converte uma lista para outra lista
    	List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
    	return ResponseEntity.ok().body(listDto);    	
    }
    //Buscar todas as categorias com paginação
    //No postman:/categorias/page?page=0&linesPerpage=20
    @GetMapping(value="/page") 
    public ResponseEntity<Page<ClienteDTO>> findPage(
    		@RequestParam(value="page",defaultValue="0") Integer page, 
    		@RequestParam(value="linesPerpage",defaultValue="24")Integer linesPerpage, 
    		@RequestParam(value="orderBy",defaultValue="nome")String orderBy, 
    		@RequestParam(value="direction",defaultValue="ASC")String direction){    	
    		Page<Cliente> list = service.findPage(page,linesPerpage,orderBy,direction);
    		//Converte uma lista para outra lista
    		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
    		return ResponseEntity.ok().body(listDto);    	
    }
    

}
