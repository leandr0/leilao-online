/**
 * 
 */
package com.fiap.leilao.business;

import java.io.Serializable;

import com.fiap.leilao.business.exception.LeilaoBusinessException;
import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.Usuario;

/**
 * @author Leandro
 *
 */
public interface CriarLeilaoBean extends Serializable{

	/**
	 * 
	 * @param leilao
	 * @param usuario
	 * @return
	 * @throws LeilaoBusinessException
	 */
	public Long criarLeilao(Leilao leilao,Usuario usuario)throws LeilaoBusinessException , IllegalArgumentException;
}
