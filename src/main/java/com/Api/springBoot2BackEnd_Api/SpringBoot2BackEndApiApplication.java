package com.Api.springBoot2BackEnd_Api;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Api.springBoot2BackEnd_Api.domain.Categoria;
import com.Api.springBoot2BackEnd_Api.domain.Cidade;
import com.Api.springBoot2BackEnd_Api.domain.Cliente;
import com.Api.springBoot2BackEnd_Api.domain.Endereco;
import com.Api.springBoot2BackEnd_Api.domain.Estado;
import com.Api.springBoot2BackEnd_Api.domain.ItemPedido;
import com.Api.springBoot2BackEnd_Api.domain.Pagamento;
import com.Api.springBoot2BackEnd_Api.domain.PagamentoComBoleto;
import com.Api.springBoot2BackEnd_Api.domain.PagamentoComCartao;
import com.Api.springBoot2BackEnd_Api.domain.Pedido;
import com.Api.springBoot2BackEnd_Api.domain.Produto;
import com.Api.springBoot2BackEnd_Api.domain.enums.EstadoPagamento;
import com.Api.springBoot2BackEnd_Api.domain.enums.TipoCliente;
import com.Api.springBoot2BackEnd_Api.repositories.CategoriaRepository;
import com.Api.springBoot2BackEnd_Api.repositories.CidadeRepository;
import com.Api.springBoot2BackEnd_Api.repositories.ClienteRepository;
import com.Api.springBoot2BackEnd_Api.repositories.EnderecoRepository;
import com.Api.springBoot2BackEnd_Api.repositories.EstadoRepository;
import com.Api.springBoot2BackEnd_Api.repositories.ItemPedidoRepository;
import com.Api.springBoot2BackEnd_Api.repositories.PagamentoRepository;
import com.Api.springBoot2BackEnd_Api.repositories.PedidoRepository;
import com.Api.springBoot2BackEnd_Api.repositories.ProdutoRepository;

@SpringBootApplication
public class SpringBoot2BackEndApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2BackEndApiApplication.class, args);
		}
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Override	
	public void run(String... args) throws Exception {
		// Adicionar dados as tabelas ctegoria e produtos
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritorio");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2)); // ,cat3,cat4,cat5,cat6,cat7,cat8));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838993"));
		
		Endereco e1 = new Endereco(null, "Rua das flores", "300", "Apto 303", "Jardim", "38220834", cli1,c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "400", "Sala 800", "Centro", "38777812", cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"),cli1,e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"),cli1,e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTO,ped2,sdf.parse("20/10/2017 00:00"),null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}	

}
