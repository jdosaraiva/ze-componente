package com.example.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class Bean {
	
	private static Logger logger = LogManager.getLogger(Bean.class);
	
	public void action() {
		logger.debug("Botão clicado!");
	}

}
