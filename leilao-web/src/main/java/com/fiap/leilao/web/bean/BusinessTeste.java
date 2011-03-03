/**
 * 
 */
package com.fiap.leilao.web.bean;

import java.io.Serializable;
import java.util.List;

import com.fiap.leilao.domain.Leilao;

/**
 * @author Leandro
 *
 */
public interface BusinessTeste extends Serializable {

	public static final String JNDI_NAME = "businessTesteBean";
	
	public List<Leilao> getLeiloesAtivos() throws RuntimeException;
}
