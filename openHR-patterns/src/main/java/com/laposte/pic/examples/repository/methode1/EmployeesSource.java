package com.laposte.pic.examples.repository.methode1;

import com.laposte.pic.examples.core.HRARequest;
import com.laposte.pic.examples.model.Employee;




			public class EmployeesSource extends HRARequest<Employee> {
				// <Employee> indique ici que la classe HRARequest utilisera un objet Employee 
				// (la classe HRARequest a �t� d�finie comme utilisant un type g�n�rique T, donc l� on lui pr�cise que ce type ici est un Employee)
				

				
				public EmployeesSource() {
					
					sqlRequest = "SELECT a.NUDOSS, b.NMPRES, a.NOMPAT FROM ZY00 a "
							+ "INNER JOIN ZY3Y b ON a.NUDOSS = b.NUDOSS ";
				} 
				
				public EmployeesSource(String parameter) {
					
					sqlRequest = "SELECT a.NUDOSS, b.NMPRES, a.NOMPAT FROM ZY00 a "
							+ "INNER JOIN ZY3Y b ON a.NUDOSS = b.NUDOSS "
							+ "WHERE a.MATCLE = '" + parameter + "'";
				} 
				
				

				@Override
				protected Employee getEntityFromNode() {
					
					// m�thode non static donc n�cessit� d'instancier
					Employee e = new Employee();
					// on remplit l'objet Employee en faisant la conversion attributs du modele <=> donn�es correspondantes HRAccess
					e.setId(node.getColumnIndexByName("NUDOSS"));
					e.setName(node.getStringByName("NMPRES"));
					e.setTitle(node.getStringByName("NOMPAT"));
					
				// maintenant on a un objet Employee contenant trois attributs id (nudoss) name (nmpres) et title (nompat) comme on l'a d�fini dans le mod�le
					return e;
					
				};
				
			} 