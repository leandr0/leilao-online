/**
 * 
 */
package com.fiap.leilao.web.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.bean.LeilaoBean;
import com.fiap.leilao.domain.type.StatusLeilao;

/**
 * @author Leandro
 *
 */
@Stateless(mappedName = BusinessTeste.JNDI_NAME)
@Remote(BusinessTeste.class)
public class BusinessTesteImpl implements BusinessTeste{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2028949726045140994L;
	
	
	@EJB(mappedName = LeilaoBean.JNDI_NAME)
	private LeilaoBean leilaoBean;

	@Override
	public List<Leilao> getLeiloesAtivos() throws RuntimeException {
		try{
			return leilaoBean.searchLeilaoByStatus(StatusLeilao.INICADO);
		}catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
}
