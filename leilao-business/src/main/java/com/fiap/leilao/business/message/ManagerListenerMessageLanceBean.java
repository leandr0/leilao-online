/**
 * 
 */
package com.fiap.leilao.business.message;

import java.util.Calendar;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.jboss.ejb3.annotation.ResourceAdapter;

import com.fiap.leilao.domain.Lance;
import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.bean.LanceBean;


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
						propertyValue = ResourceAdapterMessage.JNDI_QUEUE_NAME
				)		
		}
)
@ResourceAdapter(ResourceAdapterMessage.RESOURCE_ADAPTER)        
public class ManagerListenerMessageLanceBean implements MessageListener {
	
	@EJB
	private LanceBean lanceBean;
	

	@Override
	public void onMessage(Message message) {
		try{
			
			ObjectMessage objectMessage = (ObjectMessage) message;
			
			Lance lance = (Lance) objectMessage.getObject();
			lance.setDataLance(Calendar.getInstance().getTime());
			
			lanceBean.insert(lance);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
