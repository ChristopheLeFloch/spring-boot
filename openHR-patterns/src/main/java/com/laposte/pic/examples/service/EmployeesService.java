package com.laposte.pic.examples.service;

import java.util.List;

import com.laposte.pic.examples.core.RequestException;
import com.laposte.pic.examples.model.Employee;
import com.laposte.pic.examples.repository.methode1.EmployeesSource;

public class EmployeesService {
	
	
	// m�thode pour renvoyer une liste d'employ�s
		public static List<Employee> getEmployees() throws RequestException {
			//cr�e un objet de type EmployeesSource sans donn�es mais qui contient le moteur d'extraction des donn�es
			EmployeesSource source = new EmployeesSource();
			// cr�e une liste d'employees et la remplit avec la m�thode getEntitiesList()
			List<Employee> employees = source.getEntitiesList();
			return employees;
			
			
		}
		
		// m�thode pour renvoyer un employ�
		public static Employee getEmployeeByMatcle(String matcle) throws RequestException {
			
			//cr�e un objet de type EmployeesSource sans donn�es mais qui contient le moteur d'extraction des donn�es
			EmployeesSource source = new EmployeesSource(matcle);
			// cr�e une liste d'employees et la remplit avec la m�thode getEntitiesList()
			List<Employee> employees = source.getEntitiesList();
						
			if (!employees.isEmpty()) {
				return employees.get(0);
			}
			
			return null;
			
		}

}
