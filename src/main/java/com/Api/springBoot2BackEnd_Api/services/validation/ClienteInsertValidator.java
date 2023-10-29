package com.Api.springBoot2BackEnd_Api.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//import com.Api.springBoot2BackEnd_Api.domain.Cliente;
import com.Api.springBoot2BackEnd_Api.domain.enums.TipoCliente;
import com.Api.springBoot2BackEnd_Api.dto.ClienteNewDTO;
//import com.Api.springBoot2BackEnd_Api.repositories.ClienteRepository;
import com.Api.springBoot2BackEnd_Api.resources.exception.FieldMessage;
import com.Api.springBoot2BackEnd_Api.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	//@Autowired
	//private ClienteRepository repo;
	@Override
	public void initialize(ClienteInsert ann) {
		
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();		
		
		// inclua os testes aqui, inserindo erros na lista
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod())&& !BR.isValidCPF(objDto.getCpfOuCnpj())){
			list.add(new FieldMessage("CpfOuCnpj","Cpf Invalido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod())&& !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
			list.add(new FieldMessage("CpfOuCnpj","CNPJ Invalido"));
		}
		
		/*Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux !=null) {
			list.add(new FieldMessage("email","Email ja existente"));
		}*/
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldMessage()).addConstraintViolation();
			//context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}
