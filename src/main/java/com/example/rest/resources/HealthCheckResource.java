package com.example.rest.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/health")
public class HealthCheckResource {
	
	private static final Logger logger = LogManager.getLogger(HealthCheckResource.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response health() {
		final String message = "{ \"status\" : \"OK\" }";
		logger.debug(message);
		return Response.ok(message).build();
	}

}
