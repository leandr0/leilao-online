/**
 * 
 */
package com.fiap.leilao.domain.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.fiap.leilao.domain.Lance;
import com.fiap.leilao.domain.exception.LeilaoDomainException;

/**
 * @author Leandro
 *
 */
@Stateless(mappedName = LanceBean.JNDI_NAME)
@Remote(LanceBean.class)
public class ManagerLanceBean extends AbstractDomainBean<Lance>implements LanceBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 243692927104355027L;

	@Override
	public Long getMaiorLanceLeilao(Long idLeilao) throws IllegalArgumentException, LeilaoDomainException {

		Connection conn = null;
		
		try{
			
			conn = dataSource.getConnection();
			
			PreparedStatement  queryLance = conn.prepareStatement("SELECT LC.COMPRADOR_ID , LC.VALOR , LC.DT_LANCE  FROM LANCE LC WHERE LEILAO_ID = ? ORDER BY  LC.VALOR DESC , LC.DT_LANCE ASC");
			queryLance.setMaxRows(1);
			
			queryLance.setLong(1, idLeilao);
			
			ResultSet resultSet = queryLance.executeQuery();
			
			Long result = null;
			
			while(resultSet.next()){
				result = resultSet.getLong(1);
			}
			
			return result;
			
		}catch (Throwable e) {
			throw new LeilaoDomainException(e);
		}finally{
		
			try{
				conn.close();
			}catch (Throwable e) {
				System.err.println(e.getMessage());
			}
		}	
	}
}