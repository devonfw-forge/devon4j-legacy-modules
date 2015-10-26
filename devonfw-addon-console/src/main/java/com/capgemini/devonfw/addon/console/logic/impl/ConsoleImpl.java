package com.capgemini.devonfw.addon.console.logic.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Named;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import com.capgemini.devonfw.addon.console.logic.api.Console;
import com.capgemini.devonfw.addon.console.service.api.rest.ConsoleTo;

@Named
public class ConsoleImpl implements Console, ApplicationContextAware{

	private ApplicationContext ctx;

	@Override
	public ConsoleTo execute(ConsoleTo command) {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("JavaScript");
		engine.put("ctx",ctx);
		Environment env = (Environment) ctx.getBean("environment");
		Map<String, Object> map = new HashMap();
        for(Iterator it = ((AbstractEnvironment) env).getPropertySources().iterator(); it.hasNext(); ) {
            PropertySource propertySource = (PropertySource) it.next();
            if (propertySource instanceof MapPropertySource) {
                map.putAll(((MapPropertySource) propertySource).getSource());
            }
        }
        engine.put("props", map);
		
		
		Object result;
		try {
			result = engine.eval(command.getInput());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = "[error in script] "+e;
			e.printStackTrace();
		}
		command.setOutput(result==null? "result==null":result.toString());
		return command;
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.ctx = ctx;
		
	}

}
