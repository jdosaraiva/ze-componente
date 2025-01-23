package com.example.components.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "opc_dominio")
@SequenceGenerator(name = "dominio_seq", sequenceName = "opc_dominio_seq", allocationSize = 1)
public class Dominio {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dominio_seq")
	private Long id;

	@Column(nullable = false)
	private String dominio;

	@Column(nullable = false, unique = true)
	private String codigo;

	@Column(nullable = false, unique = true)
	private String sigla;

	@Column(nullable = false)
	private String descricao;

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}