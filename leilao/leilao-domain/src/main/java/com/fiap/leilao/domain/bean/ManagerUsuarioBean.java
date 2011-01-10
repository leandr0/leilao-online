/**
 * 
 */
package com.fiap.leilao.domain.bean;

import javax.ejb.Asynchronous;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.fiap.leilao.domain.Usuario;

/**
 * @author Leandro
 *
 */
@Stateless(mappedName = "managerUsuarioBean")
@Remote(value = UsuarioBean.class)
@Asynchronous
public class ManagerUsuarioBean extends AbstractDomainBean<Usuario> implements UsuarioBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 161548778713781298L;

}
