package com.laposte.pic.examples.repository.methode2;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;

import com.hraccess.datasource.TDataNode;
import com.hraccess.openhr.exception.AuthenticationException;
import com.hraccess.openhr.exception.SessionBuildException;
import com.hraccess.openhr.exception.SessionConnectionException;
import com.hraccess.openhr.exception.UserConnectionException;
import com.laposte.pic.examples.core.OpenHRExtractionSource;
import com.laposte.pic.examples.core.RequestException;
import com.laposte.pic.examples.model.Employee;

public class EmployeesSource {
	
	private String sqlRequest;
	
	public EmployeesSource() {
		
		sqlRequest = "SELECT a.NUDOSS, b.NMPRES, a.NOMPAT FROM ZY00 a "
				+ "INNER JOIN ZY3Y b ON a.NUDOSS = b.NUDOSS ";
	} 
	
	public EmployeesSource(String parameter) {
		
		sqlRequest = "SELECT a.NUDOSS, b.NMPRES, a.NOMPAT FROM ZY00 a "
				+ "INNER JOIN ZY3Y b ON a.NUDOSS = b.NUDOSS "
				+ "WHERE a.MATCLE = '" + parameter + "'";
	} 
	
	public List<Employee> getEntitiesList() throws RequestException, AuthenticationException, SessionBuildException, SessionConnectionException, ConfigurationException, UserConnectionException, IllegalStateException {
		
		OpenHRExtractionSource source = new OpenHRExtractionSource(sqlRequest);
		
		// Process result for list REGATE
		TDataNode node = source.getDataNode();
		List<Employee> employeesList = new ArrayList<Employee>();
		if (node.hasRecords()) {
			node.first();
			
			do {
				// méthode non static donc nécessité d'instancier
				Employee e = new Employee();
				// on remplit l'objet Employee en faisant la conversion attributs du modele <=> données correspondantes HRAccess
				e.setId(node.getColumnIndexByName("NUDOSS"));
				e.setName(node.getStringByName("NMPRES"));
				e.setTitle(node.getStringByName("NOMPAT"));
				employeesList.add(e);
			} while (node.next());
		}
		
		return employeesList;
	}
	

}
