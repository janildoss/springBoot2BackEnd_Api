package com.Api.springBoot2BackEnd_Api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Api.springBoot2BackEnd_Api.domain.Produto;
import com.Api.springBoot2BackEnd_Api.dto.ProdutoDTO;
import com.Api.springBoot2BackEnd_Api.resources.utils.URL;
import com.Api.springBoot2BackEnd_Api.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	@Autowired
	private ProdutoService service;
    //@RequestMapping(method= RequestMethod.GET)
    @GetMapping(value="/{id}")
    public ResponseEntity<Produto> listar(@PathVariable Integer id){
    	Produto obj =  service.find(id);
        return ResponseEntity.ok(obj);  
    }
    
    //Buscar todas os PRODUTOS com paginação
    //No postman:http://localhost:8080/produtos/nome=computador&categorias=1,3,4
     //@GetMapping(value="/page") 
    @GetMapping   
    public ResponseEntity<Page<ProdutoDTO>> findPage(
    		@RequestParam(value="nome",defaultValue="") String nome, 
    		@RequestParam(value="categorias",defaultValue="") String categorias, 
    		@RequestParam(value="page",defaultValue="0") Integer page, 
    		@RequestParam(value="linesPerpage",defaultValue="24")Integer linesPerpage, 
    		@RequestParam(value="orderBy",defaultValue="nome")String orderBy, 
    		@RequestParam(value="direction",defaultValue="ASC")String direction){    	
    	//String nomeDecoded = URL.decodeParam(categorias);
    	String nomeDecoded = URL.decodeParam(nome);
    	List<Integer> ids = URL.decodeIntList(categorias);
    	Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerpage, orderBy, direction);
    	//Converte uma lista para outra lista
    	Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
    	return ResponseEntity.ok().body(listDto);    	
    }

}
