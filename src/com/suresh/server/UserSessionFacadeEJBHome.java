package com.suresh.server;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface UserSessionFacadeEJBHome extends EJBHome {
	public UserSessionFacadeEJBRemote create() throws RemoteException, CreateException;
}
