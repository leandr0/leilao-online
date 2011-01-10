/**
 * 
 */
package com.fiap.leilao.domain.bean;

import java.io.Serializable;

import com.fiap.leilao.domain.EntityBasic;

/**
 * @author Leandro
 *
 */
public interface AbstractDomain <E extends EntityBasic<?>> extends Serializable{

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public E insert(E entity);
	
	/**
	 * 
	 * @param entity
	 */
	public void delete(E entity);
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public E find(E entity);
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public E update(E entity);
}
