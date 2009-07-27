package de.dirent.tthelper.pages;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import de.dirent.tthelper.entities.Meldung;
import de.dirent.tthelper.entities.Termin;


public class Start extends TTHelperPage {
	
	@Property
	private Meldung meldung;
	
	
	@Inject
	private ComponentResources resources;
	
	public String getPublishDate() {
		
		if( meldung == null ) return "";

		
		DateFormat df = new SimpleDateFormat( "dd.MMMM yyyy",  resources.getLocale() );
		return df.format( meldung.getPublishDate() );
	}
	
	public List<Meldung> getMeldungen() {
		
		try {
			
			return getPersistenceManager().getMonthlyMeldungen();
			
		} catch( Exception e ) {
			
			logger.error( "Could not load current termine: " + e.getMessage() );
			return new ArrayList<Meldung>();
		}
	}
}