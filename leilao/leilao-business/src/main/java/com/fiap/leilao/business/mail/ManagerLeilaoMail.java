/**
 * 
 */
package com.fiap.leilao.business.mail;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author Leandro
 *
 */
public class ManagerLeilaoMail implements LeilaoMail {

	private  Multipart multipart;

	private MimeMessage mailMessage;

	public ManagerLeilaoMail() {

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.host", "smtp.mail.yahoo.com");

		multipart 	= new MimeMultipart();
		mailMessage = new MimeMessage(Session.getInstance(properties, new PopupAuthenticator()));
	}

	public LeilaoMail addAnexo(String fileName) throws MessagingException{

		MimeBodyPart anexo = new MimeBodyPart();

		FileDataSource fileDataSource = new FileDataSource(fileName);
		anexo.setDataHandler(new DataHandler(fileDataSource));
		anexo.setFileName(fileDataSource.getName());

		multipart.addBodyPart(anexo);

		return this;
	}

	public void enviarMensagem(String mensagem) throws MessagingException{

		MimeBodyPart bodyMensagem = new MimeBodyPart();
		bodyMensagem.setText(mensagem);
		multipart.addBodyPart(bodyMensagem);
		mailMessage.setContent(multipart);
		mailMessage.setSentDate(new Date());
		addEmailFrom("leilao12scj@yahoo.com");
		Transport.send(mailMessage);
		
		multipart 	= new MimeMultipart();
	}

	public LeilaoMail addEmailFrom(String from) throws AddressException, MessagingException{
		mailMessage.setFrom(new InternetAddress(from));
		return this;
	}

	public LeilaoMail addCopia(String copia) throws MessagingException{
		InternetAddress[] address = {new InternetAddress(copia)};
		mailMessage.setRecipients(Message.RecipientType.CC, address);
		return this;   
	}

	public LeilaoMail addEmailTo(String to) throws MessagingException{

		InternetAddress[] address = {new InternetAddress(to)};
		mailMessage.setRecipients(Message.RecipientType.TO, address);

		return this;
	}

	public LeilaoMail addSubject(String subject) throws MessagingException{
		mailMessage.setSubject(subject);
		return this;
	}

	static class PopupAuthenticator extends Authenticator {
		
		String senha = "!112scj!1";
		
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("leilao12scj", senha);
		}
	}

}
