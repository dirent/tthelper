package de.dirent.tthelper.components;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.RequestGlobals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TimeIf {

    private final Logger logger = LoggerFactory.getLogger( TimeIf.class );

    @Parameter( required=true, defaultPrefix="literal" )
    private String pattern;

    @Parameter( required=false )
    private boolean adminAccess = true;
    
    @Inject
    private RequestGlobals requestGlobals;


    public boolean isActive() {
		
    	if( adminAccess ) {
    		
    		// check, if user is in role 'ADMIN_ROLE'
    		if( requestGlobals.getHTTPServletRequest().isUserInRole( "ROLE_ADMIN" ) ) {
    			return true;
    		}
    	}

		SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy", Locale.US );
		
		try {
			
			return sdf.parse( pattern ).getTime() > System.currentTimeMillis();
			
		} catch( ParseException pe ) {
			
			logger.warn( "Could not parse date for component TimeIf: " + pe.getMessage() );
		}
		
		return false;
	}
}
