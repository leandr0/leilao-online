/**
 * 
 */
package com.fiap.leilao.business;

import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.fiap.leilao.business.exception.LeilaoBusinessException;
import com.fiap.leilao.business.message.EnviarMessageLanceBean;
import com.fiap.leilao.domain.Item;
import com.fiap.leilao.domain.Lance;
import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.Produto;
import com.fiap.leilao.domain.Usuario;
import com.fiap.leilao.domain.bean.ItemBean;
import com.fiap.leilao.domain.bean.LeilaoBean;
import com.fiap.leilao.domain.exception.LeilaoDomainArgumentException;

/**
 * Bean que permite enviar lances aos leilões
 * @author Leandro
 *
 */
/*
 * O bean é anotado com @Asynchronous, pois para as ações que enviam mensagens JMS
 * podem demorar , devido a disponibilidade da rede ou do broker JMS
 * Assim não prendendo as requisições
 */
@Asynchronous
@Remote(EnviarLanceBean.class)
@Stateless(mappedName = EnviarLanceBean.JNDI_NAME)
public class ManagerEnviarLanceBean implements EnviarLanceBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2599603693648851218L;

	@EJB
	private EnviarMessageLanceBean enviarMessageLanceBean;

	@EJB
	private LeilaoBean leilaoBean;
	
	@EJB
	private ItemBean itemBean;
	
	/* (non-Javadoc)
	 * @see com.fiap.leilao.business.EnviarLanceBean#enviarLance(com.fiap.leilao.domain.Lance, com.fiap.leilao.domain.Usuario)
	 */
	@Override
	public void enviarLance(Lance lance, Usuario usuario)throws LeilaoBusinessException , LeilaoDomainArgumentException{

		if(lance == null || lance.getValor().doubleValue() <= 0.0)
			throw new LeilaoDomainArgumentException("Lance inválido");

		if(lance.getLeilao() == null || lance.getLeilao().getVendedor().getId().equals(usuario.getId()))
			throw new LeilaoDomainArgumentException("Usuário não pode efetuar o lance");

		if(lance.getLeilao() == null || lance.getLeilao().getValorInicial().doubleValue() > lance.getValor().doubleValue())
			throw new LeilaoDomainArgumentException("O valor do lance é menor que o valor mínimo do leilão");
		
		
		try{

			lance.setUsuario(usuario);

			enviarMessageLanceBean.enviarObjectMessage(lance);

		}catch (Throwable e) {
			throw new LeilaoBusinessException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.fiap.leilao.business.EnviarLanceBean#buscarLeiloesAtivos(com.fiap.leilao.domain.Usuario)
	 */
	@Override
	public List<Leilao> buscarLeiloesAtivos(Usuario usuario) throws LeilaoBusinessException,LeilaoDomainArgumentException {
		try{
			return leilaoBean.pesquisaLeilaoEnviarLance(usuario);
		}catch (LeilaoDomainArgumentException e) {
			throw e;
		}
		catch (Exception e) {
			throw new LeilaoBusinessException(e.getMessage());
		}	
	}

	@Override
	public List<Item> buscarItensProduto(Produto produto)throws LeilaoBusinessException, LeilaoDomainArgumentException {
		try{
			return itemBean.getItensProduto(produto);
		}catch (LeilaoDomainArgumentException e) {
			throw e;
		}catch (Exception e) {
			throw new LeilaoBusinessException(e.getMessage());
		}
	}
}