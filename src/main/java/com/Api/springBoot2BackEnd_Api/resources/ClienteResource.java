package com.Api.springBoot2BackEnd_Api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Api.springBoot2BackEnd_Api.domain.Cliente;
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

}
