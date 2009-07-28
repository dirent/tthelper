package de.dirent.tthelper.pages;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.annotations.Property;

import de.dirent.tthelper.entities.Meldung;


public class Start extends TTHelperPage {
	
	@Property
	private Meldung meldung;
	
	
	public String getPublishDate() {
		
		if( meldung == null ) return "";

		DateFormat df = new SimpleDateFormat( "dd. MMMM yyyy",  Locale.GERMAN );
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