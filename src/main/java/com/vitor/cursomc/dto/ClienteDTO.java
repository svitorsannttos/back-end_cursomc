package com.vitor.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.vitor.cursomc.domain.Cliente;

@SuppressWarnings("deprecation")
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatorio")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatorio")
	@Email(message = "E-mail inválido")
	private String email;

	public ClienteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
