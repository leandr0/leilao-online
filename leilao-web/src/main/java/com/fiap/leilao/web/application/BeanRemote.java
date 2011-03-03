/**
 * 
 */
package com.fiap.leilao.web.application;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.fiap.leilao.web.bean.BusinessTeste;

/**
 * @author Leandro
 *
 */
@Stateless(mappedName = "remoteBean")
@Remote(RemoteInterface.class)
public class BeanRemote implements RemoteInterface {

	@EJB
	private BusinessTeste businessTeste;
	
	/* (non-Javadoc)
	 * @see com.fiap.leilao.web.application.RemoteInterface#print()
	 */
	@Override
	public void print() {
		System.out.println("PRINT");

	}

}
