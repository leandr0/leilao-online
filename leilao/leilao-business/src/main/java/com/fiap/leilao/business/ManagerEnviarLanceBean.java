/**
 * 
 */
package com.fiap.leilao.business;

import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.fiap.leilao.business.exception.LeilaoBusinessException;
import com.fiap.leilao.business.mail.ManagerLeilaoMail;
import com.fiap.leilao.business.message.EnviarMessageLanceBean;
import com.fiap.leilao.domain.Item;
import com.fiap.leilao.domain.Lance;
import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.Produto;
import com.fiap.leilao.domain.Usuario;
import com.fiap.leilao.domain.bean.ItemBean;
import com.fiap.leilao.domain.bean.LeilaoBean;
import com.fiap.leilao.domain.type.StatusLeilao;

/**
 * @author Leandro
 *
 */
@Asynchronous
@Remote(EnviarLanceBean.class)
@Stateless(mappedName = EnviarLanceBean.JNDI_NAME)
public class ManagerEnviarLanceBean implements EnviarLanceBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5560596108270139544L;

	@EJB
	private EnviarMessageLanceBean enviarMessageLanceBean;

	@EJB
	private LeilaoBean leilaoBean;
	
	@EJB
	private ItemBean itemBean;
	
	/* (non-Javadoc)
	 * @see com.fiap.leilao.business.EnviarLanceBean#enviarLance(com.fiap.leilao.domain.Lance, com.fiap.leilao.domain.Usuario)
	 */
	@Override
	public Long enviarLance(Lance lance, Usuario usuario)throws LeilaoBusinessException , IllegalArgumentException{

		if(lance == null || lance.getValor().doubleValue() <= 0.0)
			throw new IllegalArgumentException("Lance invalido");
		//TODO : descomentar
		if(lance.getLeilao() == null /*|| lance.getLeilao().getVendedor().getId().equals(usuario.getId())*/)
			throw new IllegalArgumentException("Usuario nao pode efetuar o lance");

		try{

			lance.setUsuario(usuario);

			enviarMessageLanceBean.enviarObjectMessage(lance);

			return 0L;

		}catch (Throwable e) {
			throw new LeilaoBusinessException(e);
		}
	}

	@Override
	public void enviarEmail(String mensagem) throws LeilaoBusinessException,IllegalArgumentException {
		try{

			new ManagerLeilaoMail()
			.addEmailTo("leandro1604@gmail.com")
			.addSubject("JBOSS MAIL")
			.enviarMensagem(mensagem);
			
		}catch (Exception e) {
			throw new LeilaoBusinessException(e);
		}
	}

	@Override
	public List<Leilao> buscarLeiloesAtivos() throws LeilaoBusinessException,IllegalArgumentException {
		try{
			return leilaoBean.searchLeilaoByStatus(StatusLeilao.INICADO);
		}catch (IllegalArgumentException e) {
			throw e;
		}
		catch (Exception e) {
			throw new LeilaoBusinessException(e.getMessage());
		}	
	}

	@Override
	public List<Item> buscarItensProduto(Produto produto)throws LeilaoBusinessException, IllegalArgumentException {
		try{
			return itemBean.getItensProduto(produto);
		}catch (IllegalArgumentException e) {
			throw e;
		}catch (Exception e) {
			throw new LeilaoBusinessException(e.getMessage());
		}
	}
}