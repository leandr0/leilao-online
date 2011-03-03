/**
 * 
 */
package com.fiap.leilao.domain.bean;

import com.fiap.leilao.domain.Lance;
import com.fiap.leilao.domain.exception.LeilaoDomainException;

/**
 * @author Leandro
 *
 */
public interface LanceBean extends AbstractDomain<Lance> {

	public static final String JNDI_NAME = "managerLanceBean";
	
	/**
	 * 
	 * @param idLeilao
	 * @return
	 * @throws IllegalArgumentException
	 * @throws LeilaoDomainException
	 */
	public Long getMaiorLanceLeilao(Long idLeilao) throws IllegalArgumentException , LeilaoDomainException;
}
