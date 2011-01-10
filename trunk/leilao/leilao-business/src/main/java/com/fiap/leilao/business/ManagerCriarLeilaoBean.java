/**
 * 
 */
package com.fiap.leilao.business;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.fiap.leilao.business.exception.LeilaoBusinessException;
import com.fiap.leilao.domain.Item;
import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.Usuario;
import com.fiap.leilao.domain.bean.LeilaBean;
import com.fiap.leilao.domain.type.PerfilUsuario;
import com.fiap.leilao.domain.type.StatusLeilao;

/**
 * @author Leandro
 *
 */
@Stateless(mappedName = "criarLeilaoBean")
@Asynchronous
@Remote(CriarLeilaoBean.class)
public class ManagerCriarLeilaoBean implements CriarLeilaoBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -752521412915840616L;
	
	@EJB
	private LeilaBean leilaoBean;
	
	//TODO: Tratar acesso pelo JAAS
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long criarLeilao(Leilao leilao, Usuario usuario) throws LeilaoBusinessException , IllegalArgumentException{
		
		if(usuario == null || !usuario.getPerfil().equals(PerfilUsuario.VENDEDOR) )
			throw new IllegalArgumentException("Usuario invalido para a operacao");
		
		try{
			
			leilao.setVendedor(usuario);
			leilao.setStatus(StatusLeilao.AGUARDANDO_AUTORIZACAO);
			leilao.getProduto().setLeilao(leilao);
			
			for(Item item : leilao.getProduto().getItens()){
				item.setProduto(leilao.getProduto());
			}
			
			leilao = leilaoBean.insert(leilao);
			
			return leilao.getId();
			
		}catch (Throwable e) {
			throw new LeilaoBusinessException(e);
		}

	}
}
