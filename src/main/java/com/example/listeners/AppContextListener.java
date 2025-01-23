package com.example.listeners;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
	
	private static final Logger logger = LogManager.getLogger(AppContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.debug("Contexto iniciado!");
		Locale.setDefault(Locale.ENGLISH);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.debug("Contexto destruído!");
	}
}
