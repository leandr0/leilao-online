/**
 * 
 */
package com.fiap.leilao.business;

import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.fiap.leilao.business.event.LeilaoEvent;
import com.fiap.leilao.business.exception.LeilaoBusinessException;
import com.fiap.leilao.business.qualifier.AprovarLeilao;
import com.fiap.leilao.business.qualifier.ReprovarLeilao;
import com.fiap.leilao.domain.Item;
import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.Produto;
import com.fiap.leilao.domain.bean.ItemBean;
import com.fiap.leilao.domain.bean.LeilaoBean;
import com.fiap.leilao.domain.type.StatusLeilao;

/**
 * @author Leandro
 *
 */
@Stateless(mappedName = "autorizarLeilaoBean")
@Remote(AutorizarLeilaoBean.class)
@Asynchronous
public class ManagerAutorizarLeilaoBean implements AutorizarLeilaoBean {

	@EJB
	private LeilaoBean leilaBean;
	
	@EJB
	private ItemBean itemBean;

	@Inject
	@AprovarLeilao
	private Event<LeilaoEvent> aprovarLeilao;
	
	@Inject
	@ReprovarLeilao
	private Event<LeilaoEvent> reprovarLeilao;
	
	/* (non-Javadoc)
	 * @see com.fiap.leilao.business.AutorizarLeilaoBean#atutorizarLeilao(com.fiap.leilao.domain.Leilao)
	 */
	//TODO: Seguranca JAAS
	//TODO: Add usuario com parametro e verificar perfil usuario
	@Override
	public void atutorizarLeilao(Leilao leilao) throws LeilaoBusinessException , IllegalArgumentException{

		if(leilao == null || !leilao.getStatus().equals(StatusLeilao.AGUARDANDO_AUTORIZACAO))
			throw new IllegalArgumentException("Status do leilao invalido");

		try{

			leilao.setStatus(StatusLeilao.INICADO);
			leilaBean.update(leilao);
			
			aprovarLeilao.fire(new LeilaoEvent(leilao));
			
		}catch (Throwable e) {
			throw new LeilaoBusinessException(e);
		}


	}

	/*
	 * (non-Javadoc)
	 * @see com.fiap.leilao.business.AutorizarLeilaoBean#buscarLeiloesPendentes()
	 */
	@Override
	public List<Leilao> buscarLeiloesPendentes() throws LeilaoBusinessException {
		try{
			return leilaBean.searchLeilaoByStatus(StatusLeilao.AGUARDANDO_AUTORIZACAO);
		}catch (Exception e) {
			throw new LeilaoBusinessException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.fiap.leilao.business.AutorizarLeilaoBean#buscarItensLeilao()
	 */
	@Override
	public List<Item> buscarItensLeilao(Produto produto) throws LeilaoBusinessException {
		try{
			return itemBean.getItensProduto(produto);
		}catch (Exception e) {
			throw new LeilaoBusinessException(e);
		}
	}

	@Override
	public void cancelarLeilao(Leilao leilao) throws LeilaoBusinessException {

		if(leilao == null || !leilao.getStatus().equals(StatusLeilao.AGUARDANDO_AUTORIZACAO))
			throw new IllegalArgumentException("Status do leilao invalido");

		try{

			leilao.setStatus(StatusLeilao.CANCELADO);
			leilaBean.update(leilao);
			
			reprovarLeilao.fire(new LeilaoEvent(leilao));
			
		}catch (Throwable e) {
			throw new LeilaoBusinessException(e);
		}
	}
}