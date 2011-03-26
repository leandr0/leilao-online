/**
 * 
 */
package com.fiap.leilao.domain.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fiap.leilao.domain.Lance;
import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.Usuario;
import com.fiap.leilao.domain.exception.LeilaoDomainArgumentException;
import com.fiap.leilao.domain.exception.LeilaoDomainException;
import com.fiap.leilao.domain.security.Seguranca;
import com.fiap.leilao.domain.type.StatusLeilao;
import com.fiap.leilao.security.login.criptografia.CriptografiaUtil;

/**
 * @author Leandro
 *
 */
@Stateless(mappedName = UsuarioBean.JNDI_NAME)
@Remote(value = UsuarioBean.class)
public class ManagerUsuarioBean extends AbstractDomainBean<Usuario> implements UsuarioBean{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 472651525952181352L;
	
	private static final Log LOG = LogFactory.getLog(ManagerUsuarioBean.class);
	
	@Override
	public Usuario pesquisaLoginSenha(String login, String senha)throws LeilaoDomainArgumentException, LeilaoDomainException {

		if(StringUtils.isBlank(login)|| StringUtils.isBlank(senha))
			throw new LeilaoDomainArgumentException("Login e/ou Senha inv�lidos");

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
				throw new LeilaoDomainArgumentException("Registro n�o encontrado");
			
			return seguranca.getUsuario();
			
		}catch (LeilaoDomainArgumentException e) {
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
	public List<Leilao> pesquisaLeiloesUsuario(Usuario usuario)throws LeilaoDomainArgumentException, LeilaoDomainException {
		
		if(usuario == null)
			throw new LeilaoDomainArgumentException("Usu�rio inv�lido");

		try{
			CriteriaBuilder 			criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Leilao> 		criteriaQuery 	= criteriaBuilder.createQuery(Leilao.class);
			Root<Leilao> 				root			= criteriaQuery.from(Leilao.class);
			Predicate					condition		= criteriaBuilder.equal(root.get("vendedor"),usuario);

			criteriaQuery.where(condition);
			TypedQuery<Leilao> typedQuery = entityManager.createQuery(criteriaQuery);

			return typedQuery.getResultList();
			
		}catch (Exception e) {
			throw new LeilaoDomainException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.fiap.leilao.domain.bean.UsuarioBean#searchLeiloesLance(com.fiap.leilao.domain.Usuario)
	 */
	@Override
	public List<Leilao> pesquisaLeiloesLance(Usuario usuario)throws LeilaoDomainArgumentException, LeilaoDomainException {
		if(usuario == null)
			throw new IllegalArgumentException("Usu�rio inv�lido");

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
			
		}catch (Exception e) {
			throw new LeilaoDomainException(e);
		}
	}

	@Override
	public List<Leilao> pesquisaLeiloesGanhos(Usuario usuario)throws LeilaoDomainArgumentException, LeilaoDomainException {
		if(usuario == null)
			throw new LeilaoDomainArgumentException("Usu�rio inv�lido");

		try{
			CriteriaBuilder 			criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Leilao> 		criteriaQuery 	= criteriaBuilder.createQuery(Leilao.class);
			Root<Leilao> 				root			= criteriaQuery.from(Leilao.class);
			Predicate					condition		= criteriaBuilder.and(
													criteriaBuilder.equal(root.get("status"), StatusLeilao.FINALIZADO), 
													criteriaBuilder.equal(root.get("comprador"),usuario));

			criteriaQuery.where(condition);
			TypedQuery<Leilao> typedQuery = entityManager.createQuery(criteriaQuery);

			return typedQuery.getResultList();
			
		}catch (Exception e) {
			throw new LeilaoDomainException(e);
		}
	}

	@Override
	public Usuario pesquisaChaveSeguranca(String chaveSeguranca)throws LeilaoDomainArgumentException, LeilaoDomainException {

		if(StringUtils.isBlank(chaveSeguranca))
			throw new LeilaoDomainArgumentException("Usu�rio inv�lido");

		Connection conn = null;

		try{

			conn = dataSource.getConnection();
			
			PreparedStatement querySearch = conn.prepareStatement("SELECT USU.ID FROM USUARIO USU " +
																  " INNER JOIN SEGURANCA SEG "+
																  " ON USU.SEGURANCA_ID = SEG.ID "+
																  " WHERE SEG.CHAVE_SERVICO  = ? ");

			querySearch.setString(1, chaveSeguranca);
			
			ResultSet resultSet = querySearch.executeQuery();

			Usuario result = new Usuario();

			if(resultSet.next())
				result.setId(resultSet.getLong(1));
			
			return result;

		}catch (Throwable e) {
			throw new LeilaoDomainException(e);
		}finally{

			try{
				conn.close();
			}catch (Throwable e) {
				LOG.error("Erro ao fechar conexao");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.fiap.leilao.domain.bean.UsuarioBean#salvarUsuario(com.fiap.leilao.domain.Usuario)
	 */
	@Override
	public Usuario salvarUsuario(Usuario usuario)throws LeilaoDomainArgumentException, LeilaoDomainException {
		try{
		
			usuario.getSeguranca().setSenha(CriptografiaUtil.criptografar(usuario.getSeguranca().getSenha()));
			
			
			StringBuffer  chaveServico = new StringBuffer();
			chaveServico.append(usuario.getSeguranca().getLogin().getBytes());
			chaveServico.append(Calendar.getInstance().getTimeInMillis());
			
			usuario.getSeguranca().setChaveServico(chaveServico.toString());
			
			return insert(usuario);
			
		}catch (Exception e) {
			throw new LeilaoDomainArgumentException(e.getMessage());
		}
	}
}