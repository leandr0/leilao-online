/**
 * 
 */
package com.fiap.leilao.web.application;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.fiap.leilao.domain.bean.UsuarioBean;

/**
 * @author Leandro
 *
 */
@ManagedBean(name ="loginBean")
@SessionScoped
public class UILoginBean extends UIAbstractBean{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1155172418846104116L;

	private String login;

	private String senha;

	@EJB
	private UsuarioBean usuarioBean;

	public String logar(){

		try{
			
			setAttributeInSession("usuario",
					usuarioBean.pesquisaLoginSenha(login, senha));
			
			return "leilao-home";
		}
		catch (IllegalArgumentException e) {
			showMessageWarn(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			login = null;
			senha = null;
		}
		
		return null;
	}

	public String logout(){
		session.invalidate();
		return "home";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}