/**
 * 
 */
package com.fiap.leilao.business.mail;

/**
 * Enum que contém todos os templates de e-mail usados na aplicação
 * @author Leandro
 *
 */
public enum TemplateMessageMail {

	
	GANHADOR_LEILAO_MAIL("<html><body>Prezado , $NOME .<br/>É com grande satisfação que informamos que você foi o grande ganhador do leilão de descrição $DESCRICAO , código $CODIGO .<br/>Segue anexo o boleto com os dados do leilão.<br/><br/> Atenciosamente , <br/><br/>Equipe FIAP LEILÃO.</body></html>"),
	REJEITAR_LEILAO_MAIL("<html><body>Prezado , $NOME .<br/>Informamos que seu leilão de descrição $DESCRICAO , código $CODIGO .<br/>Foi rejeitado.<br/><br/> Atenciosamente , <br/><br/>Equipe FIAP LEILÃO.</body></html>"),
	APROVAR_LEILAO_MAIL("<html><body>Prezado , $NOME .<br/>Informamos que seu leilão de descrição $DESCRICAO , código $CODIGO .<br/>Foi aprovado.<br/><br/> Atenciosamente , <br/><br/>Equipe FIAP LEILÃO.</body></html>");
	
	private TemplateMessageMail(String template){
		this.template = template;
	}
	
	public String template;
}
