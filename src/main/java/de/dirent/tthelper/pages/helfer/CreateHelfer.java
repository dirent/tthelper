package de.dirent.tthelper.pages.helfer;


import java.text.Format;
import java.util.Date;
import java.util.List;

import org.apache.tapestry.Translator;
import org.apache.tapestry.annotations.InjectPage;
import org.apache.tapestry.annotations.Property;
import org.apache.tapestry.annotations.SetupRender;

import de.dirent.tthelper.entities.Helfer;
import de.dirent.tthelper.entities.PokalMannschaft;
import de.dirent.tthelper.pages.Start;
import de.dirent.tthelper.pages.TTHelperPage;
import de.dirent.tthelper.utils.BooleanFormat;
import de.dirent.tthelper.validate.DateTranslator;


public class CreateHelfer extends TTHelperPage {

	@Property
	private Helfer helfer;
	

	@SetupRender
	public void validate() {
		
		if( this.helfer == null ) {
			
			this.helfer = new Helfer();
		}
	}
	
	@InjectPage
	private Start startPage;
	
	public Object onSuccessFromHelfer() {

		getPersistenceManager().add( this.helfer );
		
		return startPage;
	}


	public List<Helfer> getGemeldeteHelfer() {

		return getPersistenceManager().getAllHelfer();
	}
	

	
	private Format booleanFormat = new BooleanFormat( "Ja", "Nein" );
	
    public Format getBooleanFormat() {
    	
    	return booleanFormat;
    }
    
    public Translator<Date> getDateTranslator() {
    	
    	return new DateTranslator( "dd.MM.yyyy" );
    }
}