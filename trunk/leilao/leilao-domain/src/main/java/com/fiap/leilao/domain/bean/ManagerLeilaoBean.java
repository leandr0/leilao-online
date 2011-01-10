/**
 * 
 */
package com.fiap.leilao.domain.bean;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.fiap.leilao.domain.Leilao;

/**
 * @author Leandro
 *
 */
@Stateless(mappedName = "leilaoBean")
@Remote(LeilaBean.class)
public class ManagerLeilaoBean extends AbstractDomainBean<Leilao> implements LeilaBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1733626237243895505L;
}
