package com.suresh.client;

import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.suresh.common.UserDelegate;

public class AppMain {

	public static void main(String ap[]) throws NamingException, EJBException, RemoteException{
		//getInitialContext("t3://localhost:7001");
		String nameFromEJB = new UserDelegate().getUserName();
		System.out.println(" Name: "+ nameFromEJB);
	}
	
	public final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
	public  static InitialContext getInitialContext(String url)	throws NamingException
	{
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
		env.put(Context.PROVIDER_URL, url);
		//env.put("weblogic.jndi.createIntermediateContexts", "true");
		return new InitialContext(env);
	}
}
