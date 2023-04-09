package com.laposte.pic.examples.core;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.hraccess.openhr.HRApplication;
import com.hraccess.openhr.HRSessionFactory;
import com.hraccess.openhr.IHRRole;
import com.hraccess.openhr.IHRSession;
import com.hraccess.openhr.IHRUser;
import com.hraccess.openhr.beans.HRDataSourceParameters;
import com.hraccess.openhr.dossier.HRDossierCollection;
import com.hraccess.openhr.dossier.HRDossierCollectionParameters;
import com.hraccess.openhr.dossier.HRDossierFactory;
import com.hraccess.openhr.exception.AuthenticationException;
import com.hraccess.openhr.exception.SessionBuildException;
import com.hraccess.openhr.exception.SessionConnectionException;
import com.hraccess.openhr.exception.UserConnectionException;

public final class OpenHrSession {

	  private static IHRSession session;
	  private static IHRUser user;
	  private static IHRRole role;
	  
	    public static IHRRole getRole() {
		return role;
	}

	public static void setRole(IHRRole role) {
		OpenHrSession.role = role;
	}


	// L'utilisation du mot cl� volatile
	    // permet d'�viter le cas o� "Singleton.instance" est non nul,
	     // mais pas encore "r�ellement" instanci�.
	  private static volatile OpenHrSession instance = null;

	    private OpenHrSession() throws  SessionBuildException, 
	                              SessionConnectionException, 
	                              ConfigurationException, 
	                              AuthenticationException, 
	                              UserConnectionException, 
	                              IllegalStateException {
	         super();
	         HRApplication.configureLogs(OpenhrConfiguration.logPath);

	         // Creating from given OpenHR configuration file and connecting session to HR Access server
	         this.session = HRSessionFactory.getFactory().createSession(
	            new PropertiesConfiguration(OpenhrConfiguration.openHrProperties));
	 
	    }

	      /**
	   * M�thode permettant de renvoyer la session
	   * 
	   * @return Retourne l'instance du singleton.
	   * @throws IllegalStateException
	   * @throws UserConnectionException
	   * @throws ConfigurationException
	   * @throws SessionConnectionException
	   * @throws SessionBuildException
	   * @throws AuthenticationException
	   */
	  protected final static OpenHrSession getInstance() throws  SessionBuildException,
	      SessionConnectionException, ConfigurationException, AuthenticationException, UserConnectionException,
	      IllegalStateException {
	        //Le "Double-Checked Singleton"/"Singleton doublement v�rifi�" permet 
	         //d'�viter un appel co�teux � synchronized, 
	         //une fois que l'instanciation est faite.
	        if (OpenHrSession.instance == null ) {
	           // Le mot-cl� synchronized sur ce bloc emp�che toute instanciation
	           // multiple m�me par diff�rents "threads".
	           // Il est TRES important.
	           synchronized(OpenHrSession.class) {
	             if (OpenHrSession.instance == null) {
	               OpenHrSession.instance = new OpenHrSession();
	               user = session.connectUser(OpenhrConfiguration.username, OpenhrConfiguration.password);
	               role = user.getRole("ADMIN-APPLICATIF()");
	             }
	           }
	        }
	        if (! session.isConnected()) {
	        	user = session.connectUser(OpenhrConfiguration.username, OpenhrConfiguration.password);

	        }

	        return OpenHrSession.instance;
	    }

	    protected IHRSession getSession() {
	      return session;

	    }

	    /**
	     * 
	     * @return l'utilisateur connect�
	     */
	    protected IHRUser getUser() {
	      return user;
	    }

	    


	}