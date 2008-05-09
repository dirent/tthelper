package de.dirent.tthelper.pages;


import org.acegisecurity.annotation.Secured;
import org.acegisecurity.userdetails.UserDetailsService;
import org.apache.tapestry.annotations.ApplicationState;
import org.apache.tapestry.ioc.annotations.Inject;
import org.apache.tapestry.services.RequestGlobals;

import de.dirent.tthelper.entities.UserDetailsBean;
import de.dirent.tthelper.model.Verein;
import de.dirent.tthelper.services.PersistenceManager;


@Secured( "ROLE_USER" )
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
	
	protected Verein getCurrentVerein() {
		
		if( !userDetailsBeanExists ) {
			
			String username = requestGlobals.getHTTPServletRequest().getUserPrincipal().getName();			
			userDetailsBean = (UserDetailsBean) userDetailsService.loadUserByUsername(username);
		}
		
		return userDetailsBean.getVerein();
	}
}
