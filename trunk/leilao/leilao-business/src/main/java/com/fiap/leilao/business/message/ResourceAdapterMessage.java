/**
 * 
 */
package com.fiap.leilao.business.message;

import java.io.Serializable;

/**
 * @author Leandro
 *
 */
public interface ResourceAdapterMessage extends Serializable {

	/**
	 * 
	 */
	public static final String RESOURCE_ADAPTER = "activemq.rar"; 
	
	/**
	 * 
	 */
	public static final String JNDI_CONNECTION_FACTORY = "java:/QueueConnectionFactory";
	
	/**
	 * 
	 */
	public static final String JNDI_QUEUE_NAME = "/queue/recebeLanceLeilao";
}
