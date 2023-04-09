package com.laposte.pic.examples.controller;

import java.util.List;

import com.laposte.pic.examples.core.RequestException;
import com.laposte.pic.examples.model.Employee;
import com.laposte.pic.examples.service.EmployeesService;

public class EmployeesController {
	
	
	
	
	// méthode pour renvoyer une liste d'employés
	static List<Employee> getEmployees() {
		
				
		try {
			return EmployeesService.getEmployees();
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
				
		
		
		
	}
	
	// méthode pour renvoyer un employé
	static Employee getEmployee(String parameter) {
		
		try {
			return EmployeesService.getEmployeeByMatcle(parameter);
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

}
