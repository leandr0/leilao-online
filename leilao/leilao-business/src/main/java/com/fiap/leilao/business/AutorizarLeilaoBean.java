/**
 * 
 */
package com.fiap.leilao.business;

import java.util.List;

import com.fiap.leilao.business.exception.LeilaoBusinessException;
import com.fiap.leilao.domain.Item;
import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.Produto;

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
	
	/**
	 * 
	 * @return {@link List}{@code <}{@link Leilao}{@code >}
	 * @throws LeilaoBusinessException
	 */
	public List<Leilao> buscarLeiloesPendentes() throws LeilaoBusinessException;
	
	/**
	 * 
	 * @param produto {@link Produto}
	 * @return {@link List}{@code <}{@link Item}{@code >}
	 * @throws LeilaoBusinessException
	 */
	public List<Item> buscarItensLeilao(Produto produto) throws LeilaoBusinessException;
	
	/**
	 * 
	 * @param leilao
	 * @throws LeilaoBusinessException
	 */
	public void cancelarLeilao(Leilao leilao) throws LeilaoBusinessException;
}