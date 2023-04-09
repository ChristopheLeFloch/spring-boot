package com.laposte.pic.examples.core;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.hraccess.openhr.HRApplication;
import com.hraccess.openhr.HRSessionFactory;
import com.hraccess.openhr.IHRRole;
import com.hraccess.openhr.IHRSession;
import com.hraccess.openhr.IHRUser;
import com.hraccess.openhr.beans.HRExtractionSource;
import com.hraccess.openhr.exception.AuthenticationException;
import com.hraccess.openhr.exception.SessionBuildException;
import com.hraccess.openhr.exception.SessionConnectionException;
import com.hraccess.openhr.exception.UserConnectionException;

public class OpenHRExtractionSource extends HRExtractionSource {

	private  HRExtractionSource extractionSource;
	


			public OpenHRExtractionSource( String sqlExtraction) throws AuthenticationException, SessionBuildException, SessionConnectionException, ConfigurationException, UserConnectionException, IllegalStateException {
				/*
				 * Création à partir d'un fichier de configuration openhr.properties et
				 * connexion d'une session au serveur HR Access
				 */
				OpenHrSession session = OpenHrSession.getInstance();
				
				IHRUser user = session.getUser();
				IHRRole role = session.getRole();

				
				this.setRole(role);
				this.setConversation(user.getMainConversation());
				this.setSQLExtraction(sqlExtraction);
				
				// Extracting up to 100 rows (upper bound is 99 999 rows)
				this.setMaxRowCount(OpenhrConfiguration.maxRow);
				
			}

		} 