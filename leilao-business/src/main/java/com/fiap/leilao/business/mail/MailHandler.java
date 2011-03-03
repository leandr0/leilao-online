/**
 * 
 */
package com.fiap.leilao.business.mail;

import javax.enterprise.event.Observes;

import com.fiap.leilao.business.event.LeilaoEvent;
import com.fiap.leilao.business.qualifier.AprovarLeilao;
import com.fiap.leilao.business.qualifier.ReprovarLeilao;

/**
 * @author Leandro
 *
 */
public class MailHandler {

	private LeilaoMail leilaoMail = new ManagerLeilaoMail();

	public void aprovarLeilao(@Observes @AprovarLeilao LeilaoEvent event){

		try{
			
			leilaoMail.addEmailTo(event.getLeilao().getVendedor().getEmail())
			.addSubject("LEILAO APROVADO !!!")
			.enviarMensagem("SEU LEILAO NUMERO : [ "+event.getLeilao().getId()+" ] FOI ACEITO");
			
		}catch (Throwable e) {
			//TODO : LOG
			e.printStackTrace();
		}
	}
	
	public void reprovarLeilao(@Observes @ReprovarLeilao LeilaoEvent event){

		try{

			leilaoMail.addEmailTo(event.getLeilao().getVendedor().getEmail())
			.addSubject("LEILAO REPROVADO !!!")
			.enviarMensagem("SEU LEILAO NUMERO : [ "+event.getLeilao().getId()+" ] NÃO FOI ACEITO");
			
		}catch (Throwable e) {
			//TODO : LOG
			e.printStackTrace();
		}
	}

}