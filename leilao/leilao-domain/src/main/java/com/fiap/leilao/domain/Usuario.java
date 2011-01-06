/**
 * 
 */
package com.fiap.leilao.domain;

import java.util.List;

/**
 * @author Leandro
 *
 */
public class Usuario implements EntityBasic<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7323841700391064976L;

	private Leilao leilaoComprador;
	
	private Leilao leilaoVendedor;
	
	private List<Lance> lances;
	
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

	public Leilao getLeilaoComprador() {
		return leilaoComprador;
	}

	public void setLeilaoComprador(Leilao leilaoComprador) {
		this.leilaoComprador = leilaoComprador;
	}

	public Leilao getLeilaoVendedor() {
		return leilaoVendedor;
	}

	public void setLeilaoVendedor(Leilao leilaoVendedor) {
		this.leilaoVendedor = leilaoVendedor;
	}

	public List<Lance> getLances() {
		return lances;
	}

	public void setLances(List<Lance> lances) {
		this.lances = lances;
	}
}
