/**
 * 
 */
package com.fiap.leilao.domain.bean;

import java.util.List;

import com.fiap.leilao.domain.Item;
import com.fiap.leilao.domain.Produto;
import com.fiap.leilao.domain.exception.LeilaoDomainException;

/**
 * @author Leandro
 *
 */
public interface ItemBean extends AbstractDomain<Item> {

	/**
	 * 
	 * @param produto
	 * @return
	 * @throws IllegalArgumentException
	 * @throws LeilaoDomainException
	 */
	public List<Item> getItensProduto(Produto produto) throws IllegalArgumentException , LeilaoDomainException;
}
