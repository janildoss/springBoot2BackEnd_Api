package com.Api.springBoot2BackEnd_Api.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.Api.springBoot2BackEnd_Api.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		//Procedimento para gerar uma data de vencimento. 
		//Deveria ser um webservice que geraria boleto
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
		
	}

}
