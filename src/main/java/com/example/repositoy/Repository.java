package com.example.repositoy;

import java.util.List;

public interface Repository<T, ID> {

	T save(T entity);
	T find(ID id);
	List<T> findAll();
	T update(T entity);
	void delete(T entity);
}
