package com.example.service;

import java.util.List;

import com.example.config.Transactional;
import com.example.model.entity.Dominio;
import com.example.repositoy.DominioRepository;

import jakarta.inject.Inject;

public class DominioService {

	@Inject
	private DominioRepository dominioRepository;

	@Transactional
	public Dominio save(Dominio dominio) {
		return dominioRepository.save(dominio);
	}

	public Dominio find(Long id) {
		return dominioRepository.find(id);
	}

	public List<Dominio> findAll() {
		return dominioRepository.findAll();
	}

	@Transactional
	public Dominio update(Dominio dominio) {
		return dominioRepository.update(dominio);
	}

	@Transactional
	public void delete(Dominio dominio) {
		dominioRepository.delete(dominio);
	}
}
