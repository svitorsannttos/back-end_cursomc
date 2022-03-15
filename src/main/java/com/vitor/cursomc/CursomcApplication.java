package com.vitor.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vitor.cursomc.domain.Categoria;
import com.vitor.cursomc.domain.Cidade;
import com.vitor.cursomc.domain.Cliente;
import com.vitor.cursomc.domain.Endereco;
import com.vitor.cursomc.domain.Estado;
import com.vitor.cursomc.domain.ItemPedido;
import com.vitor.cursomc.domain.Pagamento;
import com.vitor.cursomc.domain.PagamentoComBoleto;
import com.vitor.cursomc.domain.PagamentoComCartao;
import com.vitor.cursomc.domain.Pedido;
import com.vitor.cursomc.domain.Produto;
import com.vitor.cursomc.domain.enums.EstadoPagamento;
import com.vitor.cursomc.domain.enums.TipoCliente;
import com.vitor.cursomc.repositories.CategoriaRepository;
import com.vitor.cursomc.repositories.CidadeRepository;
import com.vitor.cursomc.repositories.ClienteRepository;
import com.vitor.cursomc.repositories.EnderecoRepository;
import com.vitor.cursomc.repositories.EstadoRepository;
import com.vitor.cursomc.repositories.ItemPedidoRepository;
import com.vitor.cursomc.repositories.PagamentoRepository;
import com.vitor.cursomc.repositories.PedidoRepository;
import com.vitor.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Tecnologia");
		Categoria cat2 = new Categoria(null, "Climatização");
		Categoria cat3 = new Categoria(null, "ulalau");
		Categoria cat4 = new Categoria(null, "e noses");
		Categoria cat5 = new Categoria(null, "eu ela e eu");
		Categoria cat6 = new Categoria(null, "aiaiaiaia");
		Categoria cat7 = new Categoria(null, "vamos que vamos");
		Categoria cat8 = new Categoria(null, "chupa cu de kiev");

		Produto p1 = new Produto(null, "Notebook", 2000.00);
		Produto p2 = new Produto(null, "Computador", 3500.00);
		Produto p3 = new Produto(null, "Agratto 9.000 Btus", 1100.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2));
		cat2.getProdutos().addAll(Arrays.asList(p3));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2,cat3,cat4,cat5,cat6,cat7,cat8));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado("Minas Gerais");
		Estado est2 = new Estado("Bahia");

		Cidade ci1 = new Cidade("Fátima", est2);
		Cidade ci2 = new Cidade("Adustina", est2);
		Cidade ci3 = new Cidade("Belorizonte", est1);

		est1.getCidades().addAll(Arrays.asList(ci3));
		est2.getCidades().addAll(Arrays.asList(ci1, ci2));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(ci1, ci2, ci3));

		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com", "55555555", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("252525", "959595"));

		Endereco end1 = new Endereco(null, "Avenida Dantas", "125", "Santos", "Japão", "49032490", cli1, ci1);
		Endereco end2 = new Endereco(null, "Avenida Doido", "999", "Aleluia", "Farol", "32564800", cli1, ci3);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2021 10:30"), cli1, end2);
		Pedido ped2 = new Pedido(null, sdf.parse("20/02/2022 10:30"), cli1, end1);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped2, 5);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped1, sdf.parse("01/01/2022 02:56"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped2, p3, 10.00, 1, p3.getPreco());
		ItemPedido ip2 = new ItemPedido(ped1, p2, 0.00, 2, p2.getPreco()*2);
		ItemPedido ip3 = new ItemPedido(ped2, p1, 50.00, 3, p1.getPreco()*3);
		
		ped2.getItens().addAll(Arrays.asList(ip1,ip3));
		ped1.getItens().addAll(Arrays.asList(ip2));
		
		p1.getItens().addAll(Arrays.asList(ip3));
		p2.getItens().addAll(Arrays.asList(ip2));
		p3.getItens().addAll(Arrays.asList(ip1));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
