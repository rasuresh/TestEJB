package com.suresh.server;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.EJBObject;

public interface UserSessionFacadeEJBRemote extends  EJBObject {
	public String getUserFirstName(int userId)throws EJBException, RemoteException;
}
