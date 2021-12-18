package com.vitor.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.cursomc.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listarCategorias(){
		
		Categoria cat1 = new Categoria(1,"Tecnologia");
		Categoria cat2 = new Categoria(2,"Segurança");
		
		List<Categoria> list = new ArrayList<Categoria>();
		list.add(cat1);
		list.add(cat2);
		
		
		return list;
	}
}
