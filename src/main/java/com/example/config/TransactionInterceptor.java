package com.example.config;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Interceptor
@Transactional
@Priority(Interceptor.Priority.APPLICATION)
public class TransactionInterceptor {

	@Inject
	private EntityManager entityManager;
	
	@AroundInvoke
	public Object manageTransaction(InvocationContext ctx) throws Exception {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			Object result = ctx.proceed();
			transaction.commit();
			return result;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}
}
