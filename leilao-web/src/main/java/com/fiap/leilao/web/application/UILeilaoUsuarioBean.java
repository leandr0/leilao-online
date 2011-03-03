/**
 * 
 */
package com.fiap.leilao.web.application;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.Usuario;
import com.fiap.leilao.domain.bean.UsuarioBean;

/**
 * @author Leandro
 *
 */
@ManagedBean(name = "ui_leilaousuario_bean")
@SessionScoped
public class UILeilaoUsuarioBean extends UIAbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4664472160381519370L;

	private List<Leilao> leiloesUsuario;

	@EJB
	private UsuarioBean usuarioBean;

	public String carregarLeilaoUsuario(){

		try{
			Usuario usuario = (Usuario) getAttributeInSession("usuario");

			leiloesUsuario = usuarioBean.searchLeiloesUsuario(usuario);
		}catch (Exception e) {
			// TODO: Tratar
		}
		return "leiloes-usuario";
	}

	public String carregarLeilaoLance(){
		try{
			Usuario usuario = (Usuario) getAttributeInSession("usuario");

			leiloesUsuario = usuarioBean.searchLeiloesLance(usuario);
		}catch (Exception e) {
			// TODO: Tratar
		}
		return "leiloes-usuario";
	}

	public List<Leilao> getLeiloesUsuario() {
		return leiloesUsuario;
	}

	public void setLeiloesUsuario(List<Leilao> leiloesUsuario) {
		this.leiloesUsuario = leiloesUsuario;
	}
}
