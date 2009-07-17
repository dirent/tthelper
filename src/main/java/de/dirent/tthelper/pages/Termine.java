package de.dirent.tthelper.pages;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import de.dirent.tthelper.entities.Termin;


public class Termine extends TTHelperPage {
	
	@Property
	private Termin termin;	
	
	public String getTerminDate() {
		
		if( this.termin != null ) {
			
			if( this.termin.getFromDate() == null  ||  this.termin.getFromDate() == this.termin.getToDate() ) {
				
				return format( this.termin.getToDate() );
			}
			
			if( this.termin.getToDate().getTime() - this.termin.getFromDate().getTime() == 1000*60*60*24 ) {
				
				return format( this.termin.getFromDate() ) + " / " + format( this.termin.getToDate() );
			}
			
			return format( this.termin.getFromDate() ) + " - " + format( this.termin.getToDate() );
		}
		
		return "";
	}
	
	
	@Inject
	private ComponentResources resources;
	
	protected String format( Date date ) {
		
		if( date == null ) return "";

		DateFormat df = SimpleDateFormat.getDateInstance( DateFormat.SHORT, resources.getLocale() );
		return df.format( date );
	}
	
	public List<Termin> getTermine() {
		
		try {
			
			return getPersistenceManager().getTermine();
			
		} catch( Exception e ) {
			
			logger.error( "Could not load current termine: " + e.getMessage() );
			return new ArrayList<Termin>();
		}
	}
}