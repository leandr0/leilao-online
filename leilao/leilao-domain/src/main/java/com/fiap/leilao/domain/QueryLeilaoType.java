/**
 * 
 */
package com.fiap.leilao.domain;

import org.hibernate.cfg.NotYetImplementedException;

/**
 * Enum que cont�m as queries nativas .<p>
 * A decis�o arquitetural de utilizar as queries nativas em <p>
 * enumerations � devido a configura��o das mesmas em um arquivo <p>
 * orm.xml utilizando o hibernate como provider � o lan�amento da <p>
 * exce��o {@link NotYetImplementedException}
 * 
 * @author Leandro
 *
 */
public enum QueryLeilaoType {

	
	PESQUISA_FINALIZAR_LEILAO("SELECT ID FROM LEILAO WHERE DT_FINAL < ? AND LL.STATUS = 1 "),
	
	UPDATE_LISTA_FINALIZAR_LEILAO("UPDATE LEILAO  SET  STATUS = ? WHERE ID = ?"),
	
	PESQUISA_GANHADOR_LEILAO(" SELECT LL.ID , PROD.DESCRICAO,USU.NOME , USU.EMAIL  " +
							" FROM LEILAO LL INNER JOIN USUARIO USU " +
							" ON LL.COMPRADOR_ID = USU.ID " +
							" INNER JOIN PRODUTO PROD	" +
							" ON PROD.LEILAO_ID = LL.ID "+
							" WHERE LL.ID = ? ");
	
	private QueryLeilaoType(String nativeQuery){
		this.nativeQuery = nativeQuery;
	}
	
	public String nativeQuery;
}
