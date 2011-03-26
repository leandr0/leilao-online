/**
 * 
 */
package com.fiap.leilao.business.message;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.jboss.ejb3.annotation.ResourceAdapter;

/**
 * @author Leandro
 *
 */
@Asynchronous
@Remote(EnviarSolicitacaoRelatorioBean.class)
@Stateless(mappedName = EnviarSolicitacaoRelatorioBean.JNDI_NAME)
@ResourceAdapter(EnviarSolicitacaoRelatorioBean.RESOURCE_ADAPTER)  
public class ManagerEnviarSolicitacaoRelatorioBean implements EnviarSolicitacaoRelatorioBean {

	
	@Resource(mappedName = EnviarMessageLanceBean.JNDI_CONNECTION_FACTORY)
	private ConnectionFactory factory;

	@Resource(mappedName = EnviarMessageLanceBean.JNDI_QUEUE_RELATORIO_NAME)	
	private Destination destination;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6508485036335241228L;

	@Override
	public void enviarSolicitacaoRelatorioEmail(Long idLeilao) {
		
		Connection connection 	= null;
		Session session 		= null;

		try{
		
			connection 	= factory.createConnection(); 
			session 	= connection.createSession(true,Session.SESSION_TRANSACTED);

			TextMessage textMessage = session.createTextMessage();
			textMessage.setLongProperty("ID_LEILAO", idLeilao);
			
			session.createProducer(destination).send(textMessage);

		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally{
			try{
				session.close();
				connection.close();
			}catch (Exception e) {
				System.err.println(e);
			}
		}
		
	}

}
