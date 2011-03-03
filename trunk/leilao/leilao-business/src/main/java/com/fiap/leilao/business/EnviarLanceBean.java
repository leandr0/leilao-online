/**
 * 
 */
package com.fiap.leilao.business;

import java.io.Serializable;
import java.util.List;

import com.fiap.leilao.business.exception.LeilaoBusinessException;
import com.fiap.leilao.domain.Item;
import com.fiap.leilao.domain.Lance;
import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.Produto;
import com.fiap.leilao.domain.Usuario;

/**
 * @author Leandro
 *
 */
public interface EnviarLanceBean extends Serializable{
	
	public static String JNDI_NAME = "enviarLanceBean";
	
	/**
	 * 
	 * @param lance
	 * @param usuario
	 * @return
	 * @throws LeilaoBusinessException
	 * @throws IllegalArgumentException
	 */
	public Long enviarLance(Lance lance , Usuario usuario) throws LeilaoBusinessException , IllegalArgumentException;

	/**
	 * 
	 * @return
	 * @throws LeilaoBusinessException
	 * @throws IllegalArgumentException
	 */
	public List<Leilao> buscarLeiloesAtivos() throws LeilaoBusinessException , IllegalArgumentException;
	
	/**
	 * 
	 * @param produto
	 * @return
	 * @throws LeilaoBusinessException
	 * @throws IllegalArgumentException
	 */
	public List<Item> buscarItensProduto(Produto produto) throws LeilaoBusinessException , IllegalArgumentException;
	
	public void enviarEmail(String mensagem) throws LeilaoBusinessException , IllegalArgumentException;
	
}
