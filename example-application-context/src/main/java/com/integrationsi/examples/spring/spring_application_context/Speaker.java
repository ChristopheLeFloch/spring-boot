package com.integrationsi.examples.spring.spring_application_context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class Speaker {
	
	private String name;
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String presentPerson(Person person) {
		return "je m'appele " + this.name + ", je vous presente " + person.getName();
	}
	
	@Bean
	@Scope("singleton")
	public Speaker speakerSingleton() {
	    return new Speaker();
	}
}
