/**
 * 
 */
package com.fiap.leilao.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fiap.leilao.domain.type.StatusLeilao;

/**
 * @author Leandro
 *
 */
@Entity
@Table(name = "LEILAO")
public class Leilao implements EntityBasic<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5568890762367416768L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "VENDEDOR_ID" , nullable = false)
	private Usuario vendedor;
	
	@ManyToOne
	@JoinColumn(name = "COMPRADOR_ID")
	private Usuario comprador;
	
	@OneToMany(mappedBy = "leilao")
	private List<Lance> lances;
	
	@OneToOne(mappedBy = "leilao", cascade = CascadeType.ALL)
	private Produto produto;
	
	@Column(name = "STATUS" , nullable = false)
	private StatusLeilao status;
	
	@Column(name = "VALOR_INICIAL" , nullable = false)
	private Double valorInicial;
	
	@Column(name = "DT_FINAL" , nullable = false)
	private Date dataFinal;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}

	public List<Lance> getLances() {
		return lances;
	}

	public void setLances(List<Lance> lances) {
		this.lances = lances;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public StatusLeilao getStatus() {
		return status;
	}

	public void setStatus(StatusLeilao status) {
		this.status = status;
	}

	public Double getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(Double valorInicial) {
		//TODO: formatar com duas casas decimais
		this.valorInicial = valorInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}	
}