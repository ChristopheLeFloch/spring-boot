package com.integrationsi.examples.spring.spring_web_java_application_context;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringApplicationConfig {
	
	
	@Bean()
	public Speaker speakerSingleton() {		
		Speaker s = new Speaker();
		s.setName("Laurent");
		return s;		
	}
	
	@Bean()
	public Person personSingleton1() {		
		Person p = new Person();
		p.setName("Barbara");
		return p;		
	}
	
	@Bean()
	public Person personSingleton2() {		
		Person p = new Person();
		p.setName("Laurence");
		return p;		
	}
	
	@Bean()
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

}
