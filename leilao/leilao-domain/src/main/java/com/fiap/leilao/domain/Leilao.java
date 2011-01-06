/**
 * 
 */
package com.fiap.leilao.domain;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author Leandro
 *
 */
public class Leilao implements EntityBasic<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2861797679346068361L;

	@OneToOne
	@JoinColumn(name = "VENDEDOR_ID")
	private Usuario vendedor;
	
	@OneToOne
	@JoinColumn(name = "COMPRADOR_ID")
	private Usuario comprador;
	
	private List<Lance> lances;
	
	private Produto produto;
	
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}

	public List<Lance> getLances() {
		return lances;
	}

	public void setLances(List<Lance> lances) {
		this.lances = lances;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}	
}