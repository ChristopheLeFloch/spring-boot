package com.laposte.pic.examples.core;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;

import com.hraccess.datasource.TDataNode;
import com.hraccess.openhr.exception.AuthenticationException;
import com.hraccess.openhr.exception.SessionBuildException;
import com.hraccess.openhr.exception.SessionConnectionException;
import com.hraccess.openhr.exception.UserConnectionException;

public abstract class HRARequest<T> {
	// <T> permet de définir que la classe utilisera un type générique ici appelé T

	protected String sqlRequest;
	protected TDataNode node; 

	
	
	protected static String getSqlFormatFromDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String day=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		if (day.length() == 1) {day="0"+day;};
		String month=String.valueOf(calendar.get(Calendar.MONTH)+1);
		if (month.length() == 1) {month="0"+month;};
		String year=String.valueOf(calendar.get(Calendar.YEAR));
		
		String dateString=String.valueOf(day)
							+ String.valueOf(month)
							+ String.valueOf(year);
		

		return "to_date('"+dateString+"',"+"'DDMMYYYY')";
	}
	
	protected static String getSqlCodesList(List<String> matcles) {
		// TODO Auto-generated method stub
		String codesList = "(' '";
		if (matcles!=null) {
			for (String code : matcles) codesList=codesList+","+"'"+code+"'";
		}
		codesList=codesList + ")";
		return codesList;
	}
	
	protected static String getSqlNudossList(int[] nudossList) {
		// TODO Auto-generated method stub
		String sqlNudossList = "(";
		String sep="";
		for (int nudoss : nudossList) {
			sqlNudossList=sqlNudossList+sep+nudoss;
			sep=",";
		}
		sqlNudossList=sqlNudossList + ")";
		return sqlNudossList;
	}
	

	public List<T> getEntitiesList() throws RequestException {

		List<T> le = new ArrayList<T>();
		try {
			if (sqlRequest == null || sqlRequest.isEmpty() || sqlRequest.equals(" "))
				return le;
			
	
			OpenHRExtractionSource source;
		
			source = new OpenHRExtractionSource(sqlRequest);

			source.setActive(true);
			
			node = source.getDataNode(); 
			
			synchronized (node) {
				if (node.hasRecords()) {
					node.first();
					do {
	
							le.add(getEntityFromNode());
					} while (node.next());
	
				}
			}

			source.setActive(false);
			
		} catch (AuthenticationException e) {
			RequestException re = new RequestException("Erreur d'authentification", e);
			throw(re);
		
		} catch (SessionBuildException e) {
			RequestException re = new RequestException("Erreur lors de la création de la session", e);
			throw(re);			
		}catch (SessionConnectionException e) {
			RequestException re = new RequestException("Impossible de se connecter à la session", e);
			throw(re);			
		} catch (ConfigurationException e) {
			RequestException re = new RequestException("Problème lors de la lecture de la configuration", e);
			throw(re);			  
		} catch (UserConnectionException e) {
			RequestException re = new RequestException("Problème lors de la connexion de l'utilisateur", e);
			throw(re);			
		} catch (IllegalStateException e ) {
			RequestException re = new RequestException(e);
			throw(re);
		}
		
		return le;
	}
	
	protected abstract T getEntityFromNode();

} 