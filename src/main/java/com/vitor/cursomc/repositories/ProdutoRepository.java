package com.vitor.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitor.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
