package com.parking.model.business.service;

import java.util.List;

public interface DAO<T> {

	int[] add(T item);

	int[] add(Iterable<T> items);
	
	Integer update(T item);

	Integer remove(T item);
	
	T get(Long id);

	List<T> getAll();
}