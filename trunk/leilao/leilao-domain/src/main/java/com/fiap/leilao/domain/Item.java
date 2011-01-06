/**
 * 
 */
package com.fiap.leilao.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Leandro
 *
 */
@Entity
@Table(name = "ITEM")
public class Item implements EntityBasic<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2763999461281718470L;

	@ManyToOne
	@JoinColumn(name = "PRODUTO_ID", nullable = false)
	private Produto produto;
	
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}