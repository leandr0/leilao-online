/**
 * 
 */
package com.fiap.leilao.domain.bean;

import java.util.List;

import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.exception.LeilaoDomainException;
import com.fiap.leilao.domain.type.StatusLeilao;

/**
 * @author Leandro
 *
 */
public interface LeilaoBean extends AbstractDomain<Leilao> {
	
	public static final String JNDI_NAME = "leilaoBean";
	
	/**
	 * 
	 * @param statusLeilao
	 * @return
	 * @throws IllegalArgumentException
	 * @throws LeilaoDomainException
	 */
	public List<Leilao> searchLeilaoByStatus(StatusLeilao statusLeilao)throws IllegalArgumentException , LeilaoDomainException;
	
	/**
	 * 
	 * @return
	 * @throws LeilaoDomainException
	 */
	public List<Long> searchFinalizarLeilao() throws  LeilaoDomainException;
	
	/**
	 * 
	 * @param leiloesFinalizados
	 * @throws LeilaoDomainException
	 */
	public void updateLeiloesFinalizados(List<Long> leiloesFinalizados)throws  LeilaoDomainException;
}
