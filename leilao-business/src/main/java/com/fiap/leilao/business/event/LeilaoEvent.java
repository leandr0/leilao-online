/**
 * 
 */
package com.fiap.leilao.business.event;

import com.fiap.leilao.domain.Leilao;

/**
 * @author Leandro
 *
 */
public class LeilaoEvent {

	private final Leilao leilao;
	
	public LeilaoEvent(Leilao leilao) {
		this.leilao = leilao;
	}

	public Leilao getLeilao() {
		return leilao;
	}
}