/**
 * 
 */
package com.fiap.leilao.business;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.fiap.leilao.business.exception.LeilaoBusinessException;
import com.fiap.leilao.domain.Lance;
import com.fiap.leilao.domain.Usuario;
import com.fiap.leilao.domain.bean.LanceBean;

/**
 * @author Leandro
 *
 */
@Stateless(mappedName = "enviarLanceBean")
@Remote(EnviarLanceBean.class)
@Asynchronous
public class ManagerEnviarLanceBean implements EnviarLanceBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5560596108270139544L;

	@EJB
	private LanceBean lanceBean;
	
	
	/* (non-Javadoc)
	 * @see com.fiap.leilao.business.EnviarLanceBean#enviarLance(com.fiap.leilao.domain.Lance, com.fiap.leilao.domain.Usuario)
	 */
	@Override
	public Long enviarLance(Lance lance, Usuario usuario)throws LeilaoBusinessException , IllegalArgumentException{
		
		if(lance == null || lance.getValor().doubleValue() <= 0.0)
			throw new IllegalArgumentException("Lance invalido");
		
		if(lance.getLeilao() == null || lance.getLeilao().getVendedor().getId().equals(usuario.getId()))
			throw new IllegalArgumentException("Usuario nao pode efetuar o lance");
		
		try{
			
			lance.setUsuario(usuario);
			
			lanceBean.insert(lance);
			
			return lance.getId();
			
		}catch (Throwable e) {
			throw new LeilaoBusinessException(e);
		}
	}
}