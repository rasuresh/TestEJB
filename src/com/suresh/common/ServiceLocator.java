package com.suresh.common;

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import com.suresh.client.AppMain;

public class ServiceLocator {
	private static ServiceLocator serviceLocator;

	private InitialContext ic;

	protected ServiceLocator() throws Exception {
		try {
			ic = getInitialContext();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static synchronized ServiceLocator getInstance()
			throws Exception {
		if (serviceLocator == null) {
			serviceLocator = new ServiceLocator();
		}
		return serviceLocator;
	}

	private InitialContext getInitialContext() throws Exception {
		try {
			//return new InitialContext();
			return AppMain.getInitialContext("t3://localhost:7001");
			
		} catch (Exception e) {
			throw new Exception("Unable to create InitialContext");
		}
	}

	/**
	 * EJB Lookup
	 */
	public EJBHome getEjbHome(String ejbName, Class ejbClass)
			throws Exception {
		try {
			EJBHome ejbHome = null;
			Object object = ic.lookup(ejbName);
			ejbHome = (EJBHome) PortableRemoteObject.narrow(object, ejbClass);
			if (ejbHome == null) {
				throw new Exception("Could not get home for "
						+ ejbName);
			}
			return ejbHome;
		} catch (NamingException ne) {
			throw new Exception(ne.getMessage());
		}
	}

	/**
	 * EJB Lookup via local interface
	 */
	public EJBLocalHome getEjbLocalHome(String ejbName)
			throws Exception {
		try {
			EJBLocalHome ejbLocalHome = null;
			Object object = ic.lookup(ejbName);
			ejbLocalHome = (EJBLocalHome) object;
			if (ejbLocalHome == null) {
				throw new Exception(
						"Could not get local home for " + ejbName);
			}
			return ejbLocalHome;
		} catch (NamingException ne) {
			throw new Exception(ne.getMessage());
		}
	}
}
