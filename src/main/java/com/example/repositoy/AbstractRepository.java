package com.example.repositoy;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public abstract class AbstractRepository<T, ID> implements Repository<T, ID> {

	@Inject
	private EntityManager em;

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public AbstractRepository() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Override
	public T save(T entity) {
		try {
			em.persist(entity);
			em.flush();
			return entity;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar a entidade", e);
		}
	}

	@Override
	public T find(ID id) {
		try {
			return em.find(entityClass, id);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao encontrar a entidade", e);
		}
	}

	@Override
	public List<T> findAll() {
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<T> query = builder.createQuery(entityClass);
			Root<T> root = query.from(entityClass);

			CriteriaQuery<T> all = query.select(root);
			TypedQuery<T> allQuery = em.createQuery(all);

			return allQuery.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao listar todas as entidades", e);
		}
	}

	@Override
	public T update(T entity) {
		try {
			T mergedEntity = em.merge(entity);
			em.flush();
			return mergedEntity;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao atualizar a entidade", e);
		}
	}

	@Override
	public void delete(T entity) {
		try {
			em.remove(em.contains(entity) ? entity : em.merge(entity));
		} catch (Exception e) {
			throw new RuntimeException("Erro ao excluir a entidade", e);
		}
	}

}
