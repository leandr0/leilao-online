/**
 * 
 */
package com.fiap.leilao.domain.bean;

import java.util.List;

import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.Usuario;
import com.fiap.leilao.domain.exception.LeilaoDomainException;

/**
 * @author Leandro
 *
 */
public interface UsuarioBean extends AbstractDomain<Usuario>{

	/**
	 * 
	 */
	public static final String JNDI_NAME = "managerUsuarioBean";
	
	/**
	 * 
	 * @param login
	 * @param senha
	 * @return
	 * @throws IllegalArgumentException
	 * @throws LeilaoDomainException
	 */
	public Usuario findByLoginSenha(String login , String senha) throws IllegalArgumentException , LeilaoDomainException;
	
	/**
	 * 
	 * @param usuario
	 * @return
	 * @throws IllegalArgumentException
	 * @throws LeilaoDomainException
	 */
	public List<Leilao> searchLeiloesUsuario(Usuario usuario) throws IllegalArgumentException , LeilaoDomainException;
	
	/**
	 * 
	 * @param usuario
	 * @return
	 * @throws IllegalArgumentException
	 * @throws LeilaoDomainException
	 */
	public List<Leilao> searchLeiloesLance(Usuario usuario) throws IllegalArgumentException , LeilaoDomainException;
}
