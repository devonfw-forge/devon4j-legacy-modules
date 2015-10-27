package com.capgemini.devonfw.addon.console.service.impl.rest;

import java.net.URL;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capgemini.devonfw.addon.console.logic.api.Console;
import com.capgemini.devonfw.addon.console.service.api.rest.ConsoleTo;


@Path("/devonfw/console/v1")
@Named("ConsoleRestService")
public class ConsoleRestServiceImpl {

	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(ConsoleRestServiceImpl.class);

	@Inject
	private Console console;

	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/info")
	public String info(){
		return "info is correct";
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Path("/execute/")
	public ConsoleTo execute(ConsoleTo command) {
		return console.execute(command);
	}

	public Console getConsole() {
		return console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}
	
}
