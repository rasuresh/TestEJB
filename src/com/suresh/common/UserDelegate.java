package com.suresh.common;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBException;

import com.suresh.server.UserSessionFacadeEJB;
import com.suresh.server.UserSessionFacadeEJBHome;
import com.suresh.server.UserSessionFacadeEJBRemote;

public class UserDelegate {
	
	public UserDelegate(){
		initializeAccountSessionFacadeEJBRemote();
	}
	private UserSessionFacadeEJBHome userSessionFacadeEJBHome;
	private UserSessionFacadeEJBRemote userSessionFacadeEJBRemote;
	   /**
	   * lookup remote interface to UserSessionFacadeEJB
	   */
	  private void initializeAccountSessionFacadeEJBRemote() throws RuntimeException {

	    if (userSessionFacadeEJBHome == null) {
	      String FACADE_NAME = "UserSessionFacadeEJB";
	      Class  FACADE_CLASS = UserSessionFacadeEJB.class;
	      try {
	        ServiceLocator locator = ServiceLocator.getInstance();
	        userSessionFacadeEJBHome = (UserSessionFacadeEJBHome)locator.getEjbHome(FACADE_NAME, FACADE_CLASS);
	        if (userSessionFacadeEJBHome == null) {
	        	System.out.println("userSessionFacadeEJBHome is Null");
	          throw new RuntimeException("Did not get home for " + FACADE_NAME);
	        }else{
	        	System.out.println("userSessionFacadeEJBHome is not Null");
	        }
	        userSessionFacadeEJBRemote = userSessionFacadeEJBHome.create();
	      }
	      catch (CreateException e) {
	        throw new RuntimeException(e.getMessage());
	      }
	      catch (RemoteException e) {
	        throw new RuntimeException(e.getMessage());
	      }
	      catch (Exception e) {
	        throw new RuntimeException(e.getMessage());
	      }
	    }
	}
	  
	  public String getUserName() throws EJBException, RemoteException{
		  String  name = userSessionFacadeEJBRemote.getUserFirstName(1);
		  return name;
	  }
}
