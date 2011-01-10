/**
 * 
 */
package com.fiap.leilao.business;

import java.io.Serializable;

import com.fiap.leilao.business.exception.LeilaoBusinessException;
import com.fiap.leilao.domain.Lance;
import com.fiap.leilao.domain.Usuario;

/**
 * @author Leandro
 *
 */
public interface EnviarLanceBean extends Serializable{
	
	/**
	 * 
	 * @param lance
	 * @param usuario
	 * @return
	 * @throws LeilaoBusinessException
	 * @throws IllegalArgumentException
	 */
	public Long enviarLance(Lance lance , Usuario usuario) throws LeilaoBusinessException , IllegalArgumentException;

}
