/**
 * 
 */
package com.fiap.leilao.business;

import com.fiap.leilao.business.exception.LeilaoBusinessException;
import com.fiap.leilao.domain.Leilao;

/**
 * @author Leandro
 *
 */
public interface AutorizarLeilaoBean {

	/**
	 * 
	 * @param leilao
	 * @throws LeilaoBusinessException
	 */
	public void atutorizarLeilao(Leilao leilao) throws LeilaoBusinessException;
}
