/**
 * 
 */
package com.fiap.leilao.domain.bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fiap.leilao.domain.EntityBasic;

/**
 * @author Leandro
 *
 */
public abstract class AbstractDomainBean<E extends EntityBasic<?>> implements AbstractDomain<E>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4875021596342511756L;
	
	
	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public E insert(E entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public void delete(E entity) {
		try{
			entity = find(entity);
			entityManager.remove(entity);
		}catch (Throwable e) {
			System.err.println(e.getMessage());
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	public E find(E entity) {
		return (E) entityManager.find(entity.getClass(), entity.getId());
	}

	@Override
	public E update(E entity) {
		return entityManager.merge(entity);
	}

	/**
	 * Usado em testes unitarios
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}	
}