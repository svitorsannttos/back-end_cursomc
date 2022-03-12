package com.vitor.cursomc;

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
import com.vitor.cursomc.domain.Produto;
import com.vitor.cursomc.domain.enums.TipoCliente;
import com.vitor.cursomc.repositories.CategoriaRepository;
import com.vitor.cursomc.repositories.CidadeRepository;
import com.vitor.cursomc.repositories.ClienteRepository;
import com.vitor.cursomc.repositories.EnderecoRepository;
import com.vitor.cursomc.repositories.EstadoRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Tecnologia");
		Categoria cat2 = new Categoria(null, "Climatização");

		Produto p1 = new Produto(null, "Notebook", 2000.00);
		Produto p2 = new Produto(null, "Computador", 3500.00);
		Produto p3 = new Produto(null, "Agratto 9.000 Btus", 1100.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2));
		cat2.getProdutos().addAll(Arrays.asList(p3));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
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
	}

}
