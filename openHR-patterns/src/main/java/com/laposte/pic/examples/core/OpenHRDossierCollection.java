package com.laposte.pic.examples.core;

import org.apache.commons.configuration.ConfigurationException;

import com.hraccess.openhr.IHRRole;
import com.hraccess.openhr.IHRUser;
import com.hraccess.openhr.beans.HRDataSourceParameters;
import com.hraccess.openhr.dossier.HRDossierCollection;
import com.hraccess.openhr.dossier.HRDossierCollectionParameters;
import com.hraccess.openhr.dossier.HRDossierFactory;
import com.hraccess.openhr.exception.AuthenticationException;
import com.hraccess.openhr.exception.SessionBuildException;
import com.hraccess.openhr.exception.SessionConnectionException;
import com.hraccess.openhr.exception.UserConnectionException;

public class OpenHRDossierCollection {
	
	
    public static HRDossierCollection getEventCollection () throws AuthenticationException, SessionBuildException, SessionConnectionException, ConfigurationException, UserConnectionException, IllegalStateException {

		//Creating configuration to handle HR Access employee dossiers
		HRDossierCollectionParameters parameters = new HRDossierCollectionParameters();
		parameters.setType(HRDossierCollectionParameters.TYPE_NORMAL);
		parameters.setProcessName("AG0GD"); 
		parameters.setDataStructureName("ZY"); 
		
    	parameters.setActivity("GTAFAC01");
		
		
        //Reading data sections
        parameters.addDataSection(new HRDataSourceParameters.DataSection("00"));
		parameters.addDataSection(new HRDataSourceParameters.DataSection("78"));
	
		OpenHrSession session = OpenHrSession.getInstance();
		
		IHRUser user = session.getUser();
		IHRRole role = session.getRole();

		
		//Instantiating a new dossier collection with given role, conversation and configuration
		HRDossierCollection dossierCollection = new HRDossierCollection(parameters,	
																		user.getMainConversation(),	
																		role,
																		new HRDossierFactory(HRDossierFactory.TYPE_DOSSIER));
		
		return dossierCollection;
    }

}
