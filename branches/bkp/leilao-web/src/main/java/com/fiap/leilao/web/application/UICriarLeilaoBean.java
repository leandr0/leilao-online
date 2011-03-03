/**
 * 
 */
package com.fiap.leilao.web.application;

import java.util.LinkedList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;

import com.fiap.leilao.business.CriarLeilaoBean;
import com.fiap.leilao.domain.Item;
import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.Produto;
import com.fiap.leilao.domain.Usuario;
import com.fiap.leilao.domain.bean.LeilaoBean;
import com.fiap.leilao.domain.bean.UsuarioBean;

/**
 * @author Leandro
 *
 */
@ManagedBean(name = "criarLeilaoBean")
@SessionScoped
public class UICriarLeilaoBean extends UIAbstractBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6666589708640713041L;

	@EJB
	private LeilaoBean leilaoBean;

	@EJB
	private CriarLeilaoBean criarLeilaoBean;

	//TODO: mock para testes
	@EJB
	private UsuarioBean usuarioBean;

	private Leilao leilao;

	private Item 	item;

	public UICriarLeilaoBean() {
		init();
	}

	private void init(){
		leilao 	= new Leilao();
		item 	= new Item();

		leilao.setProduto(new Produto());
		leilao.getProduto().setItens(new LinkedList<Item>());
	}

	public String criarLeilao(){

		try{

			Usuario usuario = new Usuario();
			usuario.setId(1L);
			usuario = usuarioBean.find(usuario);

			Long idLeilao = criarLeilaoBean.criarLeilao(leilao, usuario);

			showMessageInfo("Leilão código : "+idLeilao+" , criado com sucesso");
			
		}catch (Throwable e) {
			showMessageError("Erro ao criar um leilão.Por favor entre em contato com o administrador do sistema.");
		}finally{
			init();
		}

		return null;
	}

	public String adicionarItem(){

		try {

			Item tmp =  (Item) BeanUtils.cloneBean(item);

			leilao.getProduto().getItens().add(tmp);

			item = new Item();

		} catch (Throwable e) {
			e.printStackTrace();
		}

		return null;
	}

	public Leilao getLeilao() {
		return leilao;
	}


	public void setLeilao(Leilao leilao) {
		this.leilao = leilao;
	}


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}

}