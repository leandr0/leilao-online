/**
 * 
 */
package com.fiap.leilao.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fiap.leilao.domain.security.Seguranca;
import com.fiap.leilao.domain.type.PerfilUsuario;

/**
 * @author Leandro
 *
 */
@Entity
@Table( name = "USUARIO")
public class Usuario implements EntityBasic<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7323841700391064976L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOME" , nullable = false)
	private String nome;
	
	@Column(name = "DT_NASCIMENTO", nullable = false)
	private Date dataNascimento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PERFIL")
	private PerfilUsuario perfil;
	
	@OneToMany(mappedBy = "comprador")
	private List<Leilao> leiloesComprador;
	
	@OneToMany(mappedBy = "vendedor")
	private List<Leilao> leiloesVendedor;
	
	@OneToMany(mappedBy = "usuario")
	private List<Lance> lances;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name  = "SEGURANCA_ID")
	private Seguranca seguranca;
	
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
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public PerfilUsuario getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
	}

	public List<Leilao> getLeiloesComprador() {
		return leiloesComprador;
	}

	public void setLeiloesComprador(List<Leilao> leiloesComprador) {
		this.leiloesComprador = leiloesComprador;
	}

	public List<Leilao> getLeiloesVendedor() {
		return leiloesVendedor;
	}

	public void setLeiloesVendedor(List<Leilao> leiloesVendedor) {
		this.leiloesVendedor = leiloesVendedor;
	}

	public List<Lance> getLances() {
		return lances;
	}

	public void setLances(List<Lance> lances) {
		this.lances = lances;
	}

	public Seguranca getSeguranca() {
		return seguranca;
	}

	public void setSeguranca(Seguranca seguranca) {
		this.seguranca = seguranca;
	}	
}