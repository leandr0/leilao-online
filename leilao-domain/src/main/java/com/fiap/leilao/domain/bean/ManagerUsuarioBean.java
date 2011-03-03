/**
 * 
 */
package com.fiap.leilao.domain.bean;

import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

import com.fiap.leilao.domain.Lance;
import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.Usuario;
import com.fiap.leilao.domain.exception.LeilaoDomainException;
import com.fiap.leilao.domain.security.Seguranca;

/**
 * @author Leandro
 *
 */
@Stateless(mappedName = UsuarioBean.JNDI_NAME)
@Remote(value = UsuarioBean.class)
@Asynchronous
public class ManagerUsuarioBean extends AbstractDomainBean<Usuario> implements UsuarioBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7619461255665450157L;

	@Override
	public Usuario findByLoginSenha(String login, String senha)throws IllegalArgumentException, LeilaoDomainException {

		if(StringUtils.isBlank(login)|| StringUtils.isBlank(senha))
			throw new IllegalArgumentException("Login e/ou Senha inválidos");

		try{
			CriteriaBuilder 			criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Seguranca> 	criteriaQuery 	= criteriaBuilder.createQuery(Seguranca.class);
			Root<Seguranca> 			root			= criteriaQuery.from(Seguranca.class);
			Predicate					condition		= criteriaBuilder.and(
															criteriaBuilder.equal(root.get("login"),login),
															criteriaBuilder.equal(root.get("senha"),senha));

			criteriaQuery.where(condition);
			TypedQuery<Seguranca> typedQuery = entityManager.createQuery(criteriaQuery);

			Seguranca seguranca = typedQuery.getSingleResult();
			
			if(seguranca == null)
				throw new IllegalArgumentException("Registro não encontrado");
			
			return seguranca.getUsuario();
			
		}catch (IllegalArgumentException e) {
			throw e;
		}
		catch (Exception e) {
			throw new LeilaoDomainException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.fiap.leilao.domain.bean.UsuarioBean#searchLeiloesUsuario(com.fiap.leilao.domain.Usuario)
	 */
	@Override
	public List<Leilao> searchLeiloesUsuario(Usuario usuario)throws IllegalArgumentException, LeilaoDomainException {
		
		if(usuario == null)
			throw new IllegalArgumentException("Usuário inválido");

		try{
			CriteriaBuilder 			criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Leilao> 		criteriaQuery 	= criteriaBuilder.createQuery(Leilao.class);
			Root<Leilao> 				root			= criteriaQuery.from(Leilao.class);
			Predicate					condition		= criteriaBuilder.equal(root.get("vendedor"),usuario);

			criteriaQuery.where(condition);
			TypedQuery<Leilao> typedQuery = entityManager.createQuery(criteriaQuery);

			return typedQuery.getResultList();
			
		}catch (IllegalArgumentException e) {
			throw e;
		}
		catch (Exception e) {
			throw new LeilaoDomainException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.fiap.leilao.domain.bean.UsuarioBean#searchLeiloesLance(com.fiap.leilao.domain.Usuario)
	 */
	@Override
	public List<Leilao> searchLeiloesLance(Usuario usuario)throws IllegalArgumentException, LeilaoDomainException {
		if(usuario == null)
			throw new IllegalArgumentException("Usuário inválido");

		try{
			CriteriaBuilder 			criteriaBuilder 	= entityManager.getCriteriaBuilder();
			CriteriaQuery<Leilao> 		criteriaQuery 		= criteriaBuilder.createQuery(Leilao.class);
			Root<Leilao> 				root				= criteriaQuery.from(Leilao.class);
			Join<Leilao, Lance> 		joinLeilaoLance 	= root.join("lances");
			Join<Lance, Usuario> 		joinLanceUsuario	= joinLeilaoLance.join("usuario");			
			Predicate					condition			= criteriaBuilder.equal(joinLanceUsuario,usuario);
			
			criteriaQuery.where(condition);
			
			TypedQuery<Leilao> typedQuery = entityManager.createQuery(criteriaQuery);
			
			return typedQuery.getResultList();
			
		}catch (IllegalArgumentException e) {
			throw e;
		}
		catch (Exception e) {
			throw new LeilaoDomainException(e);
		}
	}
}