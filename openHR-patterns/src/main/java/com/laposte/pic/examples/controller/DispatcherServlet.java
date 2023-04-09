package com.laposte.pic.examples.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laposte.pic.examples.model.Employee;

public class DispatcherServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			// PrintWriter c'est une classe qui permet d'écrire dans le body de l'objet response
			// out c'est le writer de l'objet response, un objet qui va permettre d'écrire dans le body
			PrintWriter contenuDuBody = response.getWriter() ; // en fait on écrit dans un flux
			// récupère la fin de l'adresse pour savoir quel service est appelé
			
			String pathInfo = request.getPathInfo();
			String[] pathContent = pathInfo.split("/");
			String parameter = null;
			
			String path = pathContent[1];
			// enregistre le paramètre présent après le / s'il en existe un dans la variable parameter
			if (pathContent.length > 2) {
				parameter = pathContent[2];
			}
			
			// il faut ensuite dispatcher la requête sur le bon service
			
			switch (path) { 
				case "employee" : 
					// 
					Employee e = EmployeesController.getEmployee(parameter);
					
					// met dans le body de l'objet response (de type PrintWriter) le retour de la servlet converti en string via tostring 
					contenuDuBody.println(e.toString());
					break;
				
				case "employees" :
					List<Employee> employees = EmployeesController.getEmployees();
					String buffer = "";
					employees.forEach( emp -> {
						buffer.concat(emp.toString());
					});
					contenuDuBody.println(employees.toString());
					break;
					
				default :
					break;
				
				
				
			};
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
