/**
 * 
 */
package com.fiap.leilao.domain;

/**
 * @author Leandro
 *
 */
public class Lance implements EntityBasic<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7172842808673978566L;

	private Usuario usuario;
	
	private Leilao leilao;
	
	/* (non-Javadoc)
	 * @see com.fiap.leilao.domain.EntityBasic#getId()
	 */
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.fiap.leilao.domain.EntityBasic#setId(java.lang.Object)
	 */
	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Leilao getLeilao() {
		return leilao;
	}

	public void setLeilao(Leilao leilao) {
		this.leilao = leilao;
	}
}
