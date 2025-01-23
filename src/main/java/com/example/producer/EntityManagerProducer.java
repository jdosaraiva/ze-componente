package com.example.producer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {
    
    private EntityManagerFactory factory;
    
    public EntityManagerProducer() {
        this.factory = Persistence.createEntityManagerFactory("ConamPU");
    }

    @Produces
    @RequestScoped
    public EntityManager createEntityManager() {
        return factory.createEntityManager();
    }

    public void closeEntityManager(@Disposes EntityManager manager) {
        if (manager.isOpen()) {
            manager.close();
        }
    }
    
    // Opcional: método para fechar o factory quando o aplicativo for encerrado
    public void destroy() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}