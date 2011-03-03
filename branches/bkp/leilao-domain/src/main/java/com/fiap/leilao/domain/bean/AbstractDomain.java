/**
 * 
 */
package com.fiap.leilao.domain.bean;

import java.io.Serializable;

import com.fiap.leilao.domain.EntityBasic;
import com.fiap.leilao.domain.exception.LeilaoDomainException;

/**
 * @author Leandro
 *
 */
public interface AbstractDomain <E extends EntityBasic<?>> extends Serializable{

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws IllegalArgumentException
	 * @throws LeilaoDomainException
	 */
	public E insert(E entity)throws IllegalArgumentException , LeilaoDomainException;
	
	/**
	 * 
	 * @param entity
	 * @throws IllegalArgumentException
	 * @throws LeilaoDomainException
	 */
	public void delete(E entity)throws IllegalArgumentException , LeilaoDomainException;
	
	/**
	 * 
	 * @param entity
	 * @return
	 * @throws IllegalArgumentException
	 * @throws LeilaoDomainException
	 */
	public E find(E entity)throws IllegalArgumentException , LeilaoDomainException;
	
	/**
	 * 
	 * @param entity
	 * @return
	 * @throws IllegalArgumentException
	 * @throws LeilaoDomainException
	 */
	public E update(E entity)throws IllegalArgumentException , LeilaoDomainException;
}
