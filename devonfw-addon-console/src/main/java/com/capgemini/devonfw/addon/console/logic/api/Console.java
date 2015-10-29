package com.capgemini.devonfw.addon.console.logic.api;

import com.capgemini.devonfw.addon.console.service.api.rest.ConsoleTo;

public interface Console {

	public ConsoleTo execute(ConsoleTo command);
}
