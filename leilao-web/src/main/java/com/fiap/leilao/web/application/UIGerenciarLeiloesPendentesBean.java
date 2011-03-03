/**
 * 
 */
package com.fiap.leilao.web.application;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.fiap.leilao.business.AutorizarLeilaoBean;
import com.fiap.leilao.domain.Leilao;

/**
 * @author Leandro
 *
 */
@ManagedBean(name = "gerenciarLeiloesPendentesBean")
@SessionScoped
public class UIGerenciarLeiloesPendentesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6341552079491406364L;

	@EJB
	private AutorizarLeilaoBean autorizarLeilaoBean;

	private Leilao leilao;

	private List<Leilao> leiloesPendentes;

	public UIGerenciarLeiloesPendentesBean() {
		leiloesPendentes = new LinkedList<Leilao>();
	}

	public String init(){

		listarLeiloesPendentes();

		return "gerenciar-lance-leilao";
	}

	public String listarLeiloesPendentes(){

		leiloesPendentes = autorizarLeilaoBean.buscarLeiloesPendentes();

		return null;
	}

	public String autorizarLeilaoSelecionado(){

		if(leilao != null){
			if(leilao.getProduto() != null)
				System.out.println(leilao.getProduto().getDescricao());
		}

		autorizarLeilaoBean.atutorizarLeilao(leilao);

		listarLeiloesPendentes();

		return null;
	}

	
	public String cancelarLeilaoSelecionado(){

		if(leilao != null){
			if(leilao.getProduto() != null)
				System.out.println(leilao.getProduto().getDescricao());
		}

		autorizarLeilaoBean.cancelarLeilao(leilao);

		listarLeiloesPendentes();

		return null;
	}
	
	public String selecionarLeilao(){
		
		String idLeilao = FacesContext.getCurrentInstance().getExternalContext()
											.getRequestParameterMap().get("leilaoAction");

		if(idLeilao != null){
			for (Leilao ll : leiloesPendentes) {
				if(Long.valueOf(idLeilao).longValue() == ll.getId().longValue()){
					leilao = ll;
					break;
				}
			}
		}
		
		leilao.getProduto().setItens(
				autorizarLeilaoBean.buscarItensLeilao(leilao.getProduto()));
		
		return null;
	}

	public String rejeitarLeilao(){

		if(leilao != null){
			if(leilao.getProduto() != null)
				System.out.println(leilao.getProduto().getDescricao());
		}

		autorizarLeilaoBean.atutorizarLeilao(leilao);

		listarLeiloesPendentes();
		
		return null;
	}
	
	public Leilao getLeilao() {
		return leilao;
	}

	public void setLeilao(Leilao leilao) {
		this.leilao = leilao;
	}

	public List<Leilao> getLeiloesPendentes() {
		return leiloesPendentes;
	}

	public void setLeiloesPendentes(List<Leilao> leiloesPendentes) {
		this.leiloesPendentes = leiloesPendentes;
	}
}