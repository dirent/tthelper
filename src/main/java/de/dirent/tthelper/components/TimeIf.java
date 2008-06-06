package de.dirent.tthelper.components;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.tapestry.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TimeIf {

    private final Logger logger = LoggerFactory.getLogger( TimeIf.class );

    @Parameter( required=true, defaultPrefix="literal" )
    private String pattern;

    public boolean isActive() {
		
		SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy", Locale.US );
		
		try {
			
			return sdf.parse( pattern ).getTime() > System.currentTimeMillis();
			
		} catch( ParseException pe ) {
			
			logger.warn( "Could not parse date for component TimeIf: " + pe.getMessage() );
		}
		
		return false;
	}
}
