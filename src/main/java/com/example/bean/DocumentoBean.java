package com.example.bean;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

/**
 * Backing bean para demonstração do componente de documento
 */
@Named
@RequestScoped
public class DocumentoBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = LogManager.getLogger(DocumentoBean.class);
    
    private String documento;
    
    public String getDocumento() {
        return documento;
    }
    
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    @PostConstruct
    public void init() {
    	logger.info("DocumentoBean sendo inicializado");
    }
    
    public void processar() {
    	logger.info(String.format("Processar %s", documento));
    }
}
