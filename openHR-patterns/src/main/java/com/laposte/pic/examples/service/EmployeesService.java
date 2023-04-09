package com.laposte.pic.examples.service;

import java.util.List;

import com.laposte.pic.examples.core.RequestException;
import com.laposte.pic.examples.model.Employee;
import com.laposte.pic.examples.repository.methode1.EmployeesSource;

public class EmployeesService {
	
	
	// méthode pour renvoyer une liste d'employés
		public static List<Employee> getEmployees() throws RequestException {
			//crée un objet de type EmployeesSource sans données mais qui contient le moteur d'extraction des données
			EmployeesSource source = new EmployeesSource();
			// crée une liste d'employees et la remplit avec la méthode getEntitiesList()
			List<Employee> employees = source.getEntitiesList();
			return employees;
			
			
		}
		
		// méthode pour renvoyer un employé
		public static Employee getEmployeeByMatcle(String matcle) throws RequestException {
			
			//crée un objet de type EmployeesSource sans données mais qui contient le moteur d'extraction des données
			EmployeesSource source = new EmployeesSource(matcle);
			// crée une liste d'employees et la remplit avec la méthode getEntitiesList()
			List<Employee> employees = source.getEntitiesList();
						
			if (!employees.isEmpty()) {
				return employees.get(0);
			}
			
			return null;
			
		}

}
