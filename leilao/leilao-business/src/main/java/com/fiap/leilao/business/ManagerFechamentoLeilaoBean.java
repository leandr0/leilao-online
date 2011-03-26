/**
 * 
 */
package com.fiap.leilao.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fiap.leilao.business.exception.FinalizarLeilaoBusinessException;
import com.fiap.leilao.business.message.EnviarSolicitacaoRelatorioBean;
import com.fiap.leilao.domain.Lance;
import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.Usuario;
import com.fiap.leilao.domain.bean.LanceBean;
import com.fiap.leilao.domain.bean.LeilaoBean;
import com.fiap.leilao.domain.exception.LeilaoDomainArgumentException;
import com.fiap.leilao.domain.exception.LeilaoDomainException;

/**
 * Bean que possibilita executar a rotina noturna de fechamento de Leilão
 * 
 * @author Leandro
 *
 */
@Stateless(mappedName = FechamentoLeilaoBean.JNDI_NAME)
@Local(FechamentoLeilaoBean.class)
public class ManagerFechamentoLeilaoBean implements FechamentoLeilaoBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5363096927828143211L;

	private static final Log LOG = LogFactory.getLog(ManagerFechamentoLeilaoBean.class);
	
	@EJB
	private LeilaoBean leilaoBean;
	
	@EJB
	private LanceBean lanceBean;
	
	@EJB
	private EnviarSolicitacaoRelatorioBean enviarSolicitacaoRelatorioBean;
	
	/*
	 * (non-Javadoc)
	 * @see com.fiap.leilao.business.FechamentoLeilaoBean#finalizarLeilao()
	 */
	@Override
	@Schedule(hour="00", minute="00")
	public void finalizarLeilao() throws FinalizarLeilaoBusinessException {
		
		try{
			
			LOG.info("Iniciando processo Schedule para finalizar leilao");

			List<Long> leiloes = leilaoBean.pesquisaFinalizarLeilao();
			LOG.info("Numeros de leiloes finalizados "+(leiloes != null ? leiloes.size() : 0));
			
			LOG.info("Iniciando processo de atualizacao de leiloes");
			leilaoBean.updateLeiloesFinalizados(leiloes);
			
	
			for (Long idLeilao : leiloes) {
				
				try{
				
					atribuirGanhadorLeilao(idLeilao);
					
					LOG.info("Enviando solicitacao de relatorio para fila");
					enviarSolicitacaoRelatorioBean.enviarSolicitacaoRelatorioEmail(idLeilao);
					
				}catch (Exception e) {
					LOG.error("Erro no precesso de finalizacao do leilao id : "+idLeilao);
				}
			}
			
		}catch (Throwable e) {
			LOG.error("Erro ao finalizar leilao : "+e.getMessage());
			throw new FinalizarLeilaoBusinessException(e.getMessage());
		}		
	}
	
	/**
	 * Faz chamada da query que retorna o maior lance para o {@link Leilao} com p ID informado,<p>
	 * e atribui ao {@link Leilao} o {@link Usuario} como ganhador 
	 * @param idLeilao {@link Long}
	 * @throws LeilaoDomainArgumentException
	 * @throws LeilaoDomainException
	 */
	private void atribuirGanhadorLeilao(Long idLeilao) throws LeilaoDomainArgumentException, LeilaoDomainException{
		
		LOG.info("Atribuindo ganhador ao leilao");
		
		Long idLance = lanceBean.pesquisarMaiorLanceLeilao(idLeilao);
		
		Lance lance = new Lance();
		lance.setId(idLance);
		
		lance = lanceBean.find(lance);
		
		lance.getLeilao().setComprador(lance.getUsuario());
		lance.getLeilao().setValorGanhador(lance.getValor());
		
		leilaoBean.update(lance.getLeilao());
	}
}