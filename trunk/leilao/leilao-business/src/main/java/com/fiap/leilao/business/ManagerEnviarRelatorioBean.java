/**
 * 
 */
package com.fiap.leilao.business;

import java.sql.Connection;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fiap.leilao.business.mail.ManagerLeilaoMail;
import com.fiap.leilao.business.mail.TemplateMessageMail;
import com.fiap.leilao.business.report.GerarRelatorio;
import com.fiap.leilao.business.report.GerarRelatorioBoleto;
import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.bean.LeilaoBean;

/**
 * Bean que possibilita o envio de relatórios por e-mail
 * 
 * @author Leandro
 *
 */
@Stateless(mappedName = EnviarRelatorioBean.JNDI_NAME)
@Local(EnviarRelatorioBean.class)
public class ManagerEnviarRelatorioBean implements EnviarRelatorioBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8149832463831036153L;
	
	private static final Log LOG = LogFactory.getLog(ManagerEnviarRelatorioBean.class);

	@Resource(mappedName = "java:LeilaoDS")
	protected DataSource dataSource;

	@EJB
	private LeilaoBean leilaoBean;
	
	/* (non-Javadoc)
	 * @see com.fiap.leilao.business.GerarRelatorioBean#gravarRelatorio()
	 */
	@Override
	/*
	 * 
	 */
	@Asynchronous
	public void enviarRelatorio(Long idLeilao) {

		Connection connection = null;
		
		try{

			Leilao leilao = leilaoBean.pesquisarLeilaoGanhador(idLeilao);
			
			connection = dataSource.getConnection();
			
			GerarRelatorio gerarRelatorio = new GerarRelatorioBoleto(dataSource);
			
			LOG.info("Gerando relatorio");
			byte[] report = gerarRelatorio.gerarRelatorio(idLeilao);
			
			LOG.info("Enviando relatorio por e-mail");
			new ManagerLeilaoMail()
				.addEmailTo(leilao.getComprador().getEmail())
				.addAnexo(report,"Leilao-Cod-"+idLeilao+".pdf")
				.addSubject("GANHADOR LEILÃO FIAP")
				.enviarMensagemHTML(leilao, TemplateMessageMail.APROVAR_LEILAO_MAIL);


		}catch (Exception e) {
			LOG.error("Erro ao enviar relatorio", e);
		}finally{
			try{
				connection.close();
			}catch (Exception e) {
				LOG.error("Erro ao fechar conexao"+e);
			}
		}
	}
}