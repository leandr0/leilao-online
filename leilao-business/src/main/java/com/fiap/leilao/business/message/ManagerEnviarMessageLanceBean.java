/**
 * 
 */
package com.fiap.leilao.business.message;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.jboss.ejb3.annotation.ResourceAdapter;

/**
 * @author Leandro
 *
 */

@Asynchronous
@Remote(EnviarMessageLanceBean.class)
@Stateless(mappedName = EnviarMessageLanceBean.JNDI_NAME)
@ResourceAdapter(EnviarMessageLanceBean.RESOURCE_ADAPTER)        
public class ManagerEnviarMessageLanceBean implements EnviarMessageLanceBean {

	@Resource(mappedName = EnviarMessageLanceBean.JNDI_CONNECTION_FACTORY)
	private ConnectionFactory factory;

	@Resource(mappedName = EnviarMessageLanceBean.JNDI_QUEUE_NAME)	
	private Destination destination;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2148941833086393469L;

	@Override
	public void enviarTextMessage(String message) {

		Connection connection 	= null;
		Session session 		= null;

		try{
			connection 	= factory.createConnection(); 
			session 	= connection.createSession(true,Session.SESSION_TRANSACTED);

			TextMessage textMessage = session.createTextMessage(message);

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

	@Override
	public <M extends Serializable> void enviarObjectMessage(M message) {
		

		Connection connection 	= null;
		Session session 		= null;

		try{
			connection 	= factory.createConnection(); 
			session 	= connection.createSession(true,Session.SESSION_TRANSACTED);

			ObjectMessage objectMessage = session.createObjectMessage(message);

			session.createProducer(destination).send(objectMessage);

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

	@Override
	public void enviarTextMessageQuerySelector(String message,MessageSelect messageSelect) {


		Connection connection 	= null;
		Session session 		= null;

		try{
			connection 	= factory.createConnection(); 
			session 	= connection.createSession(true,Session.SESSION_TRANSACTED);

			TextMessage textMessage = session.createTextMessage(message);
			textMessage.setStringProperty(messageSelect.QUERYSELECTOR, messageSelect.VALUESELECTOR);
			
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

	@Override
	public <M extends Serializable> void enviarObjectMessageQuerySeletor(M message, MessageSelect messageSelect) {


		Connection connection 	= null;
		Session session 		= null;

		try{
			connection 	= factory.createConnection(); 
			session 	= connection.createSession(true,Session.SESSION_TRANSACTED);

			ObjectMessage objectMessage = session.createObjectMessage(message);
			objectMessage.setStringProperty(messageSelect.QUERYSELECTOR, messageSelect.VALUESELECTOR);
			
			session.createProducer(destination).send(objectMessage);

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