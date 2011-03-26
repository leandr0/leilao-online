/**
 * 
 */
package com.fiap.leilao.business.message;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.ejb3.annotation.ResourceAdapter;

import com.fiap.leilao.business.EnviarRelatorioBean;

/**
 * @author Leandro
 *
 */
@MessageDriven(
		activationConfig ={
				@ActivationConfigProperty(
						propertyName = "destinationType",
						propertyValue = "javax.jms.Queue"
				),
				@ActivationConfigProperty(
						propertyName = "destination",
						propertyValue = ResourceAdapterMessage.JNDI_QUEUE_RELATORIO_NAME
				)		
		}
)
@ResourceAdapter(ResourceAdapterMessage.RESOURCE_ADAPTER) 
public class ManagerListenerMessageRelatorioBean implements MessageListener {

	@EJB
	private EnviarRelatorioBean enviarRelatorioBean;
	

	@Override
	public void onMessage(Message message) {
		try{
			enviarRelatorioBean.enviarRelatorio(message.getLongProperty("ID_LEILAO"));	
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
