package com.integrationsi.examples.spring.spring_application_context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

	public static void main(String[] args) {
		
		//Chargement de l'applicationContext
		ApplicationContext applicationContext = 
			      new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//chargement en double d'un bean singleton 
		//SpringApplicationContext impl�mente une interface permettant d'acc�der � un bean du 
		//contexte d'application
		Speaker speakerA = (Speaker)SpringApplicationContext.getBean("speakerSingleton");
		//On peut le charger d'une mani�re classique
		Speaker speakerB = (Speaker)SpringApplicationContext.getBean("speakerSingleton");		

		
		speakerA.setName("Laurent");
		speakerB.setName("Didier");
		
		// speakerA et speakerB sont les m�me singletons
		
		//personSingleton1 et personSingleton sont deux singletons de la m�me classe
		//On a deux instances car il y a deux id distincts
		System.out.println(
				speakerB.presentPerson((Person) SpringApplicationContext.getBean("personSingleton1")));
		System.out.println(
				speakerA.presentPerson((Person) SpringApplicationContext.getBean("personSingleton2")));	
	}

}
