/**
 * 
 */
package com.fiap.leilao.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Leandro
 *
 */
@Entity
@Table(name = "PRODUTO")
public class Produto implements EntityBasic<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 480937383494357689L;
	
	@OneToOne
	@JoinColumn(name = "LEILAO_ID", nullable = false)
	private Leilao leilao;
	
	@OneToMany(mappedBy = "produto")
	private List<Item> itens;
	
	/* (non-Javadoc)
	 * @see com.fiap.leilao.domain.EntityBasic#getId()
	 */
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.fiap.leilao.domain.EntityBasic#setId(java.lang.Object)
	 */
	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub

	}

	public Leilao getLeilao() {
		return leilao;
	}

	public void setLeilao(Leilao leilao) {
		this.leilao = leilao;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
}