package de.dirent.tthelper.pages;


import org.apache.tapestry.ioc.annotations.Inject;
import org.apache.tapestry.services.RequestGlobals;


public class MeldePage extends SecuredPage {

    @Inject
    private RequestGlobals requestGlobals;

	public boolean isNotAdmin() {
		
		return !requestGlobals.getHTTPServletRequest().isUserInRole( "ROLE_ADMIN" );
	}
}
