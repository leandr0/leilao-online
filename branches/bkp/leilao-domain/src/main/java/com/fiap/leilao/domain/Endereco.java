/**
 * 
 */
package com.fiap.leilao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Leandro
 *
 */
@Entity
@Table(name = "ENDERECO")
public class Endereco implements EntityBasic<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9069126964673722148L;
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
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
}