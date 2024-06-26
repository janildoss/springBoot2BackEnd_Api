package com.Api.springBoot2BackEnd_Api.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Api.springBoot2BackEnd_Api.domain.ItemPedido;
import com.Api.springBoot2BackEnd_Api.domain.PagamentoComBoleto;
import com.Api.springBoot2BackEnd_Api.domain.Pedido;
import com.Api.springBoot2BackEnd_Api.domain.enums.EstadoPagamento;
import com.Api.springBoot2BackEnd_Api.repositories.ItemPedidoRepository;
import com.Api.springBoot2BackEnd_Api.repositories.PagamentoRepository;
import com.Api.springBoot2BackEnd_Api.repositories.PedidoRepository;
import com.Api.springBoot2BackEnd_Api.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;
	@Autowired
	private BoletoService  boletoService;
	@Autowired
	private PagamentoRepository  pagamentoRepository;
	@Autowired
	private ProdutoService  produtoService;
	@Autowired
	private ItemPedidoRepository  itemPedidoRepository;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EmailService emailService;
	/*public Pedido buscar(Integer id) {
		
		Optional<Ccategoria> obj = repo.findById(id);		
		return obj.orElseThrow(null) ;
	}*/
	//Tratamento para quando pesquisar categoria nao existente POR ID
		public Pedido find(Integer id) {
			Optional<Pedido> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		}
		
		@Transactional
		public Pedido insert(Pedido obj) {
			obj.setId(null);
			obj.setInstante(new Date());
			obj.setCliente(clienteService.find(obj.getCliente().getId()));
			obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
			obj.getPagamento().setPedido(obj);
			if(obj.getPagamento() instanceof PagamentoComBoleto) {
				PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
				boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
			}
			obj = repo.save(obj);
			pagamentoRepository.save(obj.getPagamento());
			for (ItemPedido ip : obj.getItens()) {
				ip.setDesconto(0.0);
				ip.setProduto(produtoService.find(ip.getProduto().getId()));				
				ip.setPreco(ip.getProduto().getPreco());
				ip.setPedido(obj);
			}
			
			itemPedidoRepository.saveAll(obj.getItens());  
			
			//emailService.sendOrderConfirmationEmail(obj);
			emailService.sendOrderConfirmationHtmlEmail(obj);
			return obj;	
			
		}
	
	//Tratamento para quando pesquisar categoria nao existente POR ID
	//	public Pedido find(Integer id) {
	//		Optional<Pedido> obj = repo.findById(id);
	//		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	//	}
}
