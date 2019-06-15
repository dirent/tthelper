package de.dirent.tthelper.pages;


import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.RequestGlobals;


public class MeldePage extends SecuredPage {

    @Inject
    private RequestGlobals requestGlobals;

	public boolean isNotAdmin() {
		
		return !requestGlobals.getHTTPServletRequest().isUserInRole( "ROLE_ADMIN" );
	}
}
