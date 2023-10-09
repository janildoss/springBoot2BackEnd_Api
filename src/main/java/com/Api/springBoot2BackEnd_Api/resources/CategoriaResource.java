package com.Api.springBoot2BackEnd_Api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Api.springBoot2BackEnd_Api.domain.Categoria;
import com.Api.springBoot2BackEnd_Api.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService service;
    //@RequestMapping(method= RequestMethod.GET)
    @GetMapping(value="/{id}")
    public ResponseEntity<?> listar(@PathVariable Integer id){
    	Categoria obj =  service.buscar(id);
    	 return ResponseEntity.ok(obj);  
    }

}
