/**
 * 
 */
package com.fiap.leilao.domain.bean;

import java.util.List;

import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.Usuario;
import com.fiap.leilao.domain.exception.LeilaoDomainException;
import com.fiap.leilao.domain.exception.LeilaoDomainArgumentException;
import com.fiap.leilao.domain.type.StatusLeilao;

/**
 * Interface que permite
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
	public List<Leilao> pesquisaLeilaoStatus(StatusLeilao statusLeilao)throws LeilaoDomainArgumentException , LeilaoDomainException;
	
	/**
	 * Busca leiloes que o usuário pode enviar lance
	 * @param statusLeilao
	 * @return
	 * @throws LeilaoDomainArgumentException
	 * @throws LeilaoDomainException
	 */
	public List<Leilao> pesquisaLeilaoEnviarLance(Usuario ususUsuario)throws LeilaoDomainArgumentException , LeilaoDomainException;
	
	/**
	 * 
	 * @return
	 * @throws LeilaoDomainException
	 */
	public List<Long> pesquisaFinalizarLeilao() throws  LeilaoDomainException;
	
	/**
	 * 
	 * @param leiloesFinalizados
	 * @throws LeilaoDomainException
	 */
	public void updateLeiloesFinalizados(List<Long> leiloesFinalizados)throws  LeilaoDomainException;
	
	/**
	 * 
	 * @param ususUsuario
	 * @return
	 * @throws LeilaoDomainArgumentException
	 * @throws LeilaoDomainException
	 */
	public List<Leilao> pesquisaLeilaoStatusUsuario(Usuario ususUsuario)throws LeilaoDomainArgumentException , LeilaoDomainException;
	
	/**
	 * 
	 * @param idLeilao
	 * @return
	 * @throws LeilaoDomainArgumentException
	 * @throws LeilaoDomainException
	 */
	public Leilao pesquisarLeilaoGanhador(Long idLeilao)throws LeilaoDomainArgumentException , LeilaoDomainException;
}
