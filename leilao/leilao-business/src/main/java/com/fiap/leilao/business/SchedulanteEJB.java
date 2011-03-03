/**
 * 
 */
package com.fiap.leilao.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import com.fiap.leilao.domain.bean.LeilaoBean;

/**
 * @author Leandro
 *
 */
@Stateless
public class SchedulanteEJB {

	@EJB
	private LeilaoBean leilaoBean;

	@Schedule( hour = "20" , minute = "00" , second = "00" , dayOfMonth = "*"  , dayOfWeek = "*")
	public void executaSchedulante(){

		System.out.println("EXECUTANDO SCHEDULANTE MORDE A FOCA");
		try{
			List<Long> isd = leilaoBean.searchFinalizarLeilao();

			leilaoBean.updateLeiloesFinalizados(isd);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
