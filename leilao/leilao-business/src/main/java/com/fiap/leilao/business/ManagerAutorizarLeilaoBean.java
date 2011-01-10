/**
 * 
 */
package com.fiap.leilao.business;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.fiap.leilao.business.exception.LeilaoBusinessException;
import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.bean.LeilaBean;
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
	private LeilaBean leilaBean;
	
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
			
		}catch (Throwable e) {
			throw new LeilaoBusinessException(e);
		}
		
		
	}

}
