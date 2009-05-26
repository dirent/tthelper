package de.dirent.tthelper.pages;


import org.acegisecurity.userdetails.UserDetailsService;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.RequestGlobals;

import de.dirent.tthelper.entities.UserDetailsBean;
import de.dirent.tthelper.model.Verein;
import de.dirent.tthelper.services.PersistenceManager;


public class TTHelperPage {

	@Inject
	private PersistenceManager persistenceManager;

	protected PersistenceManager getPersistenceManager() {
		
		return persistenceManager;
	}
	
	@Inject
	private UserDetailsService userDetailsService;
	@Inject
	private RequestGlobals requestGlobals;
	
	@ApplicationState
	private UserDetailsBean userDetailsBean;

	private boolean userDetailsBeanExists;
	
	public Verein getCurrentVerein() {
		
		if( !isUserAuthenticated() ) return null;
		
		if( !userDetailsBeanExists ) {
			
			String username = requestGlobals.getHTTPServletRequest().getUserPrincipal().getName();			
			userDetailsBean = (UserDetailsBean) userDetailsService.loadUserByUsername(username);
		}
		
		return userDetailsBean.getVerein();
	}
	
	protected boolean isUserAuthenticated() {
		
		return requestGlobals.getHTTPServletRequest().getUserPrincipal() != null;
	}
	
	public String getCurrentUsername() {
		
		if( !isUserAuthenticated() ) return null;
		
		
		return requestGlobals.getHTTPServletRequest().getUserPrincipal().getName();
	}
}
