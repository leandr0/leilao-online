/**
 * 
 */
package com.fiap.leilao.domain.bean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.fiap.leilao.domain.Leilao;
import com.fiap.leilao.domain.exception.LeilaoDomainException;
import com.fiap.leilao.domain.type.StatusLeilao;

/**
 * @author Leandro
 *
 */
@Stateless(mappedName = LeilaoBean.JNDI_NAME)
@Remote(LeilaoBean.class)
public class ManagerLeilaoBean extends AbstractDomainBean<Leilao> implements LeilaoBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7444379612139013082L;

	@Override
	public List<Leilao> searchLeilaoByStatus(StatusLeilao statusLeilao) throws IllegalArgumentException, LeilaoDomainException {

		if(statusLeilao == null)
			throw new IllegalArgumentException("Status invalido para pesquisa");
		
		try{
			CriteriaBuilder 		criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Leilao> 	criteriaQuery 	= criteriaBuilder.createQuery(Leilao.class);
			Root<Leilao> 			root			= criteriaQuery.from(Leilao.class);
			Predicate				condition		= criteriaBuilder.equal(root.get("status"),statusLeilao);

			criteriaQuery.where(condition);
			TypedQuery<Leilao> typedQuery = entityManager.createQuery(criteriaQuery);

			return typedQuery.getResultList();
		}catch (Exception e) {
			throw new LeilaoDomainException(e);
		}
	}

	@Override
	public List<Long> searchFinalizarLeilao() throws LeilaoDomainException {
		
		Connection conn = null;
		
		try{
		
			conn = dataSource.getConnection();
			
			PreparedStatement querySearch = conn.prepareStatement("SELECT ID FROM LEILAO WHERE DT_FINAL < ?");
			
			querySearch.setDate(1, getSqlDate());
			
			ResultSet resultSet = querySearch.executeQuery();
			
			List<Long> result = new LinkedList<Long>();
			
			while(resultSet.next()){
				result.add(resultSet.getLong(1));
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
	
	
	private Date getSqlDate(){
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.HOUR,0);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		
		return new Date(calendar.getTimeInMillis());
	}

	@Override
	public void updateLeiloesFinalizados(List<Long> leiloesFinalizados) throws LeilaoDomainException {
		
		Connection conn = null;
		
		try{
		
			conn = dataSource.getConnection();
			
			PreparedStatement queryUpdate = conn.prepareStatement("UPDATE LEILAO  SET  STATUS = ? WHERE ID = ?");
			
			int batch = 0;
			
			for (Long idLeilao : leiloesFinalizados) {
			
				queryUpdate.setInt(1, StatusLeilao.FINALIZADO.ordinal());
				queryUpdate.setLong(2, idLeilao);
				
				queryUpdate.addBatch();
				
				if(batch % 10 == 0)
					queryUpdate.executeBatch();
				
				batch++;
			}
			
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