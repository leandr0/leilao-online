/**
 * 
 */
package com.fiap.leilao.business.mail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * @author Leandro
 *
 */
public interface LeilaoMail {

	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws MessagingException
	 */
	public LeilaoMail addAnexo(String fileName) throws MessagingException;

	/**
	 * 
	 * @param mensagem
	 * @throws MessagingException
	 */
	public void enviarMensagem(String mensagem) throws MessagingException;

	/**
	 * 
	 * @param from
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public LeilaoMail addEmailFrom(String from) throws AddressException, MessagingException;

	/**
	 * 
	 * @param copia
	 * @return
	 * @throws MessagingException
	 */
	public LeilaoMail addCopia(String copia) throws MessagingException;
	
	/**
	 * 
	 * @param to
	 * @return
	 * @throws MessagingException
	 */
	public LeilaoMail addEmailTo(String to) throws MessagingException;
	
	/**
	 * 
	 * @param subject
	 * @return
	 * @throws MessagingException
	 */
	public LeilaoMail addSubject(String subject) throws MessagingException;
}