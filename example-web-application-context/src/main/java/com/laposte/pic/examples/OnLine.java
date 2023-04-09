package com.laposte.pic.examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class OnLine extends HttpServlet  {


	 public  void doGet(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException  {
		 
		//chargement en double d'un bean singleton 
		//SpringApplicationContext impléemente une interface permettant d'accéder à un bean
		Speaker speakerA = (Speaker)SpringApplicationContext.getBean("speakerSingleton");
		Speaker speakerB = (Speaker)SpringApplicationContext.getBean("speakerSingleton");		

		
		speakerA.setName("Laurent");
		speakerB.setName("Didier");
		
		//personSingleton1 et personSingleton2 sont deux singletons de la même classe
		//On a deux instances car il y a deux id distincts
		String speach1 = speakerB.presentPerson((Person) SpringApplicationContext.getBean("personSingleton1"));
		String speach2 = speakerA.presentPerson((Person) SpringApplicationContext.getBean("personSingleton2"));	

		response.setContentType("text/html") ;
		
		PrintWriter out = response.getWriter() ;
		out.println("<html>") ;
		out.println("<head>") ;
		out.println("<title>OnLine</title>") ;
		out.println("</head>") ;
		out.println("<body>") ;
		out.println("<h1>"+speach1+"!</h1>") ;
		out.println("<h1>"+speach2+"!</h1>") ;
		out.println("</body>") ;
		out.println("</html>") ;   
	}

	 public  void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException  {
	
		doGet(request, response) ;
	}
}
