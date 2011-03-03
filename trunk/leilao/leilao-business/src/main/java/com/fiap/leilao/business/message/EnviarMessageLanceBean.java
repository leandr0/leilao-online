/**
 * 
 */
package com.fiap.leilao.business.message;

import java.io.Serializable;

/**
 * @author Leandro
 *
 */
public interface EnviarMessageLanceBean extends ResourceAdapterMessage {
	
	public static final String JNDI_NAME = "enviarMessageLanceBean";
	
	public void enviarTextMessage(String message);
	
	public < M extends Serializable> void enviarObjectMessage( M message);
	
	public void enviarTextMessageQuerySelector(String message, MessageSelect messageSelect);
	
	public < M extends Serializable>  void enviarObjectMessageQuerySeletor(M message, MessageSelect messageSelect);
	
	public class MessageSelect implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 4646005624361529364L;
		
		public final String QUERYSELECTOR;
		
		public final String VALUESELECTOR;
		
		public MessageSelect(String querySelector, String valueSelector) {
			this.QUERYSELECTOR = querySelector;
			this.VALUESELECTOR = valueSelector;
		}
	}
}
