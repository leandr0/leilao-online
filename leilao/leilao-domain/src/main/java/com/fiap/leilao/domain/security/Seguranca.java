/**
 * 
 */
package com.fiap.leilao.domain.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fiap.leilao.domain.EntityBasic;
import com.fiap.leilao.domain.Usuario;

/**
 * @author Leandro
 *
 */
@Entity
@Table(name = "SEGURANCA")
public class Seguranca implements EntityBasic<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4799634596281141708L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "LOGIN", nullable = false , unique = true )	
	private String login;
	
	@Column(name = "SENHA" , nullable = false)
	private String senha;

	@OneToOne(mappedBy = "seguranca")
	private Usuario usuario;
	
	/* (non-Javadoc)
	 * @see com.fiap.leilao.domain.EntityBasic#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see com.fiap.leilao.domain.EntityBasic#setId(java.lang.Object)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}