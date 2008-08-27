package de.dirent.tthelper.pages.helfer;


import java.text.Format;
import java.util.Date;

import org.apache.tapestry.Translator;
import org.apache.tapestry.annotations.Property;
import org.apache.tapestry.annotations.SetupRender;

import de.dirent.tthelper.entities.Helfer;
import de.dirent.tthelper.utils.BooleanFormat;
import de.dirent.tthelper.validate.DateTranslator;


public class CreateHelfer {

	@Property
	private Helfer helfer;
	

	@SetupRender
	public void validate() {
		
		if( this.helfer == null ) {
			
			this.helfer = new Helfer();
		}
	}
	
	
	private Format booleanFormat = new BooleanFormat( "Ja", "Nein" );
	
    public Format getBooleanFormat() {
    	
    	return booleanFormat;
    }
    
    public Translator<Date> getDateTranslator() {
    	
    	return new DateTranslator( "dd.MM.yyyy" );
    }
}