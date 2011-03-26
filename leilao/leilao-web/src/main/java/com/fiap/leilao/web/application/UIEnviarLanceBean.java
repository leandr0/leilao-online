/**
 * 
 */
package com.fiap.leilao.web.application;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.fiap.leilao.business.EnviarLanceBean;
import com.fiap.leilao.business.exception.LeilaoBusinessException;
import com.fiap.leilao.domain.Lance;
import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.Usuario;
import com.fiap.leilao.domain.exception.LeilaoDomainArgumentException;

/**
 * @author Leandro
 *
 */
@ManagedBean(name = "enviarLanceBean")
@SessionScoped
public class UIEnviarLanceBean extends UIAbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3672735589571567484L;

	private List<Leilao> leiloesAtivos;

	private Leilao leilao;

	private Lance lance = new Lance();

	@EJB
	private EnviarLanceBean enviarLanceBean;

	public String listarLeiloes(){

		try{
			leiloesAtivos = enviarLanceBean.buscarLeiloesAtivos(getUsuario());
		}catch (Throwable e) {
			System.err.println(e.getMessage());
		}

		return "enviar-lance-leilao";
	}

	public String selecionarLeilao(){

		String idLeilao = getExternalContext().getRequestParameterMap().get("leilaoAction");

		if(idLeilao != null){
			for (Leilao ll : leiloesAtivos) {
				if(Long.valueOf(idLeilao).longValue() == ll.getId().longValue()){
					leilao = ll;
					break;
				}
			}
		}

		try {
			leilao.getProduto().setItens(
					enviarLanceBean.buscarItensProduto(leilao.getProduto()));
		} catch (LeilaoBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LeilaoDomainArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String participarLeilao(){

		try{

			if(lance.getValor().doubleValue() <= 0.0){
				showMessageWarn("O valor do lance deve ser maior que zero");
			}else if(lance.getValor().doubleValue() < leilao.getValorInicial().doubleValue()){
				showMessageWarn("O valor do lance deve ser maior ou igual ao valor inicial do leilão");
			}else{

				Usuario usu = getUsuario();
				lance.setLeilao(leilao);
				enviarLanceBean.enviarLance(lance, usu);

				showMessageInfo("Lance enviado com sucesso!");
			}
		}catch (LeilaoDomainArgumentException e) {
			showMessageWarn(e.getMessage());
		}
		catch (Throwable e) {
			showMessageError("Ocorreu um erro ao enviar o lance ao leilão.Por favor entre em contato com o Administrador do sistema");
		}finally{
			lance = new Lance();
		}

		return null;
	}

	public List<Leilao> getLeiloesAtivos() {
		return leiloesAtivos;
	}

	public void setLeiloesAtivos(List<Leilao> leiloesAtivos) {
		this.leiloesAtivos = leiloesAtivos;
	}

	public Leilao getLeilao() {
		return leilao;
	}

	public void setLeilao(Leilao leilao) {
		this.leilao = leilao;
	}

	public Lance getLance() {
		return lance;
	}

	public void setLance(Lance lance) {
		this.lance = lance;
	}
}