package com.example.controller;

import java.util.List;

import com.example.model.entity.Dominio;
import com.example.service.DominioService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class DominioController {

    @Inject
    private DominioService dominioService;

    private Dominio dominio = new Dominio();

    public Dominio getDominio() {
        return dominio;
    }

    public void setDominio(Dominio dominio) {
        this.dominio = dominio;
    }

    public String save() {
        dominioService.save(dominio);
        return "listarDominios.xhtml?faces-redirect=true"; // Navegação para a página de listagem
    }

    public List<Dominio> getDominios() {
        return dominioService.findAll();
    }
}
